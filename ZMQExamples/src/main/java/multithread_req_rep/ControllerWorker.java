package multithread_req_rep;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public class ControllerWorker
{
    private static class ControllerWorkers implements Runnable{
        private ZContext context;
        private String name;
        ControllerWorkers(ZContext context,String name){
            this.context = context;
            this.name = name;
        }
        public void run(){
            ZMQ.Socket socket = this.context.createSocket(SocketType.REP);
            socket.bind("inproc://user");
            while(!Thread.currentThread().isInterrupted()){
                String request = socket.recvStr();
                switch (request){
                    case "name"-> socket.send("Harsh "+name);
                    case "age" -> socket.send("20 "+name);
                    case "id" -> socket.send("10 "+name);
                    default -> socket.send("Bad request "+name);
                }
            }
        }
    }

    public static void main(String[] args)
    {

        try(ZContext context = new ZContext())
        {

            System.out.println("starting workers ...");

            new Thread(new ControllerWorkers(context, "server1")).start();
            new Thread(new ControllerWorkers(context, "server2")).start();
            new Thread(new ControllerWorkers(context, "server3")).start();

            System.out.println("workers started ...");


            try
            {
                System.in.read();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }



            System.out.println("String to make requests ...");

            ZMQ.Socket socket = context.createSocket(SocketType.REQ);

            new Thread(() -> {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                socket.send("name");
                System.out.println(socket.recvStr());
            });


            socket.send("age");
            System.out.println(socket.recvStr());



            new Thread(() -> {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                socket.send("height");
                System.out.println(socket.recvStr());
            });


            socket.send("id");
            System.out.println(socket.recvStr());

            System.out.println("all request made");

        }

    }
}
