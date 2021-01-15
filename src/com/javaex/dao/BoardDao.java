package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.BoardVo;

public class BoardDao {
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
			public int insert(BoardVo bVo) {
				int count = 0;
				getConnection();
				try {
					String query="";
					query +=" INSERT INTO board";
					query +=" VALUES (seq_board_no.nextval,";
					query +=" ?,";
					query +=" ?,";
					query +=" ?,";
					query +=" SYSDATE,";
					query +=" ?)";
					
				} catch (SQLException e) {
					System.out.println("error:" + e);
				}
				close();
			}
}
