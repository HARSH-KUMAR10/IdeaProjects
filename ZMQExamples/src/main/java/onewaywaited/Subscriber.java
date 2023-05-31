package onewaywaited;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Subscriber
{
    public static void main(String[] args) throws Exception
    {

        try (ZContext context = new ZContext())
        {

            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);

            subscriber.connect("tcp://localhost:8081");

            String filter = "client-" + 4;

            subscriber.subscribe(filter.getBytes(ZMQ.CHARSET));

            for (int iterator = 0; iterator < 10; iterator++)
            {

                String receivedString = subscriber.recvStr(0).trim();

                System.out.println(receivedString);

            }

        }
    }
}
