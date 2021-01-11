package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private int count = 0;
	
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
	}
	
	private void close() {
		// 5. 자원정리
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
	
	public List<GuestVo> getList() {
		
		List<GuestVo> guList = new ArrayList<GuestVo>();
		
		getConnection();
		
		try {		
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query +=" select	no,";
			query +="			name,";
			query +="			password,";
			query +="			content,";
			query +="			reg_date";
			query +=" from guestbook";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regdate = rs.getString("reg_date");
				GuestVo guVo = new GuestVo(no, name, password, content, regdate);
				
				guList.add(guVo);
			}
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		
		return guList;
	}
	
	public int guestinsert(GuestVo guVo) {
		
		getConnection();
		
		try {		
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query +=" insert into guestbook";
			query +=" values (seq_guest_id.nextval, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, guVo.getName());
			pstmt.setString(2, guVo.getPassword());
			pstmt.setString(3, guVo.getContent());
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[DAO] : " +count+ "건 등록");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		
		return count;
	}
	
	public int guestdelete(GuestVo guVo) {
		
		getConnection();
		
		try {		
			// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query +=" DELETE FROM guestbook WHERE no = ? ";
			query +=" and password =?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, guVo.getNo());
			pstmt.setString(2, guVo.getPassword());
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[DAO] : " +count+ "건 삭제");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		close();
		
		return count;
		
	}
	
	
}