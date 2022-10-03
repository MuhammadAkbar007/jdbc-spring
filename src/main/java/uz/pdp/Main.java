package uz.pdp;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DatabaseService databaseService = new DatabaseService();

        int i = -1;
        while (i != 0) {
            System.out.println("0=>Exit, 1=>New User, 2=>Edit User, 3=>Delete User, 4=>List Users");
            i = scanner.nextInt();
            scanner = new Scanner(System.in);
            switch (i) {
                case 1:
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    User user = new User(firstName, lastName, userName, password);
                    databaseService.saveUser(user);
//                    databaseService.saveUserPreparedStatement(user);
                    break;
                case 2:
                    scanner = new Scanner(System.in);
                    System.out.print("Enter user's id: ");
                    int id = scanner.nextInt();
                    scanner = new Scanner(System.in);
                    System.out.print("Enter editing firstname: ");
                    firstName = scanner.nextLine();
                    System.out.print("Enter editing lastname: ");
                    lastName = scanner.nextLine();
                    System.out.print("Enter editing username: ");
                    userName = scanner.nextLine();
                    System.out.print("Enter editing password: ");
                    password = scanner.nextLine();
                    user = new User(id, firstName, lastName, userName, password);
                    databaseService.editUser(user);
                    break;
                case 3:
                    scanner = new Scanner(System.in);
                    System.out.print("Enter user's id: ");
                    id = scanner.nextInt();
                    databaseService.deleteUser(id);
                    break;
                case 4:
                    try {
                        databaseService.getUsers();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
            }

        }

    }
}
