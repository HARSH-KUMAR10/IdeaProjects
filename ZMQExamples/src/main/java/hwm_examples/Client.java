package hwm_examples;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Client
{
    public static void main(String[] args) throws InterruptedException
    {


        ZContext context = new ZContext();

        context.setRcvHWM(1);


        ZMQ.Socket socket = context.createSocket(SocketType.SUB);

        socket.connect("tcp://localhost:9999");

        socket.subscribe("");


        while (true)
        {
            Thread.sleep(500);
            System.out.println(socket.recvStr(ZMQ.NOBLOCK));
            System.out.println(socket.recvStr(ZMQ.NOBLOCK));
            System.out.println(socket.recvStr(ZMQ.NOBLOCK));
        }
    }
}
