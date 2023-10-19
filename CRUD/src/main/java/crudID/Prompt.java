package crudID;

import java.util.Scanner;

public class Prompt {

    public void UserPrompt(){
        String choice;
        Scanner scanner = new Scanner(System.in);
        User user = new User();

        System.out.println("1: Create\n2: Read\n3: Update\n4: Delete");
        choice = scanner.nextLine();

        switch(choice) {
            case "1" -> {
                user.CreateUserInformationPrompt();
            }
            case "2" -> {
                user.Read();
            }
            case "3" -> {
                user.Update();
            }
            case "4" -> {
                user.Delete();
            }
            default -> {
                System.err.println("Please enter a valid response (ex: 1)");
                UserPrompt();
            }
        }
    }
}
