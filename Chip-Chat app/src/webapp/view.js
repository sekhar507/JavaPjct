var tempLayer;
var tempLayerTd;

function viewInit() {
	tempLayer=document.getElementById ? document.getElementById("tempLayer") : document.all ? document.all.tempLayer : null;
	tempLayerTd=document.getElementById ? document.getElementById("tempLayerTd") : document.all ? document.all.tempLayerTd : null;
	if (tempLayer==null)
		throw "Can not find [tempLayer].";
	if (tempLayerTd==null)
		throw "Can not find [tempLayerTd].";

	initMsgLayer();
	initColorTable();
	initAdministrateTable();
	initLayersName();
	showConnecting();
}

//
// Connecting Message....
//
function showConnecting() {
	tempLayer.style.width=document.body.clientWidth/2+250;
	tempLayer.style.height=document.body.clientHeight/2+50;
	tempLayer.style.visibility="visible";
	tempLayer.style.display="";
}

function hideConnecting() {
	tempLayer.style.visibility="hidden";
	tempLayer.style.display="none";
}

//
// Message Layer
//
var msgLayerTd;
var msgData="";

function initMsgLayer() {
	msgLayerTd=document.getElementById ? document.getElementById("msgLayerTd") : document.all ? document.all.msgLayerTd : null;
	if (msgLayerTd==null)
		throw "Can not find [msgLayerTd].";
}

function cleanMessage() {
	msgLayerTd.innerHTML="&nbsp;";
	msgData="";
}

function writeMessage( msg ) {
	msgData+=msg;
	msgLayerTd.innerHTML=msgData;
}

function scrollMessage() {
	setTimeout( "msgLayer.scrollTop=msgLayer.scrollHeight-msgLayerHeight;", 100 );
}

//
// Color table.
//
var colorTable;

function initColorTable() {
	colorTable="";
	var Colors = new Array( "00", "33", "66", "99", "CC", "FF" );

	colorTable='<table border="1" cellpadding="2" cellspacing="0" bordercolor="#000000" bgcolor="#FFFFFF">';
	colorTable+='<tr> ';
	colorTable+='<td bgcolor="#6666AA"><table width="100%"  border="0" cellspacing="0" cellpadding="0"> ';
	colorTable+='<tr> ';
	colorTable+='<td><font color="#FFFFFF">Choose color.</font></td> ';
	colorTable+='<td align="right"><a href="#" onClick="closeTextColor(); return false;"><img src="images/icon-x.gif" width="19" height="19" border="0" align="absmiddle"></a></td> ';
	colorTable+='</tr> ';
	colorTable+='</table></td> ';
	colorTable+='</tr> ';
	colorTable+='<tr><td>';
	colorTable+='<table border="0" cellpadding="0" cellspacing="0">';

	for (var l = 0; l < 2; l++) {
		for (var i = 0; i < Colors.length; i++) {
			colorTable+="<tr>";
			for (var j = 0; j < (Colors.length / 2); j++) {
				for (var k = 0; k < Colors.length; k++) {
					var color='#' + Colors[j + (l * (Colors.length) / 2)] + Colors[k] + Colors[i];
					colorTable+='<td bgcolor="'+color+'"><a href="#" onClick=\'changeColor("'+color+'"); closeTextColor(); return false;\'><img src="images/transparent.gif" border="0" width="10" height="10"></a></td>';
				}
			}
			colorTable+='</tr>';
		}
	}
	colorTable+='</table>';
	colorTable+='</td></tr></table>';
}

function changeTextColor() {
	tempLayerTd.innerHTML=colorTable;
	tempLayer.style.width=document.body.clientWidth/2+230;
	tempLayer.style.height=document.body.clientHeight-100;
	tempLayer.style.visibility="visible";
	tempLayer.style.display="";
}

function closeTextColor() {
	tempLayer.style.visibility="hidden";
	tempLayer.style.display="none";
}

//
// Imoticon table.
//

var imotPage=0;
var imotTable="";

