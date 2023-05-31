package erp.screens;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import erp.Login;
import erp.logins.AbstractCredentials;


public class LoginScreen
{
    static Scanner sc = new Scanner(System.in);
    Login login = new Login();
    private void signUp()
    {
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (login.signUp(email, password))
        {
            System.out.println("User created successfully");
        }
        else
        {
            System.out.println("Error: Unable to create user.");
        }
    }

    private String login()
    {
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        return login.logIn(email,password);
    }
    private void showAllUsers(){
        HashMap<String, AbstractCredentials> loginUsers = login.showAllUsers();
        Set<String> usernames = loginUsers.keySet();
        System.out.println(usernames);
    }

    public String loginScreen()
    {
        while (true)
        {
            System.out.println("=======================================================");
            System.out.print("1.SignUp\n2.Login\n3.Show All Users\n0.Exit\nEnter your choice : ");
            byte choice = sc.nextByte();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    signUp();
                    break;
                case 2:
                    String userName = login();
                    if(userName==null || userName == ""){}else{
                        return userName;
                    }
                    break;
                case 3:
                    showAllUsers();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong input");
            }
            if(choice==0){
                System.out.println("Thanks you, visit again.");
                break;
            }
        }
        return null;
    }
}
