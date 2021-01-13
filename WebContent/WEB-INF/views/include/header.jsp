<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.javaex.vo.UserVo"%>
<%
 	UserVo authUser = (UserVo)session.getAttribute("authuser");
//세션안의 값이 있는지 없는지 확인 하는거(인증용)
%>
	<div id="header">
			<h1><a href="">MySite</a></h1>
			<!-- 로그인을 하지 안았을때 -->
			<%if(authUser == null) {%>
			<!-- authUser안에 값이 없다면 -->
			<ul>
				<li><a href="/mysite2/user?action=loginForm">로그인</a></li>
				<li><a href="/mysite2/user?action=joinForm">회원가입</a></li>
			</ul>
			<%}else{ %>
			<!-- 로그인 했다면 -->
			<ul>
				<li><%=authUser.getName()%>님 안녕하세요^^</li>
				<li><a href="/mysite2/user?action=logout">로그아웃</a></li>
				<li><a  href="/mysite2/user?action=modifyForm">회원정보수정</a></li>
			</ul>
			<%} %>
			
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="/mysite2/pbc?action=list">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->   
    
