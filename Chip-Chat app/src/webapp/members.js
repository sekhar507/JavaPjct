var membersTd;
var adminHelpMsgTable;
var helpMsgTable;

function membersInit() {
	membersTd=document.getElementById ? document.getElementById("membersTd") : document.all ? document.all.membersTd : null;
	adminHelpMsgTable=document.getElementById ? document.getElementById("adminHelpMsgTable") : document.all ? document.all.adminHelpMsgTable : null;
	helpMsgTable=document.getElementById ? document.getElementById("helpMsgTable") : document.all ? document.all.helpMsgTable : null;
	if (membersTd==null)
		throw "Can not find [membersTd].";
	if (adminHelpMsgTable==null)
		throw "Can not find [adminHelpMsgTable].";
	if (helpMsgTable==null)
		throw "Can not find [helpMsgTable].";
}

function keepQuiet( id ) {
	chipchat_applet.keepQuiet( id );
}

function kickOut( id ) {
	chipchat_applet.kickOut( id );
}

function entrust( id ) {
	chipchat_applet.entrust( id );
}

function updateMemberList( list ) {
	membersTd.innerHTML=list;
}

function showHelpMsg( isAdmin ) {
	if ( isAdmin ) {
		adminHelpMsgTable.style.visibility="visible";
		adminHelpMsgTable.style.display="";
		helpMsgTable.style.visibility="hidden";
		helpMsgTable.style.display="none";
	} else {
		adminHelpMsgTable.style.visibility="hidden";
		adminHelpMsgTable.style.display="none";
		helpMsgTable.style.visibility="visible";
		helpMsgTable.style.display="";
	}
}