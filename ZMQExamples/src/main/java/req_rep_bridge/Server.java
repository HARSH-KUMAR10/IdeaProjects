package req_rep_bridge;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Server
{
    public static void main(String[] args)
    {
        new Thread(new ServerThread("server1")).start();
        new Thread(new ServerThread("server2")).start();
        new Thread(new ServerThread("server3")).start();
    }
}


class  ServerThread implements  Runnable
{

    String name;

    ServerThread(String name)
    {
        this.name = name;
    }

    public void run()
    {

        ZContext context = new ZContext();

        ZMQ.Socket socket = context.createSocket(SocketType.REP);

        socket.connect("tcp://localhost:8091");

        while (true)
        {

            String request = socket.recvStr();


            switch (request.intern())
            {

                case "hiii":

                    socket.send("hello " + name);
                    break;
                case "bye":
                    socket.send("goodbye " + name);
                    break;
                case "how are you?":
                    socket.send("fine " + name);
                    break;

            }

            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                System.out.println(e);
            }
        }

    }


}