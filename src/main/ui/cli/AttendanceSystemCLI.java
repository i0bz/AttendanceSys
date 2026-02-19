package ui.cli;

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


    AttendanceSystemCLI(Scanner stdin, InputValidator validator, AttendanceSystemController systemController) {
        this.stdin = stdin;
        this.systemController = systemController;
        this.validator = validator;
    }



    //TODO Split this up later


    void initCLI() {
        System.out.println("-------Attendance System CLI-------");

        List<String> attendanceLists = systemController.attendanceDateLists();
        int i = 0, decision;


        System.out.println();
        if (attendanceLists.isEmpty()) {
            System.out.println("There are no registered attendances.");
            return;
        }



        System.out.println("-------Date List-------");
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


        Map<String, String> rosterList = systemController.rosterLists();
        List<String> rosterUIDList = new ArrayList<>(systemController.rosterLists().keySet());
        System.out.println();
        System.out.println();

        i = 0;

        System.out.println("-------Attendance Student List-------");
        for (Map.Entry<String, String> student : rosterList.entrySet()) {
            System.out.println(++i + ". " + student.getValue() + "\t " + student.getKey() + " " + systemController.isPresent(student.getKey(), date) );
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
        systemController.toggleAttendance(studentChosen, date);

    }



    public void attendanceMode() {
        System.out.println();
        System.out.println("-------Attendance Mode-------");

        List<String> attendanceLists = systemController.attendanceDateLists();
        int i = 0, decision;


        System.out.println();
        if (attendanceLists.isEmpty()) {
            System.out.println("There are no registered attendances.");
            return;
        }



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

        AttendanceSheet sheet = systemController.queryAttendance(date);
        System.out.println();
        System.out.println("-------Attendance Mode : " + date + "-------");


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
}
