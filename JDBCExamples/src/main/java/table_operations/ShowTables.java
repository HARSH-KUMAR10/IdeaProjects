package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ShowTables
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                ResultSet allTables = dbConnection.createStatement().executeQuery("SHOW TABLES");

                if (allTables != null)
                {
                    System.out.println("All tables of harshdb2 are: \n===================================");

                    while (allTables.next())
                    {
                        System.out.println(allTables.getString(1));
                    }
                }
                else
                {
                    System.out.println("No tables found");
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
