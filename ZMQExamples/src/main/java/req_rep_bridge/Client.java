package req_rep_bridge;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public class Client
{


    public static void main(String[] args) throws IOException
    {

        Thread client1 = new Thread(new ClientThread("client1" , "hiii"));

        Thread client2 = new Thread(new ClientThread("client2" , "bye"));

        Thread client3 = new Thread(new ClientThread("client3" , "how are you?"));



        System.in.read();

        client1.start();

        client2.start();

        client3.start();

        new Thread(new ClientThread("client4" , "hiii")).start();
    }

}


class ClientThread implements   Runnable
{


    String name ;

    String message;
    ClientThread(String name , String message)
    {
        this.name = name;

        this.message = message;
    }

    public void run()
    {

        ZContext context = new ZContext();

        ZMQ.Socket socket = context.createSocket(SocketType.REQ);

        socket.connect("tcp://localhost:8090");

        while(true)
        {
            socket.send(message);

            String reply = socket.recvStr();

            System.out.println(this.name + " " + reply);

            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }


    }

}