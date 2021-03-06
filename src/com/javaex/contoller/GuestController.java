package com.javaex.contoller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;


@WebServlet("/pbc")
public class GuestController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String action = request.getParameter("action");
				System.out.println(action);
				//한글 깨짐 방지용
				request.setCharacterEncoding("UTF-8");
				
				if("list".equals(action)){
					GuestDao gDao = new GuestDao();
					List<GuestVo> gList= gDao.getList();

					request.setAttribute("gList",gList);
					//기존방식
					/*RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/AddList.jsp");
					rd.forward(request, response);*/
					//util방식
					//forword는 요청을 받고 request받는 것이다
					WebUtil.forword(request, response, "/WEB-INF/views/guestbook/AddList.jsp");
				}else if ("add".equals(action)) {

					String name = request.getParameter("name");
					String password = request.getParameter("password");
					String content = request.getParameter("content");
						
					GuestVo guVo =new GuestVo(name,password,content);

					GuestDao guDao = new GuestDao();
					guDao.guestinsert(guVo);
					//response.sendRedirect("/mysite2/pbc?action=list");
					//redirect는 응답을 보내는 것이다.
					WebUtil.redirect(request, response, "/mysite2/pbc?action=list");
				}else if ("deleteForm".equals(action)) {
					/*RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/guestbook/DeleteForm.jsp");
					rd.forward(request, response);*/
					WebUtil.forword(request, response, "/WEB-INF/views/guestbook/DeleteForm.jsp");
				}else if ("delete".equals(action)) {
					int no=Integer.parseInt(request.getParameter("no"));
					String password = request.getParameter("password");
					GuestVo guestVo = new GuestVo(no,password);
					GuestDao guestDao = new GuestDao();		
					int er=guestDao.guestdelete(guestVo);
					if(er==1) {
					//response.sendRedirect("/mysite2/pbc?action=list");
					WebUtil.redirect(request, response,"/mysite2/pbc?action=list");
					}else if (er==0){
						/*RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/passerror.jsp");
						rd.forward(request, response);*/
						WebUtil.forword(request, response, "/WEB-INF/views/guestbook/DeleteForm.jsp");
					}
				}
				
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}