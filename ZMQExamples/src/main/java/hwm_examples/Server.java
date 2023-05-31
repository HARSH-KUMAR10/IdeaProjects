package hwm_examples;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server
{

    public static void main(String[] args) throws InterruptedException
    {


        ZContext context = new ZContext();

        context.setSndHWM(5);

        ZMQ.Socket socket = context.createSocket(SocketType.PUB);

        socket.bind("tcp://localhost:9999");


        int count = 0;
        while (true)
        {
//            System.out.println(count);
            if (socket.sendMore("Message part-1 - " + count))
            {
                System.out.println("+++++++++++++++++++++" + count);
            }

            if (!socket.send("message last - " + count, ZMQ.NOBLOCK))
            {
                System.out.println("---------------------------------" + count);
            }


            count++;
        }


    }
}
