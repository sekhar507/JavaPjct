var decoTextTd;
var toTd;

function inputInit() {
	decoTextTd=document.getElementById ? document.getElementById("decoTextTd") : document.all ? document.all.decoTextTd : null;
	if (decoTextTd==null)
		throw "Can not find [decoTextTd].";
	toTd=document.getElementById ? document.getElementById("toTd") : document.all ? document.all.toTd : null;
	if (toTd==null)
		throw "Can not find [toTd].";
	document.forms["MsgSendForm"].inputtext.focus();
}

function sendMsg() {
	var t = document.forms["MsgSendForm"].inputtext.value;
	var to = document.forms["MsgSendForm"].to.value;

	if(t.length < 1) {
		return false;
	}
	document.forms["MsgSendForm"].inputtext.value="";
	chipchat_inputMsg( to, t, deco_color, deco_bold, deco_underline, deco_italic );
	return false;
}
function talkTo( to, name ) {
	document.forms["MsgSendForm"].to.value=to;
	toTd.innerHTML='To '+name+' [<a href="#" onClick="talkToAll(); return false;">To All</a>]';
	return false;
}
function talkToAll() {
	document.forms["MsgSendForm"].to.value=-1;
	toTd.innerHTML="To all...";
	return false;
}
function getOut() {
	chipchat_applet.getOut();
}

var deco_bold=false;
var deco_italic=false;
var deco_underline=false;
var deco_color="#000000";

function setDeco( t ) {
	if ( t==1 ) {
		if ( deco_bold ) {
			deco_bold=false;
		} else {
			deco_bold=true;
		}
	} else if ( t==2 ) {
		if ( deco_underline )
			deco_underline=false;
		else
			deco_underline=true;
	} else {
		if ( deco_italic )
			deco_italic=false;
		else
			deco_italic=true;
	}
	displayDeco();
	return false;
}

function displayDeco() {
	var tmp='A';
	if ( deco_bold )
		tmp='<strong>'+tmp+'</strong>';
	if ( deco_underline )
		tmp='<u>'+tmp+'</u>';
	if ( deco_italic )
		tmp='<em>'+tmp+'</em>';
	tmp='<font color="'+deco_color+'" size="+3">'+tmp+'</font>';
	decoTextTd.innerHTML=tmp;
}

function changeColor( color ) {
	deco_color=color;
	displayDeco();
	document.forms["MsgSendForm"].inputtext.focus();
}

function appendMsg( str ) {
	document.forms["MsgSendForm"].inputtext.value=document.forms["MsgSendForm"].inputtext.value+str;
	document.forms["MsgSendForm"].inputtext.focus();
}
