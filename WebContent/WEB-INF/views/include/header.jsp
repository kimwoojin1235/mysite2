<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.UserVo"%>

<%
	UserVo uVo = (UserVo) session.getAttribute("authuser");
%>

<div id="header">
	<h1>
		<a href="/mysite2/main">MySite</a>
	</h1>

	<%
		if (uVo == null) {
	%>
	<ul>
		<li><a href="/mysite2/user?action=loginForm">로그인</a></li>
		<li><a href="/mysite2/user?action=joinForm">회원가입</a></li>
	</ul>
	<%
		} else {
	%>
	<ul>
		<li><%=uVo.getName()%> 님 안녕하세요^^</li>
		<li><a href="/mysite2/user?action=logout">로그아웃</a></li>
		<li><a href="/mysite2/user?action=modifiyForm">회원정보수정</a></li>
	</ul>
	<%
		}
	%>
</div>
<!-- //header -->

<div id="nav">
<ul>
	<li><a href="/mysite2/gbc?action=addlist">방명록</a></li>
	<li><a href="">갤러리</a></li>
	<li><a href="">게시판</a></li>
	<li><a href="">입사지원서</a></li>
</ul>
<div class="clear"></div>
</div>
<!-- //nav -->