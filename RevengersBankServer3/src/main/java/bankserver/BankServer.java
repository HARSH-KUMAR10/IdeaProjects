package bankserver;

import bankserver.api.APIs;
import model.SocketControllers;
import model.Utility;
import org.json.JSONObject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankServer
{

    static int clientCount = 0;

    static ServerSocket bankServer;

    private static final String SERVER_STARTED = "Server started at ";

    private static final String SERVER_NOT_STARTED = "Unable to create server, please try again.";

    private static final String UNKNOWN_REQ = "Unknown request:";

    private static final String CLOSE_CONN = "Closing connection ...";

    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    private BankServer()
    {
        createServer();
        startConnections();
    }

    public void createServer()
    {
        try
        {
            bankServer = new ServerSocket(Utility.Dependencies.PORT);

            System.out.println(SERVER_STARTED + Utility.Dependencies.IP
                               + Utility.Delimiters.COLON_DELIMITER + Utility.Dependencies.PORT);
        }
        catch (Exception exception)
        {
            System.out.println(SERVER_NOT_STARTED);
        }
    }

    public void startConnections()
    {
        try
        {
            new Thread(() -> {
                while (true)
                {
                    try
                    {
                        Socket socket = bankServer.accept();

                        System.out.println(Utility.Messages.NEW_CONNECTION + socket.getRemoteSocketAddress());

                        clientCount++;

                        executorService.execute(() -> {
                            try
                            {
                                startReadingClient(new SocketControllers(socket));
                            }
                            catch (Exception exception)
                            {
                                System.out.println(Utility.Messages.INTERNAL_SERVER_ERROR + ": error in socket creation");
                            }
                        });

                    }
                    catch (Exception exception)
                    {
                        System.out.println(Utility.Messages.SERVER_ERROR_RESTART);
                        break;
                    }
                }
            }, Utility.Messages.READ_CONNECTIONS_THREAD).start();
        }
        catch (Exception exception)
        {
            System.out.println(Utility.Messages.SERVER_ERROR_RESTART);
        }
    }

    public void startReadingClient(SocketControllers socketControllers)
    {
        try
        {
            while (true)
            {
                String request = socketControllers.getReader().readLine();

                System.out.println(request);

                if (request != null)
                {
                    JSONObject requestObject = new JSONObject(request);

                    if (!requestObject.isEmpty())
                    {
                        if (requestObject.isNull("route") || requestObject.isNull("operation"))
                        {
                            socketControllers.getWriter().println(Utility.Messages.BAD_REQUEST);
                        }
                        else
                        {
                            System.out.println(requestObject.get("clientAddress") + Utility.Delimiters.ARROW_DELIMITER
                                               + " /" + requestObject.get("route") + "/" + requestObject.get("operation"));
                            new APIs().route(requestObject, socketControllers);
                        }

                    }
                    else
                    {
                        if (request != null)
                            System.out.println(UNKNOWN_REQ + request + Utility.Delimiters.NEW_LINE + CLOSE_CONN);

                        socketControllers.getWriter()
                                .println(Utility.Messages.UNABLE_TO_PARSE);

                        break;
                    }
                }else{
                    socketControllers.getWriter()
                            .println(Utility.Messages.UNABLE_TO_PARSE);
                    break;
                }
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();

            System.out.println(Utility.Messages.INTERNAL_SERVER_ERROR
                               + Utility.Delimiters.COLON_DELIMITER + " " + exception.getMessage());

            socketControllers.getWriter().println(Utility.Messages.INTERNAL_SERVER_ERROR);
        }

    }


    public static void main(String[] args)
    {
        System.out.println(Utility.Messages.WELCOME);
        new BankServer();
    }
}
