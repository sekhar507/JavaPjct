<%@ page language="java" contentType="text/html;charset=__CHARSETNAME__" import="chipchat.*,java.net.URLEncoder"
%><%@include file="channelInfo.jsp"
%><%
String userid_s=(String)session.getAttribute("chipchat_userid");
if ( userid_s==null ) { // Session timeout or not login...
	%><%@include file="sessionerror.jsp"%><%
	return;
}
request.setCharacterEncoding("__CHARSETNAME__");
String chnName=request.getParameter("chnName");
String chnNameDisp=request.getParameter("name");

String channelInfo=
	"chnName="
	+ URLEncoder.encode(chnName,"__CHARSETNAME__")
	+ "&name="
	+ URLEncoder.encode(chnNameDisp,"__CHARSETNAME__");

checkChannel(chnName);
session.setAttribute("chipchat_channel", chnName );

ChipChat instance=ChipChat.getInstance();
Channel channel=instance.getChannel(chnName);
RoomInfo[] rooms=channel.getRoomList();

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Chipchat - You are in<%=chnNameDisp%>channel.</title>
<meta http-equiv="Content-Type" content="text/html; charset=__CHARSETNAME__">
<link href="chipchat.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="white"> 
<table width="90%"  border="0" cellspacing="0" cellpadding="0" background="images/bar.gif" height="50"> 
  <tr> 
    <td height="49"><font color="white"><span style="font-size:20pt;">&nbsp;&nbsp;</span></font><font face="Impact" color="white"><span style="font-size:20pt;">&raquo; Choose or make a room.</span></font></td> 
    <td align="right" height="49"><a href="channel.jsp"><font color="white">Change channel...</font></a><font color="white">&nbsp;&nbsp;</font></td> 
  </tr> 
</table> 
<table width="90%"  border="0" cellspacing="0" cellpadding="5"> 
  <tr> 
    <td align="right"><font size="3">You are in &quot;<%=chnNameDisp%>&quot; channel.</font></td> 
  </tr> 
</table> 
<p>&nbsp;</p> 
<p><font size="+2">&bull; List...</font> </p> 
<font size="+2"><strong> 
<hr width="90%" align="left"> 
</strong></font> 
</p> 
<table width="90%" border="1" cellpadding="10" cellspacing="0" bordercolor="#FFFFFF"> 
  <tr bgcolor="#EBEBEB"> 
    <td width="8%" align="center"><font size="3">No.</font></td> 
    <td width="57%" align="center"><font size="3">Name</font></td> 
    <td width="10%" align="center"><font size="3">People</font></td> 
    <td width="25%" align="center">&nbsp;</td> 
  </tr> 
  <%	for ( int i=0; i<rooms.length; i++ ) {	%> 
  <tr align="center"> 
    <td bgcolor="#EBEBEB"><font size="3"><%=rooms[i].getRoomid()%></font></td> 
    <td bgcolor="#F7F7F7"><font size="3">&nbsp;<%=rooms[i].getName()%></font></td> 
    <td bgcolor="#F7F7F7"><font size="3"><%=rooms[i].getUsers()%>/<%=rooms[i].getMax()%></font></td> 
    <form method="post" action="chat.jsp"> 
      <input type="hidden" name="channelInfo" value="<%=channelInfo%>"> 
      <td bgcolor="#F7F7F7"> <%if ( rooms[i].isPasswd() ) {%> 
        <input name="passwd" type="text" size="12" maxlength="12"> 
        <%}%> 
        <input type="hidden" name="room" value="<%=rooms[i].getRoomid()%>"> 
        <input name="enter" type="submit" id="enter" value="Enter"></td> 
    </form> 
  </tr> 
  <%	}	%> 
</table> 
<P>&nbsp;</P> 
<P>&nbsp;</P> 
<P>&nbsp;</P> 
<P><font size="+2">&bull; New...</font> 
<hr width="90%" align="left"> 
<form name="makeroom" method="post" action="chat.jsp"> 
  <input type="hidden" name="channelInfo" value="<%=channelInfo%>"> 
  <table width="90%" border="1" cellpadding="10" cellspacing="0" bordercolor="#FFFFFF"> 
    <tr> 
      <td width="20%" align="right" bgcolor="#EBEBEB"><font size="3">Name :</font></td> 
      <td width="80%" bgcolor="#F7F7F7"><input name="roomname" type="text" size="50"></td> 
    </tr> 
    <tr> 
      <td align="right" bgcolor="#EBEBEB" width="20%"><font size="3">Members :</font></td> 
      <td bgcolor="#F7F7F7" width="80%"><select name="maxman"> 
          <option value="2">2</option> 
          <option value="3">3</option> 
          <option value="5">5</option> 
          <option value="10" selected>10</option> 
          <option value="20">20</option> 
          <option value="30">30</option> 
          <option value="50">50</option> 
          <option value="100">100</option> 
        </select></td> 
    </tr> 
    <tr> 
      <td align="right" bgcolor="#EBEBEB" width="20%"><font size="3">Password :</font></td> 
      <td bgcolor="#F7F7F7" width="80%"><input type="text" name="passwd"></td> 
    </tr> 
    <tr> 
      <td align="right" width="106">&nbsp;</td> 
      <td width="388"><input name="create" type="submit" id="create" value="Create"></td> 
    </tr> 
  </table> 
</form> 
</body>
</html>
