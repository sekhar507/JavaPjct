<%!
String[] channels=new String[] {
	"Movie/Video",
	"Sports/Leisure",
	"Computer/Internet",
	"Game",
	"Humor",
	"Entertainment",
	"Etc",

	"Anybody",
	"Teenagers",
	"Twenties",
	"Thirties",
	"Forties",
	"Fifties"
};

boolean checkChannel( String name ) throws JspException {
	for ( int i=0; i<channels.length; i++ ) {
		if ( channels[i].equals(name) ) {
			return true;
		}
	}
	throw new JspException("Unknown channel name..["+name+"]");
}
%>