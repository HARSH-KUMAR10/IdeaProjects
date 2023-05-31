package parallelprocessing;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Worker
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);

            receiver.connect("tcp://localhost:8082");


            ZMQ.Socket sender = context.createSocket(SocketType.PUSH);

            sender.connect("tcp://localhost:8083");


            while (!Thread.currentThread().isInterrupted())
            {
                String stringTime = receiver.recvStr();

                long time = Long.parseLong(stringTime);

                System.out.flush();

                System.out.print(stringTime + ".");

                Thread.sleep(time);

                sender.send(ZMQ.MESSAGE_SEPARATOR, 0);
            }
        }
    }
}
