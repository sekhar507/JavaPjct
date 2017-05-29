var memberListTemp="&nbsp;";
var memberList=new Array();
var userid=0;
var hostid=-1;
var username;

var roomName;
var roomMax;
var roomPasswd;

var chipchat_applet;

function chipchatInit() {
	chipchat_applet=document.applets['chipchat_applet'];
	if (chipchat_applet==null)
		throw "Can not find [chipchat_applet].";
	setTimeout( "chipchat_applet.connect()", 100 );
}

/////////////////////////////////////////////////////////////////////////////
// Normal Message - Write, Read.
/////////////////////////////////////////////////////////////////////////////
var msgColor="#F5F5F5";

function chipchat_printMsg( writer, msg ) {
	var realMsg=decodeMsg(msg);
	writeMessage('<table width="100%" border="0" cellpadding="1" cellspacing="1"><tr><td width="130" bgcolor="#000000"><font color="#FFFFFF">'+ parseID(writer) +'</font></td><td width="*" bgcolor="'+msgColor+'">'+realMsg+'</td></tr></table>');
	scrollMessage();
	if ( msgColor=="#F5F5F5" )
		msgColor="#FFFFFF";
	else 
		msgColor="#F5F5F5";
}

function chipchat_inputMsg( to, msg, fontcolor, b, u, i ) {
	chipchat_applet.sendMsg( to, encodeMsg(msg,fontcolor, b, u, i ) );
}
/////////////////////////////////////////////////////////////////////////////
// Message Encode, Decode.
/////////////////////////////////////////////////////////////////////////////
function decodeMsg(msg) {
	var msg2=""+msg;
	var fontcolor=msg2.substr( 3, 6 );
	var realMsg=msg2.substr( 9 );
	realMsg=msgReplace(realMsg);
	if ( msg2.substr(0,1)=="b" )
		realMsg="<b>" + realMsg + "</b>";
	if ( msg2.substr(1,1)=="u" )
		realMsg="<u>" + realMsg + "</u>";
	if ( msg2.substr(2,1)=="i" )
		realMsg="<i>" + realMsg + "</i>";
	realMsg="<font color=#" + fontcolor + ">" + realMsg + "</font>";
	return realMsg;
}

function encodeMsg(msg,fontcolor, b, u, i ) {
	var tmp="";
	if ( b )
		tmp+="b";
	else
		tmp+="_";
	if ( u )
		tmp+="u";
	else
		tmp+="_";
	if ( i )
		tmp+="i";
	else
		tmp+="_";
	tmp+=fontcolor.substr(1,6);
	tmp+=msg;
	return tmp;
}

/////////////////////////////////////////////////////////////////////////////
// Whisper Message...
/////////////////////////////////////////////////////////////////////////////
function chipchat_output_wspsnd( reader, msg ) {
	var realMsg=decodeMsg(msg);
	writeMessage('<table width="100%" border="0" cellpadding="1" cellspacing="1"><tr><td width="130" bgcolor="#000066"><font color="#FFFFFF">'+ parseID(reader) +'</font></td><td width="*" bgcolor="'+msgColor+'">'+realMsg+'</td></tr></table>');
	scrollMessage();
	if ( msgColor=="#F5F5F5" )
		msgColor="#FFFFFF";
	else 
		msgColor="#F5F5F5";
}
function chipchat_output_wsprcv( writer, msg ) {
	var realMsg=decodeMsg(msg);
	writeMessage('<table width="100%" border="0" cellpadding="1" cellspacing="1"><tr><td width="130" bgcolor="#660000"><font color="#FFFFFF">'+ parseID(writer) +'</font></td><td width="*" bgcolor="'+msgColor+'">'+realMsg+'</td></tr></table>');
	scrollMessage();
	if ( msgColor=="#F5F5F5" )
		msgColor="#FFFFFF";
	else 
		msgColor="#F5F5F5";
}

/////////////////////////////////////////////////////////////////////////////
// Error Message.
/////////////////////////////////////////////////////////////////////////////
function chipchat_error( error ) {
	if ( error=="SessionNotExist" || error=="WrongSessionValue" ) {
		alert("System Error...");
		location="index.jsp";
	} else if ( error=="RoomIsFull" ) {
		alert("Room is full..");
		chipchat_getout_forward();
	} else if ( error=="PasswordNotMatch" ) {
		alert("Password does not correct.");
		chipchat_getout_forward();
	} else if ( error=="Unknown" || error=="" ) {
		alert("ERROR:Unknown.");
	} else if ( error=="WSP:SendToMyself" ) {
		alert("You can not whisper to yourself.");
		talkToAll();
	} else if ( error=="WSP:NotExistAnother" ) {
		alert("Member is not exist.");
		talkToAll();
	} else {
		alert( error );
	}
}

