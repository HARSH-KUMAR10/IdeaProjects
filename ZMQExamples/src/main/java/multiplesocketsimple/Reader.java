package multiplesocketsimple;

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


            while (!Thread.currentThread().isInterrupted())
            {

                String task;
                if ((task = taskReceiver.recvStr(ZMQ.DONTWAIT)) != null)
                {
                    System.out.println(task);
                }

                String message;
                if ((message = messageReceiver.recvStr(ZMQ.DONTWAIT)) != null)
                {
                    System.out.println(message);
                }

//                Thread.sleep(1000);

            }
        }
    }
}
