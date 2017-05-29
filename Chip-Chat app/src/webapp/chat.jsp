<%@ page language="java" contentType="text/html;charset=__CHARSETNAME__" import="chipchat.*"
%><%!
	java.util.Random rand=new java.util.Random();	
%><%
request.setCharacterEncoding("__CHARSETNAME__");

String username=(String)session.getAttribute("chipchat_username");
String userid_s=(String)session.getAttribute("chipchat_userid");
String channel=(String)session.getAttribute("chipchat_channel");

if ( userid_s==null ) { // Session timeout or not login...
	%><%@include file="sessionerror.jsp"%><%
	return;
}

int userid=Integer.parseInt(userid_s);

ConnectionInfo info=new ConnectionInfo();

info.setUserid(userid);
info.setName(username);
info.setChannel(channel);

String channelInfo=request.getParameter("channelInfo");

String roomName=request.getParameter("roomname");

if ( roomName!=null ) { // Create Room...
	int maxMan=Integer.parseInt( request.getParameter("maxman") );
	String passwd=request.getParameter("passwd");

	Long roomid=ChipChat.getInstance().getChannel(channel).makeRoom( roomName, maxMan, passwd, userid );

	info.setRoomid(roomid);
	info.setRoompw(passwd);
} else {
	info.setRoomid(new Long(Long.parseLong(request.getParameter("room"))));
	info.setRoompw(request.getParameter("passwd"));
}

int inum=rand.nextInt(10000);

ConnectionWaiter.getInstance().put( request.getRemoteAddr() +  inum, info );

%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=__CHARSETNAME__">
<title>Chipchat - Chating...</title>
<link href="chipchat.css" rel="stylesheet" type="text/css">
</head>
<script language="javascript" src="chipchat.js"></script>
<script language="javascript" src="members.js"></script>
<script language="javascript" src="input.js"></script>
<script language="javascript" src="view.js"></script>
<script language="javascript">
var forwardUrl="rooms.jsp?<%=channelInfo%>";

function onLoadFunc() {
	try {
		viewInit();
		inputInit();
		membersInit();
		chipchatInit();
		resizeLayers();
	} catch (e) {
		alert( "Fail for initializing : " + e );
	}
}

function onResizeFunc() {
	resizeLayers();
}
</script>

<body bgcolor="white" text="black" link="blue" vlink="purple" alink="red" leftmargin="0" marginwidth="0" topmargin="0" marginheight="0" onLoad="onLoadFunc();" onResize="onResizeFunc();">
<div id="tempLayer" style="position:absolute; left:0px; top:0px; z-index:10; visibility:hidden; display:none;">
  <table border="0" height="100%" width="100%">
    <tr>
      <td align="right" valign="bottom" id="tempLayerTd"><table width="500" height="100"  border="1" cellpadding="5" cellspacing="0" bordercolor="#000000" bgcolor="#FFFFFF">
          <tr>
            <td bgcolor="#6666AA">&nbsp;</td>
          </tr>
          <tr>
            <td align="center"><font size="4">&nbsp;<br>
              Connecting...<br>&nbsp; </font></td>
          </tr>
          <tr>
            <td bgcolor="#6666AA">&nbsp;</td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</div>
<div id="msgLayer" style="position:absolute; left:1px; top:1px; width:10px; height:10px; z-index:3; overflow:auto; background-color: #FFFFFF">
  <table cellpadding="10" width="100%">
    <tr>
      <td id="msgLayerTd">&nbsp;</td>
    </tr>
  </table>
</div>
<div id="membersLayer" style="position:absolute; left=100px; top=0px; width:200px; height:100px; z-index:3; overflow:auto; background-color:#FFFFFF">
  <table width="100%" height="100%"  border="1" cellpadding="8" cellspacing="0" bordercolor="#000000">
    <tr height="30">
      <td bgcolor="#6666AA"><strong><font color="#FFFFFF" size="+1">Members</font></strong></td>
    </tr>
    <tr>
      <td valign="top"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="top" id="membersTd">&nbsp;</td>
          </tr>
          <tr>
            <td valign="bottom"><table width="100%"  border="0" cellpadding="5" cellspacing="0" bgcolor="#6666AA">
                <tr>
                  <td><font color="#FFFFFF">Help</font></td>
                </tr>
              </table>
              <table id="helpMsgTable" width="100%"  border="0" cellpadding="0" cellspacing="3" bgcolor="#FFFFFF" style="visibility:hidden; display:none;">
                <tr>
                  <td width="10%">W</td>
                  <td width="5%">:</td>
                  <td width="85%">Wisper</td>
                </tr>
              </table>
              <table id="adminHelpMsgTable" width="100%"  border="0" cellpadding="0" cellspacing="3" bgcolor="#FFFFFF" style="visibility:hidden; display:none;">
                <tr>
                  <td width="10%">W</td>
                  <td width="5%">:</td>
                  <td width="85%">Wisper</td>
                </tr>
                <tr>
                  <td width="10%">Q</td>
                  <td width="5%">:</td>
                  <td width="85%">Be quiet!</td>
                </tr>
                <tr>
                  <td width="10%">E</td>
                  <td width="5%">:</td>
                  <td width="85%">Entrust</td>
                </tr>
                <tr>
                  <td width="10%">X</td>
                  <td width="5%">:</td>
                  <td width="85%">Driven out</td>
                </tr>
                <tr align="center">
                  <td colspan="3"><a href="#" onClick="parent.administrate(); return false;">Administrate</a></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</div>
<table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#000000">
  <tr>
    <td width="90%"><applet code="chipchatapplet.ChipChatApplet.class" codebase="." name="chipchat_applet" width="5" height="5" hspace="0" vspace="0" id="chipchat_applet" MAYSCRIPT>
        <param name="inum" value="<%=inum%>">
        <param name="keepsession" value="9">
        <%
		if ( Server.isUseServer() ) {
		%>
        <param name="port" value="<%=Server.getServerPort()%>">
        <%
		}
		%>
      </applet>
    </td>
    <td width="10%">&nbsp;</td>
  </tr>
  <tr height="100">
    <td width="100%" colspan="2" valign="top"><table height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
        <tr valign="bottom">
          <td colspan="2"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td id="toTd">To all...</td>
                <td align="right"><a href="#" onClick="setDeco(1); return false;">Bold</a> <a href="#" onClick="setDeco(2); return false;">Underline</a> <a href="#" onClick="setDeco(3); return false;">Italic</a> <a href="#" onClick="changeTextColor(); return false;">Color</a> <a href="#" onClick="showImotTable(); return false;">Imoticom</a> <a href="#" onClick="cleanMessage(); return false;">Clean</a> </td>
              </tr>
            </table></td>
        </tr>
        <tr valign="top">
          <form name="MsgSendForm" method="post" action="#" onSubmit="sendMsg(); return false;">
            <input type="hidden" name="to" value="-1">
            <td width="36" rowspan="2"><table border="1" cellpadding="2" cellspacing="0" bordercolor="#E3E3E3">
                <tr>
                  <td id="decoTextTd"><font size="+3">A</font></td>
                </tr>
              </table></td>
            <td><input name="inputtext" type="text" size="60">
              <input name="send" type="button" id="send" value="Send" onClick="sendMsg();"></td>
          </form>
        </tr>
        <tr align="right">
          <td valign="top"><a href="#" onClick="getOut(); return false;">Logout</a></td>
        </tr>
      </table></td>
  </tr>
</table>
</body>
</html>
