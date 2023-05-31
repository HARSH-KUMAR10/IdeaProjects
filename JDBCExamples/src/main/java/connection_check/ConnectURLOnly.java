package connection_check;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectURLOnly
{
    public static void main(String[] args)
    {
        try(Connection con =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=password"))
        {

            if (con != null)
            {
                System.out.println("DB connected");
            }
            else
            {
                System.out.println("Unable to connect to DB");
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
