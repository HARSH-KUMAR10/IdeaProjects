package erp.database;

import erp.logins.AbstractCredentials;

import java.util.HashMap;

public class LoginDB
{
    private static HashMap<String,AbstractCredentials> loginMap = new HashMap<>();

    public boolean addLogin(String userName,AbstractCredentials loginCredentials){
        try{
            loginMap.put(userName,loginCredentials);
            return true;
        }catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    public String getLogin(String email,String password){
        try{
            String userName = email.split("@")[0];
            AbstractCredentials credentials = loginMap.get(userName);
            if(credentials.getEmail().equals(email) && credentials.getPassword().equals(credentials.getPassPhraseSaltValue()+password)){
                return userName;
            }
            return "";
        }catch(Exception e){
            return null;
        }
    }
    public HashMap<String,AbstractCredentials> showAllUsers(){
        return loginMap;
    }

}
