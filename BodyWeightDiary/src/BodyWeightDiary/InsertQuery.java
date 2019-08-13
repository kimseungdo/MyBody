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
		Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버연결
		String url = "jdbc:mysql://localhost:3306/myweight?serverTimezone=Asia/Seoul"; // DB연결
		System.out.println("드라이버 적재 성공");
		con = DriverManager.getConnection(url, "root", "1234");
		System.out.println("데이터베이스 연결 성공");
		// statement 문장수행
		Statement stmt = con.createStatement();			
		
		ResultSet rs = stmt.executeQuery("SELECT weight FROM myweight WHERE date LIKE '%" + d + "%'");
		
		float weight = -1;
		
		if (!rs.next()) // 값이 없다면
		{
			String s = "INSERT INTO myweight VALUES ";
			s += " ('" + d + "'," + w + ", " + h + ")";
			int i = stmt.executeUpdate(s);
			if (i == 1)
				System.out.println("레코드 추가 성공");
			else
				System.out.println("레코드 추가 실패");
		}	
		
		else // 값이 있다면 수정
		{
			String s = "UPDATE myweight SET weight= " + w + " WHERE date = '" + d + "'";
			int i = stmt.executeUpdate(s);
			if (i == 1)
				System.out.println("레코드 수정 성공");
			else
				System.out.println("레코드 수정 실패");

		}
		
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버를 찾을 수 없습니다.");
	} catch (SQLException e) {
		System.out.println("연결에 실패하였습니다.");
	}
	}
	
}
