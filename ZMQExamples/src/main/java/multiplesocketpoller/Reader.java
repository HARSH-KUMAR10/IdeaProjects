package multiplesocketpoller;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Reader
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())
        {

            // receive task from ventilator and complete
            ZMQ.Socket taskReceiver = context.createSocket(SocketType.PULL);

            taskReceiver.connect("tcp://localhost:8085");


            // receive message from publisher
            ZMQ.Socket messageReceiver = context.createSocket(SocketType.SUB);

            messageReceiver.connect("tcp://localhost:8086");

            messageReceiver.subscribe("101".getBytes(ZMQ.CHARSET));


            ZMQ.Poller poller = context.createPoller(2);

            poller.register(taskReceiver, ZMQ.Poller.POLLIN);

            poller.register(messageReceiver, ZMQ.Poller.POLLIN);

            while (!Thread.currentThread().isInterrupted())
            {

                poller.poll();

                if (poller.pollin(0))
                {
                    System.out.println(taskReceiver.recvStr());
                }
                if (poller.pollin(1))
                {
                    System.out.println(messageReceiver.recvStr());
                }
            }
        }
    }
}
