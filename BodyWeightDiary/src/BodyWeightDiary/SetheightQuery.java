package BodyWeightDiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetheightQuery {
	public double SQ() { 

		Connection con = null;
		double height = -1;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ����̹�����
			String url = "jdbc:mysql://localhost:3306/myweight?serverTimezone=Asia/Seoul"; // DB����
			//System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url, "root", "1234");
			//System.out.println("�����ͺ��̽� ���� ����");
			// statement �������
			Statement stmt = con.createStatement();			
			
			ResultSet rs = stmt.executeQuery("SELECT height FROM myweight ORDER BY height DESC LIMIT 1");
			
			while (rs.next()) { // �������ڵ�� �̵�
				height = rs.getFloat("height");// �ʵ尪�� �����´�
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("���ῡ �����Ͽ����ϴ�.");
		}
	return height;
	}	
}

