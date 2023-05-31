package onewaywaited;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Random;

public class Publisher
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);

            publisher.bind("tcp://*:8081");

//            publisher.bind("ipc://weather");

            System.out.println("Starting connection on 8081...");

            while (!Thread.currentThread().isInterrupted())
            {

                double temp = (new Random().nextDouble(10) + 30);

                int client = new Random().nextInt(5);

                String message = "client-" + client + "\ttemp: " + temp;

                Thread.sleep(700);

                publisher.send(message, 0);

                System.out.println("sent data: "+message);

                // Unsupported operation exception. SUB (can't send)
                // Unsupported operation exception. PUB (can't receive)

//                String data = publisher.recvStr();

//                System.out.println(data);

            }

        }
    }
}
