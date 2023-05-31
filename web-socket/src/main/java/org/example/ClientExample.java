package org.example;

import javax.websocket.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;

@ClientEndpoint
public class ClientExample
{
    private final String uri="ws://localhost:8080";
    private static PrintWriter writer;
    public ClientExample(){
        try{
            WebSocketContainer container= ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(uri));

        }catch(Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
    @OnOpen
    public void onOpen(Session session){
        try{
            writer = new PrintWriter(session.getBasicRemote().getSendWriter(),true);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }
    @OnMessage
    public void onMessage(String message){
        try{
            System.out.println("Server: "+message);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        new Thread(()->{
            while(true){
                try
                {
                    System.out.print("Enter expression: ");
                    String question = reader.readLine();
                    writer.println(question);
                }catch (Exception exception){
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                    break;
                }
            }
        }).start();
        new ClientExample();
    }
}
