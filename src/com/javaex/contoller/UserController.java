package com.javaex.contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;

@WebServlet("/user")
public class UserController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("usercontroller");
		String action=request.getParameter("action");
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
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
