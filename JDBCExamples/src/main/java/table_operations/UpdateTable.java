package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;

public class UpdateTable
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2", "root", "password"))
        {

            if (dbConnection != null)
            {

                int updateResult = dbConnection.createStatement()
                        .executeUpdate("ALTER TABLE users RENAME COLUMN name to nickname");

                if (updateResult == 0)
                {
                    System.out.println("Successfully renamed column");
                }
                else
                {
                    System.out.println(updateResult);
                    System.out.println("Unable to rename column");
                }


            }
            else
            {
                System.out.println("unable to connect to db");
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
