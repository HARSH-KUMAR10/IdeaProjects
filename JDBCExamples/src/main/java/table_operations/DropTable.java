package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;

public class DropTable
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {
                int dropTableResult = dbConnection.createStatement().executeUpdate("DROP TABLE users");

                if (dropTableResult == 0)
                {
                    System.out.println("table dropped successfully");
                }
                else
                {
                    System.out.println(dropTableResult);

                    System.out.println("Unable to drop table");
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
