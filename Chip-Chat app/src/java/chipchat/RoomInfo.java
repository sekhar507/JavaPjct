/*
 * Created on 2003. 2. 20.
 */
package chipchat;

/**
 * Store Room information temporary.
 * @author Mr. Lee
 */
public class RoomInfo {
   /**
    * Room name.
    */
   private final String name;
   /**
    * Maxinum number of visitor.
    */
   private final int max;
   /**
    * The number of presence man in room.
    */
   private final int users;
   /**
    * Id of room.
    */
   private final long roomid;
   /**
    * Is have password of room.
    */
   private final boolean passwd;

   /**
    * Constructor.
    * @param room Room instance.
    */
   public RoomInfo(final Room room) {
      this.name = room.getRoomName();
      this.max = room.getMaxMan();
      this.users = room.getUsers().size();
      this.roomid = room.getRoomid().longValue();
      String roomPassword = room.getPasswd();
      this.passwd = (roomPassword != null && (!"".equals(roomPassword)));
   }

   /**
    * Getter of max.
    * @return Maxinum number of visitor.
    */
   public final int getMax() {
      return max;
   }

   /**
    * Getter of name
    * @return Room name.
    */
   public final String getName() {
      return name;
   }

   /**
    * Getter of passwd
    * @return Is have password of room?
    */
   public final boolean isPasswd() {
      return passwd;
   }

   /**
    * Getter of roomid
    * @return Id of room.
    */
   public final long getRoomid() {
      return roomid;
   }

   /**
    * Getter of users
    * @return The number of presence man in room.
    */
   public final int getUsers() {
      return users;
   }
}
