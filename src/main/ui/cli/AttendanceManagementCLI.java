package ui.cli;

import controllers.AttendanceSystemController;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AttendanceManagementCLI {
    private final InputValidator validator;
    private final Scanner stdin;
    private final AttendanceSystemController systemController;

    AttendanceManagementCLI( Scanner stdin, InputValidator validator, AttendanceSystemController systemController) {
        this.validator = validator;
        this.stdin = stdin;
        this.systemController = systemController;
    }


    void initCLI() {
        int decision = 0;



        while (decision != 4) {
            System.out.println();
            System.out.println("-------Attendance Management CLI-------");

            System.out.println("1. Create Attendance");
            System.out.println("2. Delete Attendance");
            System.out.println("3. List Attendances");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = validator.safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = validator.safeIntInput();
            }


            switch (decision) {
                case 1:
                    createAttendance();
                    break;
                case 2:
                    deleteAttendance();
                    break;
                case 3:
                    listAttendances();
                    break;
            }
        }

    }



    private void createAttendance() {
        System.out.println();
        System.out.println("-------Create Attendance-------");
        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = stdin.nextLine();

        while (true) {
            try {
                if (date.equals("q")) return;
                systemController.addAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.print("Reenter: ");
                date = stdin.nextLine();
            }
        }


    }


    private void deleteAttendance() {
        System.out.println();
        System.out.println("-------Delete Attendance-------");

        System.out.print("Enter date with format yyyy-MM-dd: ");
        String date = stdin.nextLine();

        while (true) {
            try {
                if (date.equals("q")) return;
                systemController.removeAttendance(date);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid Date!!!");
                System.out.print("Reenter: ");
                date = stdin.nextLine();
            }
        }


    }


    private void listAttendances() {
        System.out.println();
        System.out.println("-------List Attendances-------");
        List<String> attendances = systemController.attendanceDateLists();
        int i = 0;

        for (String attendance : attendances) {
            System.out.println(++i + ". " + attendance);
        }

        System.out.println();
    }
}
