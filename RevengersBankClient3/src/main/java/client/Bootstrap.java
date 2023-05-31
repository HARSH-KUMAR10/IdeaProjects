package client;

import model.Utility;

public class Bootstrap
{
    public static void main(String[] args)
    {
        System.out.println(Utility.Messages.WELCOME);
        new Client();
    }
}