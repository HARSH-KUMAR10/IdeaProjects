package table_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatisticsRecords
{
    public static void main(String[] args)
    {
        try(Connection dbConnection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb2?user=root&password=password")){

            if(dbConnection!=null){

                Statement query = dbConnection.createStatement();

                ResultSet result = query.executeQuery("select min(age) from users");

                result.next();

                System.out.println("Minimum age of a user is: "+result.getInt(1));

                result = query.executeQuery("select max(age) from users");

                result.next();

                System.out.println("Maximum age of a user is: "+result.getInt(1));

                result = query.executeQuery("select avg(age) from users");

                result.next();

                System.out.println("Average age of a user is: "+result.getInt(1));

            }else{
                System.out.println("Unable to connect to db");
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
