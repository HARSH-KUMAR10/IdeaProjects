package org.example;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@ServerEndpoint("/arithmetic")
public class ServerExample
{
    static PrintWriter writer;
    @OnOpen
    public void onOpen(Session session){
        try
        {
            System.out.println("Connected to " + session.getId());
            writer = new PrintWriter(session.getBasicRemote().getSendWriter());
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("disconnecting from "+session.getId());
    }

    @OnMessage
    public void onMessage( String message,Session session){
        System.out.println("Client: "+message);
    }
    @OnError
    public void onError(Session session){
        System.out.println("Error occurred at "+session.getId());
    }

    public static void main(String[] args)
    {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
          new Thread(()->{
              while(true){
                  try{
                      writer.println(reader.readLine());
                  }catch (Exception exception){
                      System.out.println(exception.getMessage());
                      exception.printStackTrace();
                      break;
                  }
              }
          }).start();
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
}