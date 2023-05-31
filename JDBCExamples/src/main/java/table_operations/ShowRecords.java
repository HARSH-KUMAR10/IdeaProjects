package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ShowRecords
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                ResultSet tableRecords = dbConnection.createStatement().executeQuery("SELECT * FROM users");

                if (tableRecords != null)
                {
                    System.out.println("Records of table users:\n=====================================");

                    while (tableRecords.next())
                    {
                        System.out.println(tableRecords.getString(1) + "\t\t" + tableRecords.getInt("age"));
                    }
                }
                else
                {
                    System.out.println("No records found");
                }

            }
            else
            {
                System.out.println("Unable to connect to database");
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
