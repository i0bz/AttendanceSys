package utility;

import repository.AttendanceRegistry;
import repository.StudentRoster;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Persist {
    private static final Logger logger = Logger.getLogger(Persist.class.getName());

    static final String attendanceFile = "./attendances.dat";
    static final String studentRosterFile = "./roster.dat";

    //saving
    public static void saveRegistry(AttendanceRegistry registry) {
        try (FileOutputStream ofs = new FileOutputStream(attendanceFile);
             ObjectOutputStream objectStream = new ObjectOutputStream(ofs))
        {
            objectStream.writeObject(registry);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Roster file did not save successfully", e);
        }
    }
    public static void saveRosterFile(StudentRoster roster) {
        try (FileOutputStream ofs = new FileOutputStream(studentRosterFile);
             ObjectOutputStream objectStream = new ObjectOutputStream(ofs))
        {
            objectStream.writeObject(roster);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Registry file did not save successfully", e);
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
            logger.log(Level.SEVERE, "Unable to open Registry file", e);
            reg = new AttendanceRegistry();
        }

        catch (ClassNotFoundException e2) {
            logger.log(Level.SEVERE, "Corrupted registry file", e2);
            System.exit(1);
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
            logger.log(Level.SEVERE, "Unable to open Roster file", e);
            reg = new StudentRoster();
        }

        catch (ClassNotFoundException e2) {
            logger.log(Level.SEVERE, "Corrupted Roster file", e2);
            System.exit(1);
        }

        return reg;
    }
}
