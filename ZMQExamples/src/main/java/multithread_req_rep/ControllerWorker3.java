package multithread_req_rep;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.io.IOException;

public class ControllerWorker3
{
    private static class ControllerWorkers implements Runnable{
        private ZContext context;
        private String name;
        ControllerWorkers(ZContext context,String name){
            this.context = context;
            this.name = name;
        }
        public void run(){
            ZMQ.Socket socket = this.context.createSocket(SocketType.ROUTER);
            socket.bind("tcp://*:8092");
            socket.bind("inproc://user");
            while(!Thread.currentThread().isInterrupted()){
                String request = socket.recvStr();
                System.out.println("request received : "+request);
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
//            new Thread(new ControllerWorkers(context, "server2")).start();
//            new Thread(new ControllerWorkers(context, "server3")).start();

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

            new Thread(() -> {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                ZMQ.Socket socket = context.createSocket(SocketType.ROUTER);
                socket.connect("inproc://user");
                socket.send("name");
                System.out.println(socket.recvStr());
            }).start();

            ZMQ.Socket socket1 = context.createSocket(SocketType.ROUTER);
            socket1.connect("inproc://user");
            socket1.send("age");
            System.out.println(socket1.recvStr());



            new Thread(() -> {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
                ZMQ.Socket socket2 = context.createSocket(SocketType.ROUTER);
                socket2.connect("inproc://user");
                socket2.send("height");
                System.out.println(socket2.recvStr());
            }).start();


            socket1.send("id");
            System.out.println(socket1.recvStr());

            System.out.println("all request made");

        }

    }
}
