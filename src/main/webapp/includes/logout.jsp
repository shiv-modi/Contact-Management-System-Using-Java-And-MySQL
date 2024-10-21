<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<%  HttpSession currsession = request.getSession(false);
	
	if(currsession!=null){
		currsession.invalidate();
	}

//redirect
response.sendRedirect("../index.jsp");

%>
</body>
</html>