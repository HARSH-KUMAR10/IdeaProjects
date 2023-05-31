package table_operations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateRecord
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                PreparedStatement updateQuery = dbConnection.prepareStatement("UPDATE users SET age=? WHERE name=?");

                System.out.print("Enter name to update: ");

                String name = reader.readLine();

                System.out.print("Enter updated age: ");

                String age = reader.readLine();

                updateQuery.setInt(1, Integer.valueOf(age));

                updateQuery.setString(2, name);

                int updateResult = updateQuery.executeUpdate();

                if (updateResult > 0)
                {
                    System.out.println("Successfully updated " + updateResult + " records from table users");
                }
                else
                {
                    System.out.println("unable to update records");
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
