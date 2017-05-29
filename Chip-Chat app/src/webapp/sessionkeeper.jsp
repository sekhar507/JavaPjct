<%@ page language="java"%><%
if ( request.getHeader("user-agent").indexOf("ChipChat agent") < 0 ) {
	response.sendError( 404 );
}
%>