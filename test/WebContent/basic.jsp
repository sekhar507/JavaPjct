<HTML>
    <HEAD>
        <TITLE>Calling Superclass Constructors</TITLE>
    </HEAD>

    <BODY>
        <H1>Calling Superclass Constructors</H1>
        <%!
            javax.servlet.jsp.JspWriter out;

            class a
            {
                a() throws java.io.IOException 
                {
                  out.println("In a\'s covnbvbvbvnstructor...<BR>");
                }
            }

            class b extends a
            {
                b() throws java.io.IOException 
                {
                   out.println("In b\'s constructor...<BR>");
                }
            }
        %>     
        <%
        this.out = out;     

        b obj = new b();
        %>
    </BODY>
</HTML>