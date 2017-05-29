/*
 * Created on 2003. 2. 20.
 */
package chipchat;

/**
 * Message.
 * @author Mr. Lee
 */
public class Msg {
   // Static constants.
   /** It is message. */
   public static final int TYPE_MSG = 0;
   /** It is custom message. */
   public static final int TYPE_CUSTOMMSG = 1;
   /** It is Information. */
   public static final int TYPE_INFO = 2;
   /** It is Error. */
   public static final int TYPE_ERROR = 3;
   /** It is sending whisper message. */
   public static final int TYPE_WSPSND = 11;
   /** It is receiving whisper message. */
   public static final int TYPE_WSPRCV = 12;
   /** It is user list. */
   public static final int TYPE_USERS = 20;
   /** It is administrator information. */
   public static final int TYPE_ADMIN = 30;
   /** It is message tha administrator is changed. */
   public static final int TYPE_ADMINCHANGE = 31;
   /** It is message that administrator send to someone to keep quiet. */
   public static final int TYPE_KEEPQUIET = 40;
   /** It is message that someone had beed kicked out. */
   public static final int TYPE_KICKOUT = 41;

   // Member values
   /**
    * Receiver.
    */
   private final int to;
   /**
    * Writer.
    */
   private final String writer;
   /**
    * Message
    */
   private final String msg;
   /**
    * Message type.
    */
   private final int msgType;

   /**
    * Constructor.
    * @param msgType   Type of message.
    * @param to        To.
    * @param msg       Message.
    * @param writer    Writer.
    */
   public Msg(
      final int msgType,
      final int to,
      final String msg,
      final String writer) {
      this.to = to;
      this.msg = msg;
      this.writer = writer;
      this.msgType = msgType;
   }

   /**
    * Make full string which will be sent to client.
    * @param id User id.
    * @return Maked string.
    */
   public final String getString(final int id) {
      if (to == -1 || to == id || id == -2) {
         switch (msgType) {
            case Msg.TYPE_MSG :
               return ("MSG:" + writer + ">" + msg + "\r\n");
            case Msg.TYPE_CUSTOMMSG :
               return ("CUSTOM:" + writer + ">" + msg + "\r\n");
            case Msg.TYPE_INFO :
               return ("INFO:" + writer + ">" + msg + "\r\n");
            case Msg.TYPE_USERS :
               return ("USERS:" + msg + "\r\n");
            case Msg.TYPE_ERROR :
               return ("ERROR:" + msg + "\r\n");
            case Msg.TYPE_WSPSND :
               return ("WSPSND:" + writer + ">" + msg + "\r\n");
            case Msg.TYPE_WSPRCV :
               return ("WSPRCV:" + writer + ">" + msg + "\r\n");
            case Msg.TYPE_ADMIN :
               return ("ADMIN:" + msg + "\r\n");
            case Msg.TYPE_ADMINCHANGE :
               return ("ADMCG:" + msg + "\r\n");
            case Msg.TYPE_KEEPQUIET :
               return ("KEEPQUIET:" + writer + ">" + msg + "\r\n");
            case Msg.TYPE_KICKOUT :
               return ("KICKOUT:" + writer + ">" + msg + "\r\n");
            default :
               System.out.println(
                  "Need Prosess Routin for msgtype :" + msgType);
               return null;
         }
      } else {
         return null;
      }
   }
}
