package org.iisi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iisi.bean.ViewSub;
import org.iisi.db.JDBCEdit;

import java.util.List;


public class modifyPSEServlet extends HttpServlet {


	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		

		
		JDBCEdit db = new  JDBCEdit();
		
		int i=Integer.parseInt(request.getParameter("pid_n"));
		String pid = (String)session.getAttribute("PID"+i);
		List<ViewSub> editPSe=db.view_sub(pid);
		
		try {
		
			request.setAttribute("PIDs", editPSe);//("值,名")
			session.setAttribute("PID", pid);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("modifyPSE.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	
	}
}