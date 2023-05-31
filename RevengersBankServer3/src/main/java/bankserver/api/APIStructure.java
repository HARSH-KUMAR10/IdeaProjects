package bankserver.api;

import model.SocketControllers;
import org.json.JSONObject;

public interface APIStructure
{
    void route(JSONObject values, SocketControllers socketControllers);
}
