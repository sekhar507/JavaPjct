<%@ page language="java" import="chipchat.*"%><%
// System.out.println("## Start of commincator.....["+request.getHeader("INUM")+"]");
if ( request.getHeader("user-agent").indexOf("ChipChat agent") < 0 ) {
	response.sendError( 404 );
} else {
	try {
		Communicator communicator=new Communicator();
		communicator.service(request.getRemoteAddr()+request.getHeader("INUM"),request.getInputStream(), response.getOutputStream());
	} catch ( Exception e ) {
		e.printStackTrace();
	}
}
// System.out.println("## End of commincator.....["+request.getHeader("INUM")+"]");
%>