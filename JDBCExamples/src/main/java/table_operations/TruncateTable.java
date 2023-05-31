package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;

public class TruncateTable
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                int deletedResult = dbConnection.createStatement().executeUpdate("TRUNCATE TABLE users");

                if (deletedResult == 0)
                {
                    System.out.println("Successfully deleted " + deletedResult + " records from table users");
                }
                else
                {
                    System.out.println(deletedResult);

                    System.out.println("Unable to truncate records");
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
