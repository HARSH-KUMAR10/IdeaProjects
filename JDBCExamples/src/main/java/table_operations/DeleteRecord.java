package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;

public class DeleteRecord
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {
                int deleteResult = dbConnection.createStatement().executeUpdate("DELETE FROM users where age<20");

                if (deleteResult > 0)
                {
                    System.out.println("Successfully deleted "+deleteResult+" records from table users");
                }
                else
                {
                    System.out.println(deleteResult);
                    System.out.println("Unable to delete table");
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
