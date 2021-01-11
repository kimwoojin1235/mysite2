package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {
	// 0. import java.sql.*;
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		// db정보
		private String driver = "oracle.jdbc.driver.OracleDriver";
		private String url = "jdbc:oracle:thin:@localhost:1521:xe";
		private String id = "webdb";
		private String pw = "webdb";
		//db연결
		private void getConnection() {
			try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName(driver);
				// 2. Connection 얻어오기
				conn = DriverManager.getConnection(url, id, pw);
				System.out.println("접속 성공");
			} catch (ClassNotFoundException e) {
				System.out.println("error: 드라이버 로딩 실패 - " + e);
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}

		// 자원 정리
		private void close() {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		public int insert(UserVo uVo) { //저장
			int count = 0;
			getConnection();
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				String query="";
				query += " INSERT INTO users";
				query += " VALUES (seq_users_no.nextval,";
				query += " ?, ";
				query += " ?, ";
				query += " ?, ";
				query += " ? ";
				query += " )";
				pstmt =conn.prepareStatement(query);//쿼리로 만들기
				pstmt.setString(1, uVo.getId());
				pstmt.setString(2, uVo.getPassword());
				pstmt.setString(3, uVo.getName());
				pstmt.setString(4, uVo.getGender());
				
				count = pstmt.executeUpdate();//쿼리문 실행
				// 4.결과처리
				System.out.println();
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			close();
			return count;
		}
}
