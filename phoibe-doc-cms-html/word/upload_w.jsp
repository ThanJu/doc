<html>
<head>
<%@ page language="java" import="java.io.*" %>
<%@ page language="java" import="java.lang.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!--<%@ page language="java" contentType="text/html" pageEncoding="utf-8" %> -->
<title>This page for response</title>
</head>
<body>   
<%
	String userid = (String)(session.getAttribute("userid"));
	System.out.println("*****************************************************");
	if(userid==null){ 
		out.print("No login!");
		System.out.println("没有登录!");
	}else{
		System.out.println("已登录! 当前用户: " + userid);
	}


   try {       
		if (request.getContentLength() > 0) 
		{           
			String md5sum = request.getHeader("md5sum");
			if(md5sum != null && !md5sum.isEmpty())
			{
				out.println("md5sum = " + md5sum);
				System.out.println("md5sum = " + md5sum);
			}else{
				out.println("md5sum is empty, please check plugin version!");
				System.out.println("md5sum is empty, please check plugin version!");	
			}
			
			InputStream in = request.getInputStream();
			byte b[] = new byte[4096];
			//固定128字节
			byte filename[] = new byte[128];
			int contentlen;
			int filenamelen;
			String name = "test.....";
			if ((filenamelen = in.read(filename)) != -1)
			{
				int i;
				for (i = 0; i < filename.length && filename[i] != 0; i++) { }
				name = new String(filename, 0, i, "GBK");
			}
			
			String realFileName = request.getSession().getServletContext().getRealPath("/") + name;


			System.out.println("name " + name);
			System.out.println("realFileName " + realFileName);

			File f = new File(realFileName);
			FileOutputStream o = new FileOutputStream(f);
			while ((contentlen = in.read(b)) != -1)
			{                             
				o.write(b, 0, contentlen);
			}
			o.close();
			in.close();
			out.print("File upload success! Username is " + userid);      
			System.out.println("文件上传成功！路径 : " + realFileName );   
		} 
		else
		{
			out.print("No file! Username is " + userid);
			System.out.println("文件上传失败! input stream 长度是 0");
		}
	} 
	catch (IOException e){
		out.print("upload error. Username is " + userid);
		System.out.println("文件上传失败! 发生了异常");
		e.printStackTrace();
	}

%>    
</body>
</html>