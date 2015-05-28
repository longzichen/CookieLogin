<%@page import="java.net.URLDecoder"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
登录页面
<%
	Cookie[] cookies=request.getCookies();
   
    String name="";
    String pwd="";
    for(int i=0;i<cookies.length;i++)
    {
    	if(cookies[i].getName().equals("name"))
    	{
    		name=cookies[i].getValue();
    		name=URLDecoder.decode(name, "utf-8");
    	}
    	if(cookies[i].getName().equals("pwd"))
    	{
    		pwd=cookies[i].getValue();
    	}
    }
    if(name.length()>0)
    {
    	 @SuppressWarnings("unchecked")
    	 HashMap<String,String> map=(HashMap<String,String>)request.getSession().getServletContext().getAttribute("USERS");
    	 if(map.containsKey(name))
    	    {
    	    	String mpwd=map.get(name);
    			if(mpwd.equals(pwd))
    			{
    				request.getSession().setAttribute("NAME", name);
    				response.sendRedirect("welcome.jsp");
    				return;
    			}
    	    }
    }
    
%>
<div id="info"></div>
<form method="post" action="CheckLogin">
姓名:<input type="text" name="userName"><br>
密码:<input type="password" name="pwd"><br>
<input type="submit" id="sub" value="提交">
</form>
</body>
</html>