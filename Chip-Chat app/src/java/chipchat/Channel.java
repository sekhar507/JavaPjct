/*
 * Created on 2004. 3. 9.
 *
 */
package chipchat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Mr.Lee
 */
public final class Channel {
   /** Chipchat Instance.. */
   private ChipChat parent;

   /*
    * Rooms Operations...
    */

   /** Rooms */
   private Map rooms = new HashMap();

   /**
    * Make new room.
    * @param roomname The name of room
    * @param maxman The maxmum number of person in room
    * @param passwd Password
    * @param master Administrator id number
    * @return New Room Numbers.
    * @throws Exception When Room is full.
    */
   public Long makeRoom(
      final String roomname,
      final int maxman,
      final String passwd,
      final int master)
      throws Exception {
      Long num = getUniqueNumber();
      Room room = new Room(this, num, roomname, maxman, passwd, master);
      synchronized (rooms) {
         rooms.put(num, room);
      }
      return num;
   }

   /**
    * Remove Room.
    * @param num Unique number of room.
    */
   public void removeRoom(final Long num) {
      synchronized (rooms) {
         rooms.remove(num);
      }
   }

   /**
    * Get Room.
    * @param num Unique number of room.
    * @return Room.
    */
   public Room getRoom(final Long num) {
      synchronized (rooms) {
         return (Room) rooms.get(num);
      }
   }

   /*
    * Make Unique Number..
    */
   /** Lock object for unique number. */
   private Object uniqueNumberLock = new Object();
   /** Number of last maked. */
   private long uniqueNum = 0;

   /**
    * Make unique number.
    * @return Unique number
    */
   private Long getUniqueNumber() {
      long r;
      synchronized (uniqueNumberLock) {
         r = uniqueNum++;
      }
      return new Long(r);
   }

   /*
    * Room Lists....
    */
   /** Temporary List of room for listing. */
   private RoomInfo[] roomList;
   /** Whether rooms are changed or not. */
   private boolean listChanged = true;

   /**
    * Set that rooms are changed.
    */
   protected void setListChanged() {
      listChanged = true;
   }

   /**
    * Get temporary list of rooms.
    * @return Temporary list of rooms.
    */
   public RoomInfo[] getRoomList() {
      if (listChanged) {
         makeRoomList();
      }
      return roomList;
   }

   /**
    * Make new temporary list of rooms.
    */
   private void makeRoomList() {
      synchronized (rooms) {
         if (listChanged) {
            RoomInfo[] tmpRoomList = new RoomInfo[rooms.size()];
            int c = 0;
            Iterator i = rooms.values().iterator();
            while (i.hasNext()) {
               Object value = i.next();
               tmpRoomList[c] = new RoomInfo((Room) value);
               c++;
            }
            roomList = tmpRoomList;
         }
      }
   }
}
