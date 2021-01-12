<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@page import="com.javaex.vo.UserVo"%>
    <%
 	UserVo authUser = (UserVo)session.getAttribute("authuser");
//세션안의 값이 있는지 없는지 확인 하는거(인증용)
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/mysite2/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="/mysite2/assets/css/user.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrap">

		<div id="header">
			<h1>
				<a href="">MySite</a>
			</h1>

			<!-- 로그인을 하지 안았을때 -->
			<%if(authUser == null) {%>
			<!-- authUser안에 값이 없다면 -->
			<ul>
				<li><a href="">로그인</a></li>
				<li><a href="">회원가입</a></li>
			</ul>
			<%}else{ %>
			<!-- 로그인 했다면 -->
			<ul>
				<li><%=authUser.getName()%>님 안녕하세요^^</li>
				<li><a href="">로그아웃</a></li>
				<li><a href="">회원정보수정</a></li>
			</ul>
			<%} %>
		</div>
		<!-- //header -->

		<div id="nav">
			<ul>
				<li><a href="">방명록</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="">게시판</a></li>
				<li><a href="">입사지원서</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<!-- //nav -->

		<div id="aside">
			<h2>회원</h2>
			<ul>
				<li>회원정보</li>
				<li>로그인</li>
				<li>회원가입</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">
			
			<div id="content-head">
            	<h3>회원가입</h3>
            	<div id="location">
            		<ul>
            			<li>홈</li>
            			<li>회원</li>
            			<li class="last">회원가입</li>
            		</ul>
            	</div>
                <div class="clear"></div>
            </div>
            <!-- //content-head -->

			<div id="user">
				<div id="joinOK">
				
					<p class="text-large bold">
						회원가입을 축하합니다.<br>
						<br>
						<a href="" >[로그인하기]</a>
					</p>
						
				</div>
				<!-- //joinOK -->
			</div>
			<!-- //user -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<div id="footer">
			Copyright ⓒ 2020 황일영. All right reserved
		</div>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

</body>

</html>