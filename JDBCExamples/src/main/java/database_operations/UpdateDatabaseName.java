package database_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// Not working

public class UpdateDatabaseName
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                String oldDBName = "harshdb1";

                String newDBName = "harshdb2";

                Statement updateDBName = dbConnection.createStatement();

                int updateResult = updateDBName.executeUpdate("RENAME DATABASE " + oldDBName + " TO " + newDBName);

                if (updateResult == 1)
                {
                    System.out.println(String.format("DB name updated from %s to %s", oldDBName, newDBName));
                }
                else
                {
                    System.out.println("Unable to update DB name");
                }

            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
