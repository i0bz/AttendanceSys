package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseUtility {
    private ParseUtility() {}



    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Parse uid int.
     *
     * @param uid the string value ID
     * @return the integer value of the ID
     * @throws RuntimeException the ID does not match to current MMSU standard
     */
    public static int parseUID(String uid) throws RuntimeException {
        if (uid.length() != 9) throw new RuntimeException("UID is not of length 9.");
        return Integer.parseInt(uid.replaceAll("[-]", ""));
    }

    public static String unparseUID(int uid) throws RuntimeException {
        String stringUID = String.valueOf(uid);
        if (stringUID.length() != 8) throw new RuntimeException("Invalid UID");
        StringBuilder sb = new StringBuilder(stringUID);
        sb.insert(2, '-');
        return sb.toString();
    }
}
