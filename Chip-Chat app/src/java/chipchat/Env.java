/*
 * Created on 2003. 2. 20.
 */
package chipchat;

import java.io.InputStream;
import java.util.Properties;

/**
 * Environment.
 * @author Mr. Lee
 */
public final class Env extends Properties {
   // SINGLETON...
   /**
    * Instance.
    */
   private static Env instance;

   /**
    * Get instance.
    * @return Instance.
    */
   public static Env getInstance() {
      if (instance != null) {
         return instance;
      } else {
         makeInstance();
         return instance;
      }
   }

   /**
    * Make instance if it is not exist.
    */
   private static synchronized void makeInstance() {
      if (instance == null) {
         instance = new Env();
      }
   }

   /**
    * Constructor for ChipChatProperties.
    */
   private Env() {
      // default values
      setProperty("ChipChat.maxRoom", "100");
      setProperty("Communicator.serverName", "ChipChat");
      setProperty("Communicator.adminpasswd", "adminpw");

      InputStream is = getClass().getResourceAsStream("chipchat.properties");
      try {
         load(is);
      } catch (Exception e) {
         System.err.println(
            "Error : Can't read the properties file.\r\n"
               + "Make sure chipchat.property is in the CLASSPATH");
         return;
      }
   }
}
