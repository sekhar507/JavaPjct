package org.codehaus.jackson.jackson_mapper_asl;

/**
 * Hello world!
 *
 */
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
 
public class App
{
   public static void main(String[] args)
   {
      @SuppressWarnings("deprecation")
      Employee employee = new Employee(1, "Lokesh", "Gupta", new Date(1981,8,18));
      ObjectMapper mapper = new ObjectMapper();
      try
      {
    	 String temp= mapper.writeValueAsString(employee);
    	  System.out.println(temp);
    	  Employee javaobj = mapper.readValue(temp, Employee.class);
    	  System.out.println(javaobj);
        // mapper.writeValue(new File("c://12/employee.json"), employee);
      } catch (JsonGenerationException e)
      {
         e.printStackTrace();
      } catch (JsonMappingException e)
      {
         e.printStackTrace();
      } catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}