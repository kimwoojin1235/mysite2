package com.javaex.contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;
import com.sun.xml.internal.bind.v2.runtime.Name;

@WebServlet("/user")
public class UserController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("usercontroller");
		String action=request.getParameter("action");
		//데이터가 오는걸 action으로 구분
		System.out.println("actiom"+action);
		if("joinForm".equals(action)) {
			System.out.println("회원가입 폼");
			WebUtil.forword(request, response, "/WEB-INF/views/users/JoinForm.jsp");
		}else if("join".equals(action)) {
			System.out.println("회원가입");
			//Dao -->insert 저장
			//파라미터  값 꺼내기
			String id = request.getParameter("uid");
			String password = request.getParameter("pw");
			String name = request.getParameter("uname");
			String gender =request.getParameter("gender");
			//vo로 묶기--.vo만들기-->생성자 추가
			UserVo userVo = new UserVo(id,password,name,gender);	
			System.out.println(userVo.toString());
			//dao클래스에 insert(vo)사용-->저장-->회원가입
			UserDao userDao =new UserDao();
			userDao.insert(userVo);
			//포워드 -->joinok.jsp
			WebUtil.forword(request, response, "/WEB-INF/views/users/JoinOk.jsp");
		}else if ("loginForm".equals(action)) {
			System.out.println("로그인 폼");
			WebUtil.forword(request, response, "/WEB-INF/views/users/LoginForm.jsp");
		}else if ("login".equals(action)) {
			System.out.println("로그인");
			//파라미터
			String id= request.getParameter("id");
			String pw=request.getParameter("pw");
			//dao-->getuser();
			UserDao userDao =new UserDao();//dao안에서 값을 가지고 온다
			UserVo authvo=userDao.getUdes(id, pw);//가지고온 값을 vo안에 넣음
			System.out.println(authvo);//id pw-->no,name
			
			if(authvo ==null) {//로그인 실패
				System.out.println("로그인 실패");
				//리다이렉트
				WebUtil.redirect(request, response, "/mysite2/user?action=loginForm&result=fail");
			}else {//성공일때
				HttpSession session= request.getSession();//구별을 위한 번호를 준다.
				session.setAttribute("authuser",authvo);//로그인을 위해 메인에 값을 보낸다
				//session안에 값이 있는지 없는지 인증위해 보냄
				WebUtil.redirect(request, response, "/mysite2/main");
				//성공일 때만
			}
			
		}else if ("logout".equals(action)) {
			System.out.println("로그아웃");
			//세션영역에 있는 vo를 삭제
			HttpSession session = request.getSession();
			session.removeAttribute("authuser");
			//authuser라는 이름의 세션이 있는 어트리뷰트를 지운다.
			session.invalidate();
			WebUtil.redirect(request, response, "/mysite2/main");
		}else if ("modifyForm".equals(action)) {
			System.out.println("회원정보 수정폼");
			//세션에 있는 no값을 불러온다.
			HttpSession session = request.getSession();
			//로그할때 사용하는 authuser을 이용하여 불러온다
			UserVo authuser = (UserVo)session.getAttribute("authuser");
			int no = authuser.getNo();
			//회원정보 가지고 오기
			UserDao userDao = new UserDao();
			UserVo uvo =userDao.getUser(no);
			//로그인한 유저의 no를 불러온다.
			//uvo전달포워드
			request.setAttribute("userVo", uvo);
			//포워드
			WebUtil.forword(request, response, "/WEB-INF/views/users/ModifyForm.jsp");
		}else if ("modify".equals(action)) {
			System.out.println("actiom"+action);
			System.out.println("회원정보 수정");	
			String password = request.getParameter("pw");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			HttpSession session =request.getSession();
			UserVo authUser = (UserVo)session.getAttribute("authuser");
			int no = authUser.getNo();
			
			UserVo userVo = new UserVo(no, password, name, gender);
			System.out.println(userVo);
			//db의 정보를 수정한 걸로 바꾼다.
			UserDao userDao = new UserDao();
			userDao.userupdate(userVo);
			//session의 네임값만 변경을 한다
			authUser.setName(name);
			WebUtil.redirect(request, response, "/mysite2/main");
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
