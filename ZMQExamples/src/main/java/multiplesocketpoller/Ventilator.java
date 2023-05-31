package multiplesocketpoller;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Ventilator
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())
        {

            ZMQ.Socket sender = context.createSocket(SocketType.PUSH);

            sender.bind("tcp://*:8085");

//
//            ZMQ.Socket sink = context.createSocket(SocketType.PUSH);
//
//            sink.connect("tcp://localhost:8083");
//
//            System.out.println("Press enter to continue ...");
//            System.in.read();
//            System.out.println("Sending work to workers ...");
//
//            sink.send("0", 0);

            long totalEstimatedTime = 0;

            for (int workIterator = 0; workIterator < 10; workIterator++)
            {
                long workload = Math.round((Math.random() * 100) + 100);

                totalEstimatedTime += workload;

                System.out.print(workload + ".");

                sender.send((workload + ""), 0);
            }

            System.out.println("\ntotal expected cost: " + totalEstimatedTime + "ms");

            Thread.sleep(1000);
        }
    }
}
