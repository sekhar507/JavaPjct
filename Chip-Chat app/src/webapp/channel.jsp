<%@ page language="java" contentType="text/html;charset=__CHARSETNAME__" import="chipchat.*,java.net.URLEncoder,java.io.UnsupportedEncodingException"
%><%@include file="channelInfo.jsp"
%><%!
	String getRoomLink(String name, String chnName) throws JspException, UnsupportedEncodingException {
		checkChannel(chnName);
		return "<a href='rooms.jsp?chnName="
			+URLEncoder.encode(chnName,"__CHARSETNAME__")
			+"&name="
			+URLEncoder.encode(name,"__CHARSETNAME__")
			+"'>"+name
			+" ("
			+ChipChat.getInstance().getChannel( chnName ).getRoomList().length
			+")</a>";
	}
%><%

String userid_s=(String)session.getAttribute("chipchat_userid");
if ( userid_s==null ) { // Session timeout or not login...
	%><%@include file="sessionerror.jsp"%><%
	return;
}

%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Chipchat - You can select channel.</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<link href="chipchat.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="white">
<table width="90%"  border="0" cellspacing="0" cellpadding="0" background="images/bar.gif" height="50">
  <tr>
        <td height="49" width="794"><font color="white"><span style="font-size:20pt;">&nbsp;&nbsp;</span></font><font face="Impact" color="white"><span style="font-size:20pt;">&raquo; Choose Channel.</span></font></td>
        <td align="right" height="49" width="126"><a href="logout.jsp"><font color="white">Logout...</font></a><font color="white">&nbsp;&nbsp;</font></td>
  </tr>
</table>
<p>&nbsp;</p>
<p><font size="+2">&bull; By the subject.</font></p>
<ul>
  <li><%=getRoomLink("Movie/Video","Movie/Video")%></li>
  <li><%=getRoomLink("Sports/Leisure","Sports/Leisure")%></li>
  <li><%=getRoomLink("Computer/Internet","Computer/Internet")%></li>
  <li><%=getRoomLink("Game","Game")%></li>
  <li><%=getRoomLink("Humor","Humor")%></li>
  <li><%=getRoomLink("Entertainment","Entertainment")%></li>
  <li><%=getRoomLink("Etc","Etc")%></li>
</ul>
<P><font size="+2">&bull; By the age.</font></P>
<ul>
  <li><%=getRoomLink("Anybody","Anybody")%></li>
  <li><%=getRoomLink("Teenagers","Teenagers")%></li>
  <li><%=getRoomLink("Twenties","Twenties")%></li>
  <li><%=getRoomLink("Thirties","Thirties")%></li>
  <li><%=getRoomLink("Forties","Forties")%></li>
  <li><%=getRoomLink("Fifties","Fifties")%></li>
</ul>
<P>&nbsp;</P>
<P>&nbsp;</P>
<P>&nbsp;</P>
</body>
</html>
