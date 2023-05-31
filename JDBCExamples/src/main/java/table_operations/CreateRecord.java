package table_operations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateRecord
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            if (dbConnection != null)
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                PreparedStatement insertQuery = dbConnection.prepareStatement("INSERT INTO users values(?,?)");

                System.out.print("Enter name: ");

                String name = reader.readLine();

                System.out.print("Enter age: ");

                String age = reader.readLine();

                insertQuery.setString(1, name);

                insertQuery.setInt(2, Integer.valueOf(age));

                int insertIntoResult = insertQuery.executeUpdate();

                if (insertIntoResult == 1)
                {
                    System.out.println("New row added successfully");
                }
                else
                {
                    System.out.println("unable to add new record");
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
