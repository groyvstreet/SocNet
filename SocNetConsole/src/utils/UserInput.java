package utils;

import lombok.Getter;

import static java.lang.System.console;

public class UserInput {
    @Getter
    private static String email;

    @Getter
    private static String password;

    @Getter
    private static String firstName;

    @Getter
    private static String lastName;

    @Getter
    private static String birthDate;

    private UserInput() {}

    public static void fillUserInput() {
        console().printf("Enter first name: ");
        firstName = console().readLine();
        console().printf("Enter last name: ");
        lastName = console().readLine();
        console().printf("Enter birth date: ");
        birthDate = console().readLine();
        console().printf("Enter email: ");
        email = console().readLine();
        console().printf("Enter password: ");
        password = console().readLine();
    }

}
