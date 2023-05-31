package pub_sub_envelope;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publisher
{
    public static void main(String[] args) throws Exception
    {
        // Prepare our context and publisher
        try (ZContext context = new ZContext()) {
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            publisher.bind("tcp://*:5563");

            while (!Thread.currentThread().isInterrupted()) {
                // Write two messages, each with an envelope and content
                publisher.sendMore("A");
                publisher.send("We don't want to see this");
                publisher.sendMore("B");
                publisher.send("We would like to see this");
            }
        }
    }
}
