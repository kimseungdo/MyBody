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
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버연결
			String url = "jdbc:mysql://localhost:3306/myweight?serverTimezone=Asia/Seoul"; // DB연결
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, "root", "1234");
			System.out.println("데이터베이스 연결 성공");
			// statement 문장수행
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM myweight");
			while (rs.next()) { // 다음레코드로 이동
				String date = rs.getString("date"); // 필드값을 가져온다
				float weight = rs.getFloat("weight");// 필드값을 가져온다
				System.out.println(date + " " + weight);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
	}
}