/////////////////////////////////////////////////////////////////////////////
// Join or left.
/////////////////////////////////////////////////////////////////////////////
function chipchat_getout( who ) {
	writeMessage(  parseID(who)+" has left.<BR>");
	scrollMessage();
}
function chipchat_getin( id, name ) {
	if ( id != userid ) {
		writeMessage(  parseID(name)+" has joined.<BR>");
		scrollMessage();
	}
}
/////////////////////////////////////////////////////////////////////////////
// Member list.
/////////////////////////////////////////////////////////////////////////////
function chipchat_userlistStart() {
	memberList.length=0;
}
function chipchat_userlistAdd( id, name ) {
	memberList.push( id );
	memberList.push( name );
}
function chipchat_userlistEnd() {
	makeMemberList();
	applyMemberList();
}
function makeMemberList() {
	memberListTemp="";
	for ( var i=0; i<memberList.length; i+=2 )
	{
		var id=parseInt(memberList[i]);
		var name=memberList[i+1];
		if (i>0) {
			memberListTemp+='<hr width="100%" noshade/>';
		}
		memberListTemp+='<table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF"><tr>';
		if ( id==userid ) {
			memberListTemp+='<td width="75%"><p>'+parseID(name)+'</p></td>';
		} else {
			memberListTemp+='<td width="75%"><p><A href="#" onClick="parent.talkTo('+id+',\''+stripID(name)+'\'); return false;">'+parseID(name)+'</A></p></td>';
		}
		if ( id==userid ) {
			memberListTemp+='<td width="25%" align="right">&lt;= ME</td>';
		} else {
			memberListTemp+='<td width="25%" align="right">'
			memberListTemp+='<table border="1" cellpadding="0" cellspacing="0" bordercolor="#6666AA" bgcolor="#6666AA">';
            memberListTemp+='<tr>';
			if (userid==hostid) {
				memberListTemp+='<td><A href="#" onClick="parent.talkTo('+id+',\''+stripID(name)+'\'); return false;"><font color="#FFFFFF">W</font></A></td>';
                memberListTemp+='<td width="1" bgcolor="#FFFFFF"></td>';
				memberListTemp+='<td><A href="#" onClick="parent.keepQuiet('+id+'); return false;"><font color="#FFFFFF">Q</font></A></td>';
                memberListTemp+='<td width="1" bgcolor="#FFFFFF"></td>';
				memberListTemp+='<td><A href="#" onClick="parent.entrust('+id+'); return false;"><font color="#FFFFFF">E</font></A></td>';
                memberListTemp+='<td width="1" bgcolor="#FFFFFF"></td>';
				memberListTemp+='<td><A href="#" onClick="parent.kickOut('+id+'); return false;"><font color="#FFFFFF">X</font></A></td>';
			} else {
				memberListTemp+='<td><A href="#" onClick="parent.talkTo('+id+',\''+stripID(name)+'\'); return false;"><font color="#FFFFFF">W</font></A></td>';
			}
			memberListTemp+='</tr></table></td>';
		}
		memberListTemp+='</tr></table>';
	}
}
function applyMemberList() {
	updateMemberList(memberListTemp);
	showHelpMsg( userid==hostid );
}

/////////////////////////////////////////////////////////////////////////////
// Connection message.
/////////////////////////////////////////////////////////////////////////////
function chipchat_connected( servername ) {
	writeMessage( "Connected to server... " + servername + "<BR>");
	hideConnecting();
	scrollMessage();
}
function chipchat_connectionBroken() {
	alert("Your connection is broken.");
	chipchat_getout_forward();
}
function chipchat_notconnected() {
	alert( "Not connected." );
}
/////////////////////////////////////////////////////////////////////////////
// Forward
/////////////////////////////////////////////////////////////////////////////
function chipchat_getout_forward() {
	location=forwardUrl;
}
/////////////////////////////////////////////////////////////////////////////
// Setter of id, name
/////////////////////////////////////////////////////////////////////////////
function chipchat_setConnectID( id ) {
	userid=parseInt(id);
}

function chipchat_setConnectName( name ) {
	username=name;
}

