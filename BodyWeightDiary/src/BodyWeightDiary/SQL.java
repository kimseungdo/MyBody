package BodyWeightDiary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL {

	public static void main(String[] args) {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ����̹�����
			String url = "jdbc:mysql://localhost:3306/myweight?serverTimezone=Asia/Seoul"; // DB����
			System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("�����ͺ��̽� ���� ����");
			// statement �������
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM myweight");
			while (rs.next()) { // �������ڵ�� �̵�
				String date = rs.getString("date"); // �ʵ尪�� �����´�
				float weight = rs.getFloat("weight");// �ʵ尪�� �����´�
				System.out.println(date + " " + weight);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("���ῡ �����Ͽ����ϴ�.");
		}
	}
}
