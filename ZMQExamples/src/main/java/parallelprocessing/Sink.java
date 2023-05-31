package parallelprocessing;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Sink
{
    public static void main(String[] args) throws Exception
    {
        try (ZContext context = new ZContext()) {
            ZMQ.Socket receiver = context.createSocket(SocketType.PULL);
            receiver.bind("tcp://*:8083");


            String string = new String(receiver.recv(0), ZMQ.CHARSET);


            long tstart = System.currentTimeMillis();


            int task_nbr;
            int total_msec = 0;
            for (task_nbr = 0; task_nbr < 100; task_nbr++) {
                string = new String(receiver.recv(0), ZMQ.CHARSET).trim();
                if ((task_nbr / 10) * 10 == task_nbr) {
                    System.out.print(":");
                }
                else {
                    System.out.print(".");
                }
            }


            long tend = System.currentTimeMillis();

            System.out.println(
                    "\nTotal elapsed time: " + (tend - tstart) + " msec"
            );
        }
    }
}
