package crudID;

import java.sql.*;

public class PostgreSqlUpdate extends PostgreSqlConnection {

    // update firstname, lastname, dateofbirth
    public void setStringUpdate(int userId, String column, String updatedData){
        try{
            // establish connection
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            // create the update query string
            String firstNameUpdateQuery = "UPDATE users "
                    + "SET " + column + " = ? "
                    + "WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(firstNameUpdateQuery);

            // send the updated data
            preparedStatement.setString(1, updatedData);
            preparedStatement.setInt(2, userId);

            // check if the data was updated
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0){
                System.out.println("User has been Updated!");
            }else{
                System.out.println("User has not been updated!");
            }

            // close connection
            connection.close();
        }catch(SQLException sqlException){
            String errorMessage = sqlException.getMessage();;
            System.err.println("Error while updating\n"  + errorMessage);
        }
    }

    // update age
    public void UpdateAge(int userId, int updatedAge){
        try{
            // establish connection
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            // create the update query string
            String firstNameUpdateQuery = "UPDATE users "
                    + "SET age = ? "
                    + "WHERE userid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(firstNameUpdateQuery);

            // send the updated data
            preparedStatement.setInt(1, updatedAge);
            preparedStatement.setInt(2, userId);

            // check if the data was updated
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows > 0){
                System.out.println("User has been Updated!");
            }else{
                System.out.println("User has not been updated!");
            }

            // close connection
            connection.close();
        }catch(SQLException sqlException){
            String errorMessage = sqlException.getMessage();;
            System.err.println("Error while updating\n"  + errorMessage);
        }
    }
}
