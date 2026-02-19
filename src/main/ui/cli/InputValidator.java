package ui.cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidator {
    private final Scanner stdin;

    InputValidator(Scanner stdin) {
        this.stdin = stdin;
    }

    int safeIntInput() {
        int tmp;
        try {
            tmp = stdin.nextInt();
            stdin.nextLine();
        } catch (InputMismatchException e) {
            stdin.nextLine();
            return -1;
        }
        return tmp;
    }

    double safeDoubleInput() {
        int tmp;
        try {
            tmp = stdin.nextInt();
            stdin.nextLine();
        } catch (InputMismatchException e) {
            stdin.nextLine();
            return -1;
        }
        return tmp;
    }
}
