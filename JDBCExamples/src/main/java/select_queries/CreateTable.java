package select_queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CreateTable
{
    public static void main(String[] args)
    {
        try (Connection dbConnection =
                     DriverManager.getConnection("jdbc:mysql://localhost:3306/harshdb3?user=root&password=password"))
        {

            Statement createTableQuery = dbConnection.createStatement();

            int createTableResult = createTableQuery
                    .executeUpdate("create table Clubs(clubId int,clubName varchar(20), primary key (clubId))");

            if (createTableResult == 0)
            {

                createTableResult = createTableQuery
                        .executeUpdate("create table Countries(countryId int, countryName varchar(20), primary key (countryId))");

                if (createTableResult == 0)
                {

                    createTableResult = createTableQuery
                            .executeUpdate("create table Players(name varchar(20), age int, clubId int, countryId int," +
                                           " foreign key (clubId) references Clubs(clubId)," +
                                           " foreign key (countryId) references Countries(countryId))");

                    if (createTableResult == 0)
                    {
                        System.out.println("Successfully create Clubs, Countries, and Players table.");
                    }

                }
            }

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
