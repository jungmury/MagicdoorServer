<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	String value = (String)request.getAttribute("status");
%>
<h1>Close Account Result</h1>
Close Account status : <%=value %><br><br>
<input type="button" value="Go to the index page" align="middle" onclick="location.href='../index.jsp';">


</body>
</html>