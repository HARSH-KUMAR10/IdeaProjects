package database_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowAllDatabase
{
    public static void main(String[] args)
    {
        try (Connection databaseConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "password"))
        {

            if (databaseConnection != null)
            {

                Statement readDatabasesQuery = databaseConnection.createStatement();

                ResultSet readDatabaseResult = readDatabasesQuery.executeQuery("show databases");

                if (readDatabaseResult != null)
                {
                    System.out.println("Databases:\n=======================");
                    while (readDatabaseResult.next())
                    {
                        System.out.println(readDatabaseResult.getString(1));
                    }
                }

            }
            else
            {
                System.out.println("Unable to connect to database.");
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
