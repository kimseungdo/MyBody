package BodyWeightDiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertQuery {
	public void IQ(String d, double w, double h) {
	
	Connection con = null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver"); // ����̹�����
		String url = "jdbc:mysql://localhost:3306/myweight?serverTimezone=Asia/Seoul"; // DB����
		System.out.println("����̹� ���� ����");
		con = DriverManager.getConnection(url, "root", "1234");
		System.out.println("�����ͺ��̽� ���� ����");
		// statement �������
		Statement stmt = con.createStatement();			
		
		ResultSet rs = stmt.executeQuery("SELECT weight FROM myweight WHERE date LIKE '%" + d + "%'");
		
		float weight = -1;
		
		if (!rs.next()) // ���� ���ٸ�
		{
			String s = "INSERT INTO myweight VALUES ";
			s += " ('" + d + "'," + w + ", " + h + ")";
			int i = stmt.executeUpdate(s);
			if (i == 1)
				System.out.println("���ڵ� �߰� ����");
			else
				System.out.println("���ڵ� �߰� ����");
		}	
		
		else // ���� �ִٸ� ����
		{
			String s = "UPDATE myweight SET weight= " + w + " WHERE date = '" + d + "'";
			int i = stmt.executeUpdate(s);
			if (i == 1)
				System.out.println("���ڵ� ���� ����");
			else
				System.out.println("���ڵ� ���� ����");

		}
		
	} catch (ClassNotFoundException e) {
		System.out.println("����̹��� ã�� �� �����ϴ�.");
	} catch (SQLException e) {
		System.out.println("���ῡ �����Ͽ����ϴ�.");
	}
	}
	
}
