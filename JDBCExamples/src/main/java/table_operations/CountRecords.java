package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class CountRecords
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                ResultSet recordCount = dbConnection.createStatement().executeQuery("SELECT COUNT(*) from users");

                if (recordCount != null)
                {
                    recordCount.next();
                    System.out.println("total record count in users: " + recordCount.getInt(1));
                }
                else
                {
                    System.out.println("unable to count records");
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
