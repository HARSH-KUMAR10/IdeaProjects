package database_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateNewDatabase
{
    public static void main(String[] args)
    {
        try (Connection connection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=password"))
        {

            if (connection != null)
            {

                Statement createDatabaseStatement = connection.createStatement();

                String newDatabaseName = "harshdb3";

                int opeartionResult = createDatabaseStatement.executeUpdate("create database " + newDatabaseName);

                if (opeartionResult == 1)
                {
                    System.out.println("New database created - " + newDatabaseName);
                }
                else
                {
                    System.out.println("Unable to create database - " + newDatabaseName);
                }

            }
            else
            {
                System.out.println("Unable to connected to db.");
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
