package BodyWeightDiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetweightQuery {
	public double SQ(String d) { 

	Connection con = null;
	double weight = -1;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver"); // ����̹�����
		String url = "jdbc:mysql://localhost:3306/myweight?serverTimezone=Asia/Seoul"; // DB����
		//System.out.println("����̹� ���� ����");
		con = DriverManager.getConnection(url, "root", "1234");
		//System.out.println("�����ͺ��̽� ���� ����");
		// statement �������
		Statement stmt = con.createStatement();			
		
		ResultSet rs = stmt.executeQuery("SELECT weight FROM myweight WHERE date LIKE '%" + d + "%'");
		
		while (rs.next()) { // �������ڵ�� �̵�
			weight = rs.getFloat("weight");// �ʵ尪�� �����´�
		}
	} catch (ClassNotFoundException e) {
		System.out.println("����̹��� ã�� �� �����ϴ�.");
	} catch (SQLException e) {
		System.out.println("���ῡ �����Ͽ����ϴ�.");
	}
	return Math.round(weight*10)/10.0;
	}	
}