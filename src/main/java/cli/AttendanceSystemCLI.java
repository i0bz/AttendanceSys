package cli;

import controllers.AttendanceSystemController;
import entity.AttendanceSheet;
import utility.ParseUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AttendanceSystemCLI {
    private final AttendanceSystemController systemController;
    private final InputValidator validator;
    private final Scanner stdin;
    private List<String> attendanceLists;


    AttendanceSystemCLI(Scanner stdin, InputValidator validator, AttendanceSystemController systemController) {
        this.stdin = stdin;
        this.systemController = systemController;
        this.validator = validator;

        attendanceLists = this.systemController.attendanceDateLists();
    }





    void initCLI() {
        System.out.println("-------Attendance System CLI-------");

        int i = 0, decision;

        attendanceLists = this.systemController.attendanceDateLists();
        if(emptyAttendance()) return;



        AttendanceSheet sheet = attendanceSelector();


        Map<String, String> rosterList = systemController.getAllStudentsById();
        List<String> rosterUIDList = new ArrayList<>(systemController.getAllStudentsById().keySet());
        System.out.println();
        System.out.println();


        System.out.println("-------Attendance Student List-------");
        for (Map.Entry<String, String> student : rosterList.entrySet()) {
            System.out.println(++i + ". " + student.getValue() + "\t " + student.getKey() + " " + systemController.isPresent(student.getKey(), sheet.date().toString()) );
        }



        String studentChosen;
        System.out.println();
        System.out.print("Select student to toggle (select number above to exit): ");


        decision = validator.safeIntInput();

        while (true) {
            try {
                if (rosterUIDList.size() < decision) return;
                studentChosen = rosterUIDList.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = validator.safeIntInput();
            }
        }
        systemController.toggleAttendance(studentChosen, sheet.date().toString());

    }

    public void attendanceMode() {
        System.out.println();
        System.out.println("-------Attendance Mode-------");

        attendanceLists = this.systemController.attendanceDateLists();
        if (emptyAttendance()) return;
        AttendanceSheet sheet = attendanceSelector();

        System.out.println();
        System.out.println("-------Attendance Mode : " + sheet.date() + "-------");


        int parsedUid;
        String line = "";
        while (true) {
            try {
                System.out.println("Enter UID (or q to exit):");
                line = stdin.nextLine();
                parsedUid = ParseUtility.parseUID(line);
                sheet.markPresent(parsedUid);
            } catch (RuntimeException e) {
                if (line.equals("q")) return;

                System.out.print("Enter valid uid: ");
                line = stdin.nextLine();
            }
        }

    }

    private AttendanceSheet attendanceSelector() {
        int i = 0, decision;
        System.out.println("-------Attendance Date List-------");

        for (String attendanceDate : attendanceLists) {
            System.out.println(++i + ". " + attendanceDate);
        }

        System.out.println();
        System.out.print("Select Attendance: ");
        decision = validator.safeIntInput();

        String date;

        while (true) {
            try {
                date = attendanceLists.get(decision - 1);
                break;
            } catch (IndexOutOfBoundsException e) {
                System.out.print("Select valid number: ");
                decision = validator.safeIntInput();
            }
        }

        return systemController.queryAttendance(date);
    }

    private boolean emptyAttendance() {
        System.out.println();
        if (attendanceLists.isEmpty()) {
            System.out.println("There are no registered attendances.");
            return true;
        }
        return false;
    }


}
