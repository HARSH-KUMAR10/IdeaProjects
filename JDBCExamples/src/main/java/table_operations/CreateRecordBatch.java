package table_operations;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateRecordBatch
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password"))
        {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            if(dbConnection!=null){


                PreparedStatement insertQuery = dbConnection.prepareStatement("insert into users values(?,?)");

                while(true)
                {

                    System.out.print("Enter name: ");

                    String name = reader.readLine();

                    System.out.print("Enter age: ");

                    String age = reader.readLine();

                    insertQuery.setString(1, name);

                    insertQuery.setInt(2, Integer.valueOf(age));

                    insertQuery.addBatch();

                    System.out.print("Do you want to add more? (yes/no):");

                    if(reader.readLine().equalsIgnoreCase("no")){
                        break;
                    }

                }

                System.out.println(insertQuery.executeBatch().toString());

                System.out.println("data added successfully");

            }else{
                System.out.println("Unable to connect to db");
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