function chipchat_RoomInfo( name, max, passwd ) {
	roomName=name;
	roomMax=max;
	roomPasswd=passwd;
}
/////////////////////////////////////////////////////////////////////////////
// About administration.
/////////////////////////////////////////////////////////////////////////////
function chipchat_kickOut( who, id ) {
	if ( userid==id ) {
		alert("The host has driven out you.");
		chipchat_getout_forward();
	} else {
		writeMessage( "The host has driven out "+parseID(who)+".<BR>");
		scrollMessage();
	}
}
function chipchat_keepQuit( who, id ) {
	if ( userid==id ) {
		writeMessage( "The host has made me quiet.<BR>");
		alert("Keep quiet");
		scrollMessage();
	} else {
		writeMessage( "The host has made "+parseID(who)+" quiet.<BR>");
		scrollMessage();
	}
}
function chipchat_beQuit( remain ) {
	alert("You can't speak yet, "+remain+" second(s) left.");
}
function chipchat_passwdChanged( passwd ) {
	writeMessage( "Password has changed.<BR>");
}
function chipchat_RoomnameChanged( roomName ) {
	writeMessage( "The name of this room has changed into "+roomName+".<BR>" );
}
function chipchat_MaxmanChanged( number ) {
	writeMessage( "The maximum number of attendants has changed into "+number+".<BR>" );
}
/////////////////////////////////////////////////////////////////////////////
// Administration info.
/////////////////////////////////////////////////////////////////////////////
function chipchat_setAdmin( id ) {
	hostid=id;
}

