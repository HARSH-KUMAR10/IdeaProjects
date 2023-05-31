package oneway;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class AllSubscriber
{
    public static void main(String[] args) throws Exception
    {

        try (ZContext context = new ZContext())
        {

            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);

            subscriber.connect("tcp://localhost:8081");

            subscriber.subscribe("".getBytes(ZMQ.CHARSET)); // subscriber.subscribe(new byte[0]);

            for (int iterator = 0; iterator < 10; iterator++)
            {

                String receivedString = subscriber.recvStr(0).trim();

//                subscriber.send("Received.".getBytes(ZMQ.CHARSET));

                System.out.println(receivedString);

            }

        }
    }
}
