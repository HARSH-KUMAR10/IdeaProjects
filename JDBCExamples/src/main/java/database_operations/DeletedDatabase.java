package database_operations;

import java.sql.Connection;
import java.sql.DriverManager;

public class DeletedDatabase
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                String dbName = "harshdb1";

                int deleteResult = dbConnection.createStatement().executeUpdate("DROP DATABASE " + dbName);

                if (deleteResult == 0)
                {
                    System.out.println("Database deleted successfully");
                }
                else
                {
                    System.out.println(deleteResult);
                    System.out.println("Unable to delete db");
                }

            }
            else
            {
                System.out.println("Unable to connect to db");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
