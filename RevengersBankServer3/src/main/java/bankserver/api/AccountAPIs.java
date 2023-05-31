package bankserver.api;

import bankserver.service.ServerAccountServices;
import model.SocketControllers;
import model.Utility;
import org.json.JSONObject;

public class AccountAPIs implements APIStructure
{
    public void route(JSONObject values, SocketControllers socketControllers){
        switch (values.getString("operation"))
        {
            case Utility.Keyword.CREATE -> socketControllers.getWriter()
                    .println(ServerAccountServices.createAccount(values));

            case Utility.Keyword.READ -> socketControllers.getWriter()
                    .println(ServerAccountServices.loginAccount(values));

//            case Utility.Keyword.LOGOUT -> socketControllers.getWriter()
//                    .println(ServerAccountServices.logoutAccount(values));

            default -> socketControllers.getWriter()
                    .println(Utility.Keyword.API_ACTION_ACCOUNT + Utility.Messages.ROUTE_UNSUPPORTED);
        }
    }
}