function chipchat_adminChange( who ) {
	writeMessage( "The host has changed into "+parseID(who)+".<BR>");
	scrollMessage();

	makeMemberList();
	applyMemberList();
}
/////////////////////////////////////////////////////////////////////////////
// Keyword symbols 
/////////////////////////////////////////////////////////////////////////////
var replaceCharators=new Array(
	new RegExp("/\\^\\^\\;/","g"),		"<img src='imoticon/17.gif' border='0' align='absmiddle'>",
	new RegExp("/\\^\\^/","g"),		"<img src='imoticon/18.gif' border='0' align='absmiddle'>",
	new RegExp("/-_-\\!/","g"),		"<img src='imoticon/20.gif' border='0' align='absmiddle'>",
	new RegExp("/:\\)/","g"),		"<img src='imoticon/25.gif' border='0' align='absmiddle'>",
	new RegExp("/\\>\\</","g"),		"<img src='imoticon/29.gif' border='0' align='absmiddle'>",
	new RegExp("/\\@\\_\\@/","g"),		"<img src='imoticon/30.gif' border='0' align='absmiddle'>",
	new RegExp("/--\\^/","g"),		"<img src='imoticon/31.gif' border='0' align='absmiddle'>",
	new RegExp("/\\:D/","g"),		"<img src='imoticon/32.gif' border='0' align='absmiddle'>",
	new RegExp("/\\:\\>/","g"),		"<img src='imoticon/33.gif' border='0' align='absmiddle'>",
	new RegExp("/S2/","g"),		"<img src='imoticon/35.gif' border='0' align='absmiddle'>",
	new RegExp("/TT/","g"),		"<img src='imoticon/57.gif' border='0' align='absmiddle'>",
	new RegExp("/\\-\\-/","g"),		"<img src='imoticon/60.gif' border='0' align='absmiddle'>",
	new RegExp("/\\-\\-\\?/","g"),		"<img src='imoticon/71.gif' border='0' align='absmiddle'>",
	new RegExp("/\\@\\@/","g"),		"<img src='imoticon/96.gif' border='0' align='absmiddle'>",
	new RegExp("/o\\*\\*O/","g"),		"<img src='imoticon/64.gif' border='0' align='absmiddle'>",

	new RegExp("/15/","g"),		"<img src='imoticon/15.gif' border='0' align='absmiddle'>",
	new RegExp("/16/","g"),		"<img src='imoticon/16.gif' border='0' align='absmiddle'>",
	new RegExp("/19/","g"),		"<img src='imoticon/19.gif' border='0' align='absmiddle'>",
	new RegExp("/21/","g"),		"<img src='imoticon/21.gif' border='0' align='absmiddle'>",
	new RegExp("/22/","g"),		"<img src='imoticon/22.gif' border='0' align='absmiddle'>",
	new RegExp("/23/","g"),		"<img src='imoticon/23.gif' border='0' align='absmiddle'>",
	new RegExp("/24/","g"),		"<img src='imoticon/24.gif' border='0' align='absmiddle'>",
	new RegExp("/26/","g"),		"<img src='imoticon/26.gif' border='0' align='absmiddle'>",
	new RegExp("/27/","g"),		"<img src='imoticon/27.gif' border='0' align='absmiddle'>",
	new RegExp("/28/","g"),		"<img src='imoticon/28.gif' border='0' align='absmiddle'>",
	new RegExp("/34/","g"),		"<img src='imoticon/34.gif' border='0' align='absmiddle'>",
	new RegExp("/36/","g"),		"<img src='imoticon/36.gif' border='0' align='absmiddle'>",
	new RegExp("/37/","g"),		"<img src='imoticon/37.gif' border='0' align='absmiddle'>",
	new RegExp("/53/","g"),		"<img src='imoticon/53.gif' border='0' align='absmiddle'>",
	new RegExp("/54/","g"),		"<img src='imoticon/54.gif' border='0' align='absmiddle'>",
	new RegExp("/55/","g"),		"<img src='imoticon/55.gif' border='0' align='absmiddle'>",
	new RegExp("/56/","g"),		"<img src='imoticon/56.gif' border='0' align='absmiddle'>",
	new RegExp("/58/","g"),		"<img src='imoticon/58.gif' border='0' align='absmiddle'>",
	new RegExp("/59/","g"),		"<img src='imoticon/59.gif' border='0' align='absmiddle'>",
	new RegExp("/61/","g"),		"<img src='imoticon/61.gif' border='0' align='absmiddle'>",
	new RegExp("/62/","g"),		"<img src='imoticon/62.gif' border='0' align='absmiddle'>",
	new RegExp("/63/","g"),		"<img src='imoticon/63.gif' border='0' align='absmiddle'>",
	new RegExp("/65/","g"),		"<img src='imoticon/65.gif' border='0' align='absmiddle'>",
	new RegExp("/66/","g"),		"<img src='imoticon/66.gif' border='0' align='absmiddle'>",
	new RegExp("/67/","g"),		"<img src='imoticon/67.gif' border='0' align='absmiddle'>",
	new RegExp("/68/","g"),		"<img src='imoticon/68.gif' border='0' align='absmiddle'>",
	new RegExp("/69/","g"),		"<img src='imoticon/69.gif' border='0' align='absmiddle'>",
	new RegExp("/70/","g"),		"<img src='imoticon/70.gif' border='0' align='absmiddle'>",
	new RegExp("/72/","g"),		"<img src='imoticon/72.gif' border='0' align='absmiddle'>",
	new RegExp("/73/","g"),		"<img src='imoticon/73.gif' border='0' align='absmiddle'>",
	new RegExp("/74/","g"),		"<img src='imoticon/74.gif' border='0' align='absmiddle'>",
	new RegExp("/76/","g"),		"<img src='imoticon/76.gif' border='0' align='absmiddle'>",
	new RegExp("/78/","g"),		"<img src='imoticon/78.gif' border='0' align='absmiddle'>",
	new RegExp("/79/","g"),		"<img src='imoticon/79.gif' border='0' align='absmiddle'>",
	new RegExp("/80/","g"),		"<img src='imoticon/80.gif' border='0' align='absmiddle'>",
	new RegExp("/81/","g"),		"<img src='imoticon/81.gif' border='0' align='absmiddle'>",
	new RegExp("/84/","g"),		"<img src='imoticon/84.gif' border='0' align='absmiddle'>",
	new RegExp("/85/","g"),		"<img src='imoticon/85.gif' border='0' align='absmiddle'>",
	new RegExp("/97/","g"),		"<img src='imoticon/97.gif' border='0' align='absmiddle'>",
	new RegExp("/122/","g"),		"<img src='imoticon/122.gif' border='0' align='absmiddle'>",
	new RegExp("/123/","g"),		"<img src='imoticon/123.gif' border='0' align='absmiddle'>",
	new RegExp("/125/","g"),		"<img src='imoticon/125.gif' border='0' align='absmiddle'>",
	new RegExp("/126/","g"),		"<img src='imoticon/126.gif' border='0' align='absmiddle'>",
	new RegExp("/127/","g"),		"<img src='imoticon/127.gif' border='0' align='absmiddle'>",
	new RegExp("/129/","g"),		"<img src='imoticon/129.gif' border='0' align='absmiddle'>"
);

function msgReplace( msg ) {
	for ( var i=0; i<replaceCharators.length; i+=2 ) {
		msg=msg.replace( replaceCharators[i], replaceCharators[i+1] );
	}
	return msg;
}
/////////////////////////////////////////////////////////////////////////////
// About id.
/////////////////////////////////////////////////////////////////////////////
function parseID( id ) {
	if ( id==null )
		return null;
	var id2=""+id;
	var avatar=id2.substr( 0, 2 );
	var realID=id2.substr( 2 );
	return ( '<img src="avatar/' + avatar + '.gif" border="0" align="absmiddle" width="20" height="20"> ' + realID );
}

function stripID( id ) {
	return (""+id).substr( 2 );
}

/////////////////////////////////////////////////////////////////////////////
// Custom message.
/////////////////////////////////////////////////////////////////////////////
function chipchat_custom( who, cmd, msg ) {

}

function chipchat_inputCustomMsg( to, cmd, msg ) {
	chipchat_applet.sendCustomMsg( to, cmd, msg );
}
