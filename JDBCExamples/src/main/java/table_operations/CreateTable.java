package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateTable
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {
                int createTableResult = dbConnection.createStatement()
                        .executeUpdate("CREATE TABLE users(name varchar(20), age int)");

                if (createTableResult == 0)
                {
                    System.out.println("Created new table users");
                }
                else
                {
                    System.out.println(createTableResult);
                    System.out.println("Unable to create new table");
                }

            }
            else
            {
                System.out.println("Unable to connect to db.");
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
