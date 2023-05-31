package reqres;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server2
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())
        {
            ZMQ.Socket socket = context.createSocket(SocketType.REP);

            socket.bind("tcp://*:8101");

            while (!Thread.currentThread().isInterrupted())
            {
                byte[] reply = socket.recv(0);

                System.out.println("Received: [ " + new String(reply, ZMQ.CHARSET) + " ]");

                Thread.sleep(2000);

                socket.send("(Hello client)".getBytes(ZMQ.CHARSET), 0);

                System.out.println("==========================================");
            }
        }
    }
}
