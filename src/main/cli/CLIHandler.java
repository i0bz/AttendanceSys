package cli;

import controllers.AttendanceSystemController;

import java.util.Scanner;

public class CLIHandler {
    private final InputValidator validator;
    private final StudentManagementCLI studentManagement;
    private final AttendanceManagementCLI attendanceManagement;
    private final AttendanceSystemCLI attendanceSystem;


    public CLIHandler(AttendanceSystemController systemController) {
        Scanner stdin = new Scanner(System.in);
        this.validator = new InputValidator(stdin);
        this.studentManagement = new StudentManagementCLI(stdin, validator, systemController);
        this.attendanceManagement = new AttendanceManagementCLI(stdin, validator, systemController);
        this.attendanceSystem = new AttendanceSystemCLI(stdin, validator, systemController);
    }

    public void init() {
        int decision;

        while (true) {

            System.out.println("-------GetAttended-------");
            System.out.println("1. Student Management");
            System.out.println("2. Attendance Management");
            System.out.println("3. Attendance System");
            System.out.println("4. Attendance Mode");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            decision = validator.safeIntInput();

            while (decision <= 0 || decision >= 6) {
                System.out.print("Enter 1, 2, 3, 4, and 5 only: ");
                decision = validator.safeIntInput();
            }


            switch (decision) {
                case 1:
                    studentManagement.initCLI();
                    break;
                case 2:
                    attendanceManagement.initCLI();
                    break;
                case 3:
                    attendanceSystem.initCLI();
                    break;
                case 4:
                    attendanceSystem.attendanceMode();
                    break;
                case 5:
                    return;
            }
        }

    }
}
