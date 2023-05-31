package bankserver.api;

import bankserver.service.ServerBankServices;
import model.SocketControllers;
import model.Utility;
import org.json.JSONObject;

public class BankAPIs //implements APIStructure
{
    public void route(JSONObject values, SocketControllers socketControllers){
        switch (values.getString("operation"))
        {
            case Utility.Keyword.DEPOSIT -> socketControllers.getWriter()
                    .println(ServerBankServices.deposit(values));

            case Utility.Keyword.WITHDRAWAL -> socketControllers.getWriter()
                    .println(ServerBankServices.withdrawal(values));
//
//            case Utility.Keyword.DETAILS -> socketControllers.getWriter()
//                    .println(ServerBankServices.details(values));
//
//            case Utility.Keyword.TRANSFER -> socketControllers.getWriter()
//                    .println(ServerBankServices.fundTransfer(values));
//
//            case Utility.Keyword.PASSBOOK -> socketControllers.getWriter()
//                    .println(ServerBankServices.passbook(values));

            default -> socketControllers.getWriter()
                    .println(Utility.Keyword.API_ACTION_BANK + Utility.Messages.ROUTE_UNSUPPORTED);
        }
    }
}
