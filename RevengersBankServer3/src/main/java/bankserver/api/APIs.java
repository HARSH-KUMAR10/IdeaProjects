package bankserver.api;

import model.SocketControllers;
import model.Utility;
import org.json.JSONObject;

public class APIs implements APIStructure
{
    public void route(JSONObject requestBody,SocketControllers socketControllers)
    {
        switch (requestBody.get("route").toString())
        {
            case Utility.Keyword.API_ACTION_ACCOUNT ->
                new AccountAPIs().route(requestBody, socketControllers);

            case Utility.Keyword.API_ACTION_BANK ->
                new BankAPIs().route(requestBody, socketControllers);

            default -> socketControllers.getWriter().println(Utility.Messages.ROUTE_UNSUPPORTED);

        }
    }
}
