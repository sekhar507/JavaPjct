<%@ page language="java" contentType="text/html;charset=__CHARSETNAME__"
%><html>
<head>
<title>Chipchat - Input Your Name.</title>
<meta http-equiv="Content-Type" content="text/html; charset=__CHARSETNAME__">
<link href="chipchat.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/javascript">
function setAvatar(to) {
	document.form1.avatar[to].checked=1;
	return false;
}

function form1Submit() {
	if ( document.form1.name.value=="" ) {
		alert("Input your name.");
		return false;
	} else
		return true;
}

</script>
<body onload="setAvatar( Math.floor(Math.random()*15) ); document.form1.name.focus();" bgcolor="white"> 
<table width="97%" border="0" height="95%" cellpadding="0" cellspacing="0" align="center"> 
  <tr> 
    <td align="center"> <form name="form1" method="post" action="login.jsp" onSubmit="javascript:return form1Submit();"> 
        <table width="465" border="1" cellspacing="0" cellpadding="5" bordercolor="#33335C"> 
          <tr align="center" bgcolor="#33335C"> 
            <td colspan="2" valign="middle"><font color="#FFFFFF"><strong>Input your name,..</strong></font></td> 
          </tr> 
          <tr> 
            <td width="63" align="right" valign="middle">Name :</td> 
            <td width="376" align="center" valign="middle"> <input name="name" type="text" size="20" maxlength="12"> 
              <input type="image" name="formimage1" src="images/enter.gif" align="absmiddle" border="0"> </td> 
          </tr> 
          <tr> 
            <td valign="middle" align="right">Avatar:</td> 
            <td align="center" valign="middle"><table width="100%" border="0" cellpadding="0" cellspacing="0"> 
                <tr align="center"> 
                  <td><a href="#" onClick="javascript:return setAvatar(0);"><img src="avatar/01.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(1);"><img src="avatar/02.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(2);"><img src="avatar/03.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(3);"><img src="avatar/04.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(4);"><img src="avatar/05.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(5);"><img src="avatar/06.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(6);"><img src="avatar/07.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(7);"><img src="avatar/08.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(8);"><img src="avatar/09.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(9);"><img src="avatar/10.gif" width="30" height="30" border="0"></a></td> 
                </tr> 
                <tr align="center"> 
                  <td><input type="radio" name="avatar" value="01"></td> 
                  <td><input type="radio" name="avatar" value="02"></td> 
                  <td><input type="radio" name="avatar" value="03"></td> 
                  <td><input type="radio" name="avatar" value="04"></td> 
                  <td><input type="radio" name="avatar" value="05"></td> 
                  <td><input type="radio" name="avatar" value="06"></td> 
                  <td><input type="radio" name="avatar" value="07"></td> 
                  <td><input type="radio" name="avatar" value="08"></td> 
                  <td><input type="radio" name="avatar" value="09"></td> 
                  <td><input type="radio" name="avatar" value="10"></td> 
                </tr> 
                <tr align="center"> 
                  <td><a href="#" onClick="javascript:return setAvatar(10);"><img src="avatar/11.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(11);"><img src="avatar/12.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(12);"><img src="avatar/13.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(13);"><img src="avatar/14.gif" width="30" height="30" border="0"></a></td> 
                  <td><a href="#" onClick="javascript:return setAvatar(14);"><img src="avatar/15.gif" width="30" height="30" border="0"></a></td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                </tr> 
                <tr align="center"> 
                  <td><input type="radio" name="avatar" value="11"></td> 
                  <td><input type="radio" name="avatar" value="12"></td> 
                  <td><input type="radio" name="avatar" value="13"></td> 
                  <td><input type="radio" name="avatar" value="14"></td> 
                  <td><input type="radio" name="avatar" value="15"></td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                  <td>&nbsp;</td> 
                </tr> 
              </table></td> 
          </tr> 
        </table> 
      </form> 
      <p>&nbsp;</p></td> 
  </tr> 
</table> 
<div align="center"> <font size="2"><i>Copyright by Lee Jae Chun</i></font><BR> 
  <font size="1"><a href="http://chipchat.sourceforge.net/">Chipchat v__VERSION__</a></font><BR> 
</div> 
</body>
</html>