function moveImotPage( num ) {
	imotPage+=num;
	imotTable="";
	showImotTable();
}

function makeImotTable() {

	imotTable='<table border="1" cellpadding="2" cellspacing="0" bordercolor="#000000" bgcolor="#FFFFFF">';
	imotTable+='<tr> ';
	imotTable+='<td bgcolor="#6666AA"><table width="100%"  border="0" cellspacing="0" cellpadding="0"> ';
	imotTable+='<tr> ';
	imotTable+='<td><font color="#FFFFFF">Choose imoticon.</font></td> ';
	imotTable+='<td align="right"><a href="#" onClick="closeImotTable(); return false;"><img src="images/icon-x.gif" width="19" height="19" border="0" align="absmiddle"></a></td> ';
	imotTable+='</tr> ';
	imotTable+='</table></td> ';
	imotTable+='</tr> ';
	imotTable+='<tr><td>';

	imotTable+='<table border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#EEEEEE">';

	for ( var i=0; i<3; i++ ) {
		imotTable+="<TR>";
		for ( var j=0; j<5; j++ ) {
			var pos=imotPage*3*5 + i*5 + j;
			if ( (replaceCharators.length/2) > pos ) {
				imotTable+='<TD align="center"><a href="#" onClick="appendMsg(\''+convertRegExpSource(replaceCharators[pos*2].source)+'\'); closeImotTable(); return false;">'+ replaceCharators[pos*2+1] +'</a></TD>';
			} else {
				imotTable+='<TD width="50" height="50">&nbsp;</TD>';
			}
		}
		imotTable+="</TR>";
	}

	imotTable+="<TR>";
	if ( imotPage>0 ) {
		imotTable+='<TD align="left"><a href="#" onClick="moveImotPage(-1); return false;">&lt;&lt;</a></TD>';
	} else {
		imotTable+='<TD align="left">&nbsp;</TD>';
	}
	imotTable+='<TD colspan="3">&nbsp;</TD>';
	if ( (imotPage+1)*3*5 < replaceCharators.length/2 ) {
		imotTable+='<TD align="right"><a href="#" onClick="moveImotPage(+1); return false;">&gt;&gt;</a></TD>';
	} else {
		imotTable+='<TD align="right">&nbsp;</TD>';
	}
	imotTable+="</TR>";

	imotTable+="</table>";
	imotTable+="</td></tr></table>";
}

function convertRegExpSource( str ) {
	var r="";
	for ( var i=0; i<str.length; i++ ) {
		var ch=str.charAt(i);
		if ( ch!="\\" ) {
			r+=ch;
		}
	}
	return r;
}

function closeImotTable() {
	tempLayer.style.visibility="hidden";
	tempLayer.style.display="none";
}

function showImotTable() {
	if ( imotTable=="" ) {
		makeImotTable(); 
	}
	tempLayerTd.innerHTML=imotTable;
	tempLayer.style.width=document.body.clientWidth/2+300;
	tempLayer.style.height=document.body.clientHeight-100;
	tempLayer.style.visibility="visible";
	tempLayer.style.display="";
}

//
// Administrate table
//

var administrateTable;

