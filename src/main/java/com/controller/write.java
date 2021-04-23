package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model.BoardDAO;
import com.model.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/write")
public class write extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		//===================���� ���ε� ����===========================//
	    String saveDirectory= request.getServletContext().getRealPath("upload");
	    
	    
	    
	    
	    String Directory=request.getContextPath();
	    System.out.println("saveDirectory: "+saveDirectory);
	    System.out.println("Directory: "+Directory);
	    
	    File saveDir=new File(saveDirectory);
	    if(!saveDir.isDirectory()) {
	    	saveDir.mkdirs();}
		
		int maxSize = 1024*1024*5; 
		String encType = "UTF-8"; 
		
	
			//request:��û���� ���� realfolder:���� ���ε��� ��� maxsize:�ѹ��� ���ε��Ҷ� �ִ�ũ�� enctype: ���ڵ� Ÿ��, efaultFileRenamePolicy():�ߺ�����
		MultipartRequest multi=new MultipartRequest(request, saveDirectory, maxSize, encType, new DefaultFileRenamePolicy());


	   		String title=multi.getParameter("title");
	   		String content=multi.getParameter("content");
	   		String id=multi.getParameter("id");
	   		String pw=multi.getParameter("pw");
	   		
	   		File uploadFile = multi.getFile("file");
	   		
	   		String originalFileName = multi.getOriginalFileName("file");//���� ���� �̸�
	   		String filesystemName = multi.getFilesystemName("file"); //���ε� ������ ������ �����ҽ� �ٸ��̸����� ��
	   		
	   		String perfect=saveDirectory+"\\"+filesystemName;
	   		System.out.println("filesystemName: "+filesystemName);
	   		
	   	
	   		BoardDAO dao=new BoardDAO();
	   		int cnt=dao.write(title,content,id,pw,filesystemName,perfect);
	   		
	   		if(cnt>0) {
	   		System.out.println("�۾��� ����");
	   	
	   		}else {
	   			System.out.println("�۾��� ����");
	   		}
		
	        
	      //===================���� ���ε� ��===========================//

	
		
		request.getRequestDispatcher("ListServiceCon").forward(request, response);
	}


}
