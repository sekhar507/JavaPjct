/*
 * Created on 2004. 2. 20
 *
 */
package chipchat;

import java.io.OutputStream;

/**
 * User info.
 * @author Mr. Lee
 */
public class User {
   /**
    * User Name.
    */
   private String username;
   /**
    * User id number.
    */
   private Integer userid;
   /**
    * Ouput Stream.
    */
   private OutputStream outputStream;

   /**
    * Constructor.
    * @param username User Name.
    * @param userid User id number.
    * @param outputStream Ouput Stream.
    */
   public User(
      final String username,
      final int userid,
      final OutputStream outputStream) {
      this.username = username;
      this.userid = new Integer(userid);
      this.outputStream = outputStream;
   }

   /**
    * Getter of outputStream.
    * @return Ouput Stream.
    */
   public final OutputStream getOutputStream() {
      return outputStream;
   }

   /**
    * Getter of userid.
    * @return User id number.
    */
   public final Integer getUserid() {
      return userid;
   }

   /**
    * Getter of username.
    * @return User Name.
    */
   public final String getUsername() {
      return username;
   }

}