function initAdministrateTable() {
	administrateTable='';
	administrateTable+='  <table width="300" height="120"  border="1" cellpadding="4" cellspacing="0" bordercolor="#000000" bgcolor="#FFFFFF">';
	administrateTable+='    <tr> ';
	administrateTable+='      <td colspan="3" bgcolor="#6666AA"><table width="100%"  border="0" cellspacing="0" cellpadding="0"> ';
	administrateTable+='          <tr> ';
	administrateTable+='            <td><font color="#FFFFFF" size="3">Change Room Informations.</font></td> ';
	administrateTable+='            <td align="right"><a href="#" onClick="hideAdminTable(); return false;"><img src="images/icon-x.gif" width="19" height="19" border="0" align="absmiddle"></a></td> ';
	administrateTable+='          </tr> ';
	administrateTable+='        </table></td> ';
	administrateTable+='    </tr> ';
	administrateTable+='    <tr>';
	administrateTable+='      <td><font size="3">Name</font></td>';
	administrateTable+='      <td><input type="text" name="newRoomName" id="newRoomName"></td>';
	administrateTable+='      <td><a href="#" onClick="changeRoomName(); return false;">Update</a></td>';
	administrateTable+='    </tr>';
	administrateTable+='    <tr>';
	administrateTable+='      <td><font size="3">Members</font></td>';
	administrateTable+='      <td><select name="newRoomMax" id="newRoomMax">';
	administrateTable+='        <option value="2">2</option>';
	administrateTable+='        <option value="3">3</option>';
	administrateTable+='        <option value="5">5</option>';
	administrateTable+='        <option value="10">10</option>';
	administrateTable+='        <option value="20">20</option>';
	administrateTable+='        <option value="30">30</option>';
	administrateTable+='        <option value="50">50</option>';
	administrateTable+='        <option value="100">100</option>';
	administrateTable+='      </select></td>';
	administrateTable+='      <td><a href="#" onClick="changeRoomMax(); return false;">Update</a></td>';
	administrateTable+='    </tr>';
	administrateTable+='    <tr>';
	administrateTable+='      <td><font size="3">Password</font></td>';
	administrateTable+='      <td><input type="password" name="newRoomPassword" id="newRoomPassword"></td>';
	administrateTable+='      <td><a href="#" onClick="changeRoomPassword(); return false;">Update</a></td>';
	administrateTable+='    </tr>';
	administrateTable+='  </table>';
}

function administrate() {
	tempLayerTd.innerHTML=administrateTable;

	tempLayer.style.width=document.body.clientWidth/2+150;
	tempLayer.style.height=document.body.clientHeight/2+60;
	tempLayer.style.visibility="visible";
	tempLayer.style.display="";

	var newRoomName=document.getElementById ? document.getElementById("newRoomName") : document.all ? document.all.newRoomName : null;
	var newRoomMax=document.getElementById ? document.getElementById("newRoomMax") : document.all ? document.all.newRoomMax : null;
	newRoomName.value=roomName;
	newRoomMax.value=roomMax;
}

function changeRoomName() {
	var newRoomName=document.getElementById ? document.getElementById("newRoomName") : document.all ? document.all.newRoomName : null;
	chipchat_applet.changeRoomName(newRoomName.value);
	hideAdminTable();
}

function changeRoomMax() {
	var newRoomMax=document.getElementById ? document.getElementById("newRoomMax") : document.all ? document.all.newRoomMax : null;
	chipchat_applet.changeMax(newRoomMax.value); 
	hideAdminTable();
}

function changeRoomPassword() {
	var newRoomPassword=document.getElementById ? document.getElementById("newRoomPassword") : document.all ? document.all.newRoomPassword : null;
	chipchat_applet.changeRoomPassword(newRoomPassword.value);
	hideAdminTable(); 
}

function hideAdminTable() {
	tempLayer.style.visibility="hidden";
	tempLayer.style.display="none";
}

//
// Resize Layers..
//
var msgLayer;
var membersLayer;
var msgLayerHeight;

function initLayersName() {
	msgLayer=document.getElementById ? document.getElementById("msgLayer") : document.all ? document.all.msgLayer : null;
	membersLayer=document.getElementById ? document.getElementById("membersLayer") : document.all ? document.all.membersLayer : null;
	if (msgLayer==null)
		throw "Can not find [msgLayer].";
	if (membersLayer==null)
		throw "Can not find [membersLayer].";
}

function resizeLayers() {
	var clientWidth=document.body.clientWidth;
	var clientHeight=document.body.clientHeight;

	msgLayer.style.width=clientWidth-201;
	msgLayer.style.height=clientHeight-103;
	msgLayerHeight=clientHeight-103;
	membersLayer.style.left=clientWidth-200;
	membersLayer.style.height=clientHeight-100;
}
