package crudID;


import java.sql.*;
import java.util.Random;

public class PostgreSqlConnection{

    // postgresql info -> eventually put these into a .env file
    String jdbcURL = "{PostgreSQL db url goes here}";
    String username = "{db username}";
    String password ="{db password}";
    Random rand = new Random();

    // create a new user
    public void CreateNewUser(String firstName, String lastName, String dateOfBirth, int age){
        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            // create user ID
            int upperBound = 10000;
            int userId = rand.nextInt(upperBound);

            // Send data -> ???? avoid SQL injection
            String createUser = "INSERT INTO users (userId, firstName, lastName, dateOfBirth, age) VALUES (?, ?, ?, ?, ?)";

            // Set Values for placeholders
            PreparedStatement preparedStatement = connection.prepareStatement(createUser);
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setInt(5, age);

            // check if the data was updated
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0){
                System.out.println("User has been Updated!");
            }else{
                System.out.println("User has not been updated!");
            }

            // close the sql connection
            connection.close();
        } catch(SQLException SQLexception){
            System.err.println("Connection to postgre has failed\n" + SQLexception.getMessage());
        }
    }

    // Read from the database
    public void Read(int userId){
        try{
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            String readQuery = "SELECT firstname, lastname, dateofbirth, age FROM users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(readQuery);
            preparedStatement.setInt(1, userId);

            ResultSet result = preparedStatement.executeQuery();

            // if user exists -> print the results -> there maybe be more than one

            String first_name = result.getString("firstname");
            String last_name = result.getString("lastname");
            String dob = result.getString("dateofbirth");
            int age = result.getInt("age");

            System.out.println("First Name: " + first_name + "\n" +
                    "Last Name: " + last_name + "\n" +
                    "Date of Birth: " + dob + "\n" +
                    "Age: " + age);


        } catch(SQLException sqlException){
            System.err.println("There has been an error trying to Read from users table..");
            System.err.println("A user with that particular ID may not exist..");
        }
    }

    public void Delete(int userId){
        try{
            // connect to postgresql
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            // send delete query to the database
            String deleteQuery = "DELETE FROM users WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            // set query parameters
            preparedStatement.setInt(1, userId);

            // get result of query
            int result = preparedStatement.executeUpdate();

            // check if the user was deleted
            if(result > 0){
                System.out.println("User " + userId + " has been deleted!");
            }

        }catch(SQLException sqlException){
            System.err.println("Error deleting user");
        }
    }

}
