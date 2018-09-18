<%@ page language="java" import="java.io.*" %>
<%@ page language="java" import="java.lang.*" %>
<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %>
<%
	String userid = (String)(session.getAttribute("userid"));
	System.out.println("*****************************************************");
	if(userid==null){ 
		out.print("No login!");
		System.out.println("没有登录!");
		response.sendError(407, "Need authentication!!!" );
		
	}else{
		out.print("login! userid = " + userid);
		System.out.println("已登录! 当前用户: " + userid);


	/*
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
	   System.out.println(cookie.getName() + " : " + cookie.getValue());
	}
	*/
	

	String filename = request.getParameter("name"); 
	if(filename==null || filename.isEmpty()){
		out.print("请在链接后添加要下载的文件名！");
		System.out.println("请在链接后添加要下载的文件名！");
	}else{
		String realFileName = request.getSession().getServletContext().getRealPath("/") + filename;
		  //得到想客服端输出的输出流  
		 OutputStream outputStream = response.getOutputStream();  
			//输出文件用的字节数组，每次向输出流发送600个字节  
		   byte b[] = new byte[100];  
		  //要下载的文件  
		   File fileload = new File(realFileName);       
		   //客服端使用保存文件的对话框  
		   response.setHeader("Content-disposition", "attachment; filename*=UTF-8''"+filename);  
		   //通知客服文件的MIME类型  
		//response.setContentType("application/msword");  
		response.setContentType("text/html");  
		   //通知客服文件的长度  
		   long fileLength = fileload.length();  
			String length = String.valueOf(fileLength);  
		   response.setHeader("Content-length", length);  
			//读取文件，并发送给客服端下载  
			FileInputStream inputStream = new FileInputStream(fileload);  
		   int n = 0;  
		   while((n=inputStream.read(b))!=-1){  
			   outputStream.write(b,0,n);  
		   }  
		   inputStream.close();
		   outputStream.close();
	}
	}
%>
