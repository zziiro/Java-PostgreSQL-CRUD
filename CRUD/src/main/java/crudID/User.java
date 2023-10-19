package crudID;

import java.util.Scanner;

// deleted constructors, not sure if I needed them or not
// maybe make a user id when everything is all fleshed out (for access purposes)

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String dob;
    private int age;

    Scanner scanner = new Scanner(System.in);
    PostgreSqlConnection psql = new  PostgreSqlConnection();
    PostgreSqlUpdate psqlU = new PostgreSqlUpdate();

    // add a new user to the database (Create)
    public void CreateUserInformationPrompt(){

        System.out.println("First Name: ");
        this.firstName = scanner.nextLine();
        System.out.println("Last Name: ");
        this.lastName = scanner.nextLine();
        System.out.println("Date of Birth: ");
        this.dob = scanner.nextLine();
        System.out.println("Age: ");
        this.age = scanner.nextInt();

        psql.CreateNewUser(this.firstName, this.lastName, this.dob, this.age);
    }

    // Read from database
    public void Read(){
        System.out.println("Enter a User ID: ");
        userId = scanner.nextInt();

        psql.Read(userId);
    }

    // Update user information
    public void Update(){
        String choice;
        System.out.println("Choose which to update:\n1: First Name\n2: Last Name\n3: Date of Birth\n4: Age");
        choice = scanner.nextLine();

        switch(choice){
            // update first name
            case "1" -> {
                int userId;
                String updatedFirstName;
                String column = "firstname";

                System.out.println("User ID: ");
                userId = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Update first name to: ");
                updatedFirstName = scanner.nextLine();
                psqlU.setStringUpdate(userId, column, updatedFirstName);
            }
            // update last name
            case "2" -> {
                String column = "lastname";
                System.out.println("User ID: ");
                int userId = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Update last name to: ");
                String updatedLastName = scanner.nextLine();
                psqlU.setStringUpdate(userId, column, updatedLastName);

            }
            // update date of birth
            case "3" -> {
                String column = "dateofbirth";
                System.out.println("User ID: ");
                int userId = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Update Date of Birth to: ");
                String updatedDateOfBirth = scanner.nextLine();
                psqlU.setStringUpdate(userId, column, updatedDateOfBirth);
            }
            // update age
            case "4" -> {
                System.out.println("User ID: ");
                int userId = scanner.nextInt();

                scanner.nextLine();

                System.out.println("Update age to: ");
                int updatedAge = scanner.nextInt();
                psqlU.UpdateAge(userId, updatedAge);
            }
            default -> {
                System.err.println("Invalid response, please enter a valid response.(ex: 1)");
            }
        }
    }

    // deletes the entire instance of a user
    public void Delete(){
        System.out.println("Enter userID you with to delete: ");
        userId = scanner.nextInt();

        psql.Delete(userId);
    }
}

