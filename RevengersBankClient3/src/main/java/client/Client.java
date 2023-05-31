package client;

import client.service.ClientAccountServices;
import model.SocketControllers;
import model.Utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client
{
    private SocketControllers socketControllers;

    public Client()
    {
        try
        {
//            socketControllers = new SocketControllers(new Socket(Utilities.Dependencies.IP, Utilities.Dependencies.PORT));

            start();
        }
        catch (Exception exception)
        {
            System.out.println("Unable to connect to server, please restart." + Utility.Messages.UNABLE_TO_CONN_SERVER);
        }
    }

    private void start()
    {
        try
        {
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

            boolean loopFlag = true;

            while (loopFlag)
            {
                System.out.print("1. Login\n2. Create Account\n0. Exit\nEnter your choice: ");

                switch (userInputReader.readLine())
                {
                    case Utility.Keyword.ONE -> ClientAccountServices.login();

                    case Utility.Keyword.TWO -> ClientAccountServices.signUp();

                    case Utility.Keyword.ZERO -> loopFlag=false;

                    default -> System.out.println(Utility.Messages.WRONGINPUT);
                }
            }
        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.CLIENT_ERROR_RESTART);
        }

    }
}
