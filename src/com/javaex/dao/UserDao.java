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
		}//로그인시 세션의 정보 저장용
		public UserVo getUdes(String id, String pw) {
			UserVo userVo = null;//디폴트 값은 널이다
			getConnection();
			try {
				String query="";
				query += " SELECT no,";
				query += "        name";
				query += " FROM users";
				query += " WHERE id = ?";
				query += " AND password = ?";
				
				pstmt =conn.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				
				rs= pstmt.executeQuery();
				//결과처리
				while (rs.next()) {
					int no= rs.getInt("no");
					String name =rs.getString("name"); 
					userVo = new UserVo(no,name);
					//주소가 생기면 주소를 넣는다.		
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			close();
			return userVo;
			//들어간 주소값을 리턴
		}
		//사용자 정보 가지고 오기 메소드(회원정보 수정용)
		public UserVo getUser(int no) {
			UserVo userVo = null;
			getConnection();
			try {
				String query="";
				query += " SELECT no,";
				query += "        id,";
				query += "        password,";
				query += "        name,";
				query += "        gender";
				query += " FROM users";
				query += " WHERE no = ?";
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				//결과처리
				while(rs.next()) {
					int uNo= rs.getInt("no");
					String id =rs.getString("id");
					String password = rs.getString("password"); 
					String name =rs.getString("name");
					String gender =rs.getString("gender"); 
					userVo =new UserVo(uNo,id,password,name,gender);
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
			close();
			return userVo;
		}
		public int userupdate(UserVo uVo) {
			int count = 0;
			getConnection();
			
			try {
			
				// 3. SQL문 준비 / 바인딩 / 실행
				String query ="";
				query +=" update users";
				query +=" set password = ?,";
				query +="     name = ?,";
				query +="     gender = ?";
				query +=" where no = ?";
				
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1,uVo.getPassword());
				pstmt.setString(2, uVo.getName());
				pstmt.setString(3, uVo.getGender());
				pstmt.setInt(4, uVo.getNo());
				
				count = pstmt.executeUpdate();
				System.out.println("[DAO]: " + count + "건이 수정되었습니다.");
				// 4.결과처리
				System.out.println("userDao : "+count);
			} catch (SQLException e) {
			    System.out.println("error:" + e);
			}
			close();
			
			return count;
		}

		
}
