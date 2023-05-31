package multiplesocketsimple;

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

            publisher.bind("tcp://*:8086");

            System.out.println("Starting connection on 8086...");

            while (!Thread.currentThread().isInterrupted())
            {

                double temp = (new Random().nextDouble(10) + 30);

                String message = "101\ttemp: " + temp;

                publisher.send(message, 0);

                Thread.sleep(10);

                System.out.println("sent data: "+message);

                // Unsupported operation exception. SUB (can't send)
                // Unsupported operation exception. PUB (can't receive)

//                String data = publisher.recvStr();

//                System.out.println(data);

            }

        }
    }
}
