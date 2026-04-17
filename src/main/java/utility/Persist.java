package utility;

import repository.AttendanceRegistry;
import repository.StudentRoster;

import java.io.*;

public class Persist {

    static final String attendanceFile = "./attendances.dat";
    static final String studentRosterFile = "./roster.dat";

    //saving
    public static void saveRegistry(AttendanceRegistry registry) {
        try (FileOutputStream ofs = new FileOutputStream(attendanceFile);
             ObjectOutputStream objectStream = new ObjectOutputStream(ofs))
        {
            objectStream.writeObject(registry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveRosterFile(StudentRoster roster) {
        try (FileOutputStream ofs = new FileOutputStream(studentRosterFile);
             ObjectOutputStream objectStream = new ObjectOutputStream(ofs))
        {
            objectStream.writeObject(roster);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //loading
    public static AttendanceRegistry loadRegistry() {

        AttendanceRegistry reg = null;

        try (FileInputStream ifs = new FileInputStream(attendanceFile);
             ObjectInputStream objectStream = new ObjectInputStream(ifs)) {

            reg = (AttendanceRegistry) objectStream.readObject();

        }

        catch (IOException e) {
            reg = new AttendanceRegistry();
        }

        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

        return reg;
    }
    public static StudentRoster loadRoster() {
        StudentRoster reg = null;


        try (FileInputStream ifs = new FileInputStream(studentRosterFile);
             ObjectInputStream objectStream = new ObjectInputStream(ifs)) {


            reg = (StudentRoster) objectStream.readObject();

        }

        catch (IOException e) {
            reg = new StudentRoster();
        }

        catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }

        return reg;
    }
}
