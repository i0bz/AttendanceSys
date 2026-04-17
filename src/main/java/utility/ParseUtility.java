package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseUtility {
    private ParseUtility() {}



    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    public static int parseUID(String uid) throws IllegalArgumentException {
        uid = uid.replaceAll("-", "");
        if (uid.length() != 8) throw new IllegalArgumentException("Invalid UID");
        return  Integer.parseInt(uid);
    }

    public static String unparseUID(int uid) throws IllegalArgumentException {
        String stringUID = String.valueOf(uid);
        if (stringUID.length() != 8) throw new IllegalArgumentException("Invalid UID");
        StringBuilder sb = new StringBuilder(stringUID);
        sb.insert(2, '-');
        return sb.toString();
    }
}
