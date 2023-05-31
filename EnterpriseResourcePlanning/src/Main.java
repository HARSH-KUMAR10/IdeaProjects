import erp.Login;
import erp.Users;
import erp.screens.LoginScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main
{
    static String contextUserName = null;

    public static void main(String[] args)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to Enterprise Resource Planning");

        System.out.println("Logged in as admin");

        LoginScreen loginScreen = new LoginScreen();

        contextUserName = loginScreen.loginScreen();

        if (contextUserName == null || contextUserName == "")
        {
            System.exit(0);
        }
        else
        {

        }
//        while (true)
//        {
//            try
//            {
//                System.out.print("============MENU=============\n0. Exit\n1. Create a user\nEnter Option : ");
//
//                byte mainMenuOption = Byte.parseByte(br.readLine());
//
//                switch (mainMenuOption)
//                {
//                    case 1:
//
//                        System.out.println("Creating a new user ...");
//
//                        System.out.println("===============NEW USER================");
//
//                        System.out.print("Enter Name : ");
//
//                        String name = br.readLine();
//
//                        System.out.print("Enter Email : ");
//
//                        String email = br.readLine();
//
//                        System.out.print("Enter Password : ");
//
//                        String password = br.readLine();
//
//                        user = new Users(name, email, password);
//
//                        System.out.println(user.getName() + " " + user.getEmail());
//
//                        break;
//
//                    default:
//
//                        System.out.println("Please check your input");
//
//                        break;
//
//                }
//                if (mainMenuOption == 0)
//                {
//                    break;
//                }
//            }
//            catch (IOException e)
//            {
//                System.out.println(e);
//            }
//        }
    }
}