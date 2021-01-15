package com.javaex.contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.util.WebUtil;


@WebServlet("/Board")
public class BoardController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		//데이터가 오는걸 action으로 구분
		System.out.println("actiom"+action);
		if("list".equals(action)) {
			System.out.println("aa");
			WebUtil.forword(request, response, "/WEB-INF/views/board/list.jsp");
		}else if ("write".equals(action)) {
			WebUtil.forword(request, response,"/WEB-INF/views/board/writeForm.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
