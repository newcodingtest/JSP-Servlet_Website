package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.BoardDAO;
import com.model.BoardDTO;

/**
 * Servlet implementation class DetailServiceCon
 */
@WebServlet("/DetailServiceCon")
public class DetailServiceCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int comid=Integer.parseInt(request.getParameter("comid"));
		
		BoardDAO dao=new BoardDAO();
		
		BoardDTO dto=dao.detail(comid);
		
		System.out.println("상세보기 테스트"+dto);
		
		dao.increase(comid);
		
		request.setAttribute("n", dto);
		
		request.getRequestDispatcher("Board_View/Detail.jsp").forward(request, response);
		
	}

}
