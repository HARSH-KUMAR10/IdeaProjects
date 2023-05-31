package org.example;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

import javax.websocket.ClientEndpoint;
import java.io.IOException;


@ClientEndpoint
@WebSocket(maxIdleTime = 50000)
public class MyWebSocketEndpoint
{
    Session mySession;

    @OnWebSocketConnect
    public void onConnect(Session session)
    {

        System.out.println("on connect ...");

        System.out.println("WebSocket opened: " + session.getRemoteAddress());

        System.out.println("Local Address" + session.getRemoteAddress());

        sendMessage(session, "Hi from Server");

        mySession = session;

    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason)
    {
        System.out.println("WebSocket closed: " + statusCode + " - " + reason);
    }

    @OnWebSocketMessage
    public void onMessage(String message)
    {
        try
        {
            System.out.println("WebSocket message received: " + message);

            sendMessage(mySession, "Reversing " + message);

            String ret = "";

            for (int iterator = message.length() - 1; iterator >= 0; iterator--)
            {
                ret += message.charAt(iterator);
            }

            sendMessage(mySession, ret);

        }
        catch (Exception exception)
        {
            System.out.println(exception.getMessage());

            exception.printStackTrace();
        }
    }

    @OnWebSocketError
    public void onError(Throwable error)
    {
        error.printStackTrace();
    }

    public static void sendMessage(Session session, String message)
    {
        try
        {
            session.getRemote().sendString(message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
