package org.rest.server;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 *  Plain old Java Object it does not extend as class or implements 
 *  an interface
 *  The class registers its methods for the HTTP GET request using the @GET annotation. 
 *  Using the @Produces annotation, it defines that it can deliver several MIME types,
 *  text, XML and HTML. 
 *  The browser requests per default the HTML MIME types.
 *  Sets the path to base URL + /hello
*/
@Path("/hello")//it defines URI path it can be specified on class or method
public class RestServer{

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.TEXT_PLAIN)// It defines the media type that the methods of a resource class or MessageBodyWriter can produce.
  public String sayPlainTextHello() {
    return "Hello Sekhar";
  }

  // This method is called if XML is request
  @GET
  @Produces(MediaType.TEXT_XML)//what kind of format
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello Sekhar" + "</hello>";
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<html> " + "<title>" + "Hello Sekhar" + "</title>"
        + "<body><h1>" + "Hello Sekhar " + "</body></h1>" + "</html> ";
  }

} 