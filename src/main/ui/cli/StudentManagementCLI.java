package ui.cli;

import controllers.AttendanceSystemController;

import java.util.Map;
import java.util.Scanner;

public class StudentManagementCLI {

    private final Scanner stdin;
    private final InputValidator validator;
    private final AttendanceSystemController systemController;

    StudentManagementCLI(Scanner stdin, InputValidator validator, AttendanceSystemController systemController) {
        this.stdin = stdin;
        this.validator = validator;
        this.systemController = systemController;
    }

    void initCLI() {

        int decision = 0;


        while (decision != 4) {
            System.out.println();
            System.out.println("-------Student Management CLI-------");
            System.out.println("1. Enroll Student");
            System.out.println("2. Drop Student");
            System.out.println("3. List Roster");
            System.out.println("4. Return");
            System.out.print("Enter choice: ");

            decision = validator.safeIntInput();

            while (decision <= 0 || decision >= 5) {
                System.out.print("Enter 1, 2, 3 and 4 only: ");
                decision = validator.safeIntInput();
            }

            switch (decision) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    dropStudent();
                    break;
                case 3:
                    listStudentNames();
                    break;
            }
        }


    }

    private void addStudent() {
        System.out.println();
        System.out.println("-------Enroll Student-------");

        System.out.print("Enter name: ");
        String name = stdin.nextLine();
        if (name.equals("q")) return;
        System.out.print("Enter valid uid: ");
        String uid = stdin.nextLine();

        while (true) {
            try {
                if (uid.equals("q")) return;
                systemController.enrollStudent(name, uid);
                break;
            } catch (RuntimeException e) {
                System.out.println("Invalid UID!!!");
                System.out.print("Reenter: ");
                uid = stdin.nextLine();
            }
        }


    }

    private void dropStudent() {
        System.out.println();
        System.out.println("-------Drop Student-------");

        System.out.print("Enter valid uid: ");
        String uid = stdin.nextLine();

        while (true) {
            try {
                if (uid.equals("q")) return;
                systemController.dropStudent(uid);
                break;
            } catch (RuntimeException e) {
                System.out.println("Invalid UID!!!");
                System.out.print("Reenter: ");
                uid = stdin.nextLine();
            }
        }


    }

    private void listStudentNames() {

        System.out.println();
        System.out.println("-------Student Roster List-------");
        Map<String, String> roster = systemController.getAllStudentById();
        int i = 0;
        for (Map.Entry<String, String> student : roster.entrySet()) {
            System.out.printf("%d. %s\t%s\n", ++i, student.getValue(), student.getKey());
        }
        System.out.println();
        System.out.println();

    }

}
