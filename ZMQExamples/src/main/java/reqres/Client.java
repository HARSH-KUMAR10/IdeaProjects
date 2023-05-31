package reqres;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext())
        {
            System.out.println("trying to connect to server ...");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);

            socket.connect("tcp://localhost:8100");

            socket.connect("tcp://localhost:8080");

            for (int requestNbr = 0; requestNbr < 10; requestNbr++)
            {
                Thread.sleep(500);

                System.out.println("Sending request-" + requestNbr + " to "+socket.getPlainUsername());

                socket.send(("Hello server x " + requestNbr).getBytes(ZMQ.CHARSET), 0);

                byte[] reply = socket.recv(0);

                System.out.println("Received: [ " + new String(reply, ZMQ.CHARSET) + " - " + requestNbr+" ]");

                System.out.println("==========================================");
            }
        }
    }
}
