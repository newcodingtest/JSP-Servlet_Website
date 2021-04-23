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
	
		
		//===================파일 업로드 시작===========================//
	    String saveDirectory= request.getServletContext().getRealPath("upload");
	    
	    
	    
	    
	    String Directory=request.getContextPath();
	    System.out.println("saveDirectory: "+saveDirectory);
	    System.out.println("Directory: "+Directory);
	    
	    File saveDir=new File(saveDirectory);
	    if(!saveDir.isDirectory()) {
	    	saveDir.mkdirs();}
		
		int maxSize = 1024*1024*5; 
		String encType = "UTF-8"; 
		
	
			//request:요청받은 내용 realfolder:실제 업로드할 경로 maxsize:한번에 업로드할때 최대크기 enctype: 인코딩 타입, efaultFileRenamePolicy():중복방지
		MultipartRequest multi=new MultipartRequest(request, saveDirectory, maxSize, encType, new DefaultFileRenamePolicy());


	   		String title=multi.getParameter("title");
	   		String content=multi.getParameter("content");
	   		String id=multi.getParameter("id");
	   		String pw=multi.getParameter("pw");
	   		
	   		File uploadFile = multi.getFile("file");
	   		
	   		String originalFileName = multi.getOriginalFileName("file");//기존 파일 이름
	   		String filesystemName = multi.getFilesystemName("file"); //업로드 파일이 기존에 존재할시 다른이름으로 됨
	   		
	   		String perfect=saveDirectory+"\\"+filesystemName;
	   		System.out.println("filesystemName: "+filesystemName);
	   		
	   	
	   		BoardDAO dao=new BoardDAO();
	   		int cnt=dao.write(title,content,id,pw,filesystemName,perfect);
	   		
	   		if(cnt>0) {
	   		System.out.println("글쓰기 성공");
	   	
	   		}else {
	   			System.out.println("글쓰기 실패");
	   		}
		
	        
	      //===================파일 업로드 끝===========================//

	
		
		request.getRequestDispatcher("ListServiceCon").forward(request, response);
	}


}
