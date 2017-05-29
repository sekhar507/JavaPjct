/*
 * Created on 2004. 2. 20
 */
package chipchat;

/**
 * String utility.
 * @author Mr.Lee
 */
public final class StringUtil {
   /**
    * Convert html special charectors.
    * @param src Source.
    * @return Changed String.
    */
   public static String htmlSpecialChars(final String src) {
      return htmlSpecialChars(new StringBuffer(src)).toString();
   }
   /**
    * Convert html special charectors.
    * @param src Source.
    * @return Changed String.
    */
   public static StringBuffer htmlSpecialChars(final StringBuffer src) {
      if (src == null) {
         return null;
      }
      int srcLength = src.length();

      for (int i = 0; i < srcLength; i++) {
         switch (src.charAt(i)) {
            case '<' :
               src.replace(i, i + 1, "&lt;");
               srcLength += 3;
               i += 3;
               break;
            case '>' :
               src.replace(i, i + 1, "&gt;");
               srcLength += 3;
               i += 3;
               break;
            case '&' :
               src.replace(i, i + 1, "&amp;");
               srcLength += 4;
               i += 4;
               break;
            case '"' :
               src.replace(i, i + 1, "&quot;");
               srcLength += 5;
               i += 5;
               break;
            case '\'' :
               src.replace(i, i + 1, "&#039;");
               srcLength += 5;
               i += 5;
               break;
            default :
               break;
         }
         if (i > 0 && src.charAt(i - 1) == ' ' && src.charAt(i) == ' ') {
            src.replace(i - 1, i, "&nbsp;");
            src.replace(i + 5, i + 6, "&nbsp;");
            srcLength += 10;
            i = i + 10;
         }
      }
      return src;
   }
}
