<%@ page language="java" contentType="text/html;charset=__CHARSETNAME__" import="java.util.*, javax.servlet.*, javax.servlet.http.*, chipchat.*"
%><%
	request.setCharacterEncoding("__CHARSETNAME__");

	String name=request.getParameter("name");
	String avatar=request.getParameter("avatar");
	String errorMsg=null;

	if ( name==null || name.trim().equals("") ) {
		errorMsg="Wrong name values.";
	} else if ( avatar==null ) {
		errorMsg="Wrong avatar values.";
	} else if ( name.length()>12 ) {
		errorMsg="To long name, length "+name.length()+". Must short than 12.";
	}
	if ( errorMsg!=null ) {
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Chipchat - Login...</title>
<meta http-equiv="Content-Type" content="text/html; charset=__CHARSETNAME__">
<meta http-equiv="refresh" content="20;URL=index.jsp">
</head>
<body bgcolor="white">

<script language="JavaScript" type="text/javascript">
	alert("<%=errorMsg%>");
	history.back();
</script>

</body>
</html>
<%
	} else {
		name = avatar + name;
		session.setAttribute("chipchat_username", StringUtil.htmlSpecialChars(name));
		int id=Math.abs((new Random()).nextInt() % 1000000) + 1;
		session.setAttribute("chipchat_userid",""+id );
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Login...</title>
<meta http-equiv="Content-Type" content="text/html; charset=__CHARSETNAME__">
</head>
<body bgcolor="white">
<p>Logined. Successful. Now going to room automately.</p>
<p>If page doesn't go. Click <a href="channel.jsp">here</a>.</p>

<script language="JavaScript" type="text/javascript">
	document.location.replace("channel.jsp");
</script>

</body>
</html>
<%
	}
%>