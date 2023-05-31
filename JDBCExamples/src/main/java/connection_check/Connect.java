package connection_check;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect
{
    public static void main(String[] args)
    {
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/test", "root", "password"))
        {

            if (con != null)
            {

                Statement st = con.createStatement();

                ResultSet rs = st.executeQuery("select * from persons;");

                while (rs.next())
                {
                    System.out.println("Name: " + rs.getString("name"));

                    System.out.println("Age: " + rs.getInt("age"));

                    System.out.println("===================================");
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
