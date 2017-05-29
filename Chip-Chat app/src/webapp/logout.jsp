<%@ page language="java" contentType="text/html;charset=__CHARSETNAME__" import="java.util.*, javax.servlet.*, javax.servlet.http.*"
%><%
	session.setAttribute("chipchat_username", null);
	session.setAttribute("chipchat_userid", null);
	session.invalidate();
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Chipchat - Logout..</title>
<meta http-equiv="Content-Type" content="text/html; charset=__CHARSETNAME__">
</head>
<body bgcolor="white">
<table width="95%" height="95%" border="0" align="center">
	<tr>
		<td align="center" valign="middle"> 
			<p>Success....</p>
			<p><a href="index.jsp">Login...</a></p>
</td>
	</tr>
</table>
</body>
</html>
