package req_rep_bridge;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Broker
{
    public static void main(String[] args)
    {

        ZContext context = new ZContext();
      //  ZContext backendContext = new ZContext();


        ZMQ.Socket frontend = context.createSocket(SocketType.ROUTER);
        ZMQ.Socket backend = context.createSocket(SocketType.DEALER);

        frontend.bind("tcp://localhost:8090");
        backend.bind("tcp://localhost:8091");

        ZMQ.Poller item = context.createPoller(2);

        item.register(frontend , ZMQ.Poller.POLLIN);
        item.register(backend , ZMQ.Poller.POLLIN);

        boolean more = false;

        byte message[];

        while(true)
        {
            item.poll();

            if(item.pollin(0))
            {

                while(true)
                {
                    message = frontend.recv();

                    more = frontend.hasReceiveMore();


                    backend.send(message, more ? ZMQ.SNDMORE : 0);
                    if (!more) {
                        break;
                    }

                }
            }

            if(item.pollin(1))
            {

                while (true)
                {
                    message = backend.recv();

                    more = backend.hasReceiveMore();


                    frontend.send(message, more ? ZMQ.SNDMORE : 0);
                    if (!more) {
                        break;
                    }
                } message = frontend.recv();

                    more = frontend.hasReceiveMore();


                    backend.send(message, more ? ZMQ.SNDMORE : 0);
                    if (!more) {
                        break;
                    }


            }



        }



    }

}
