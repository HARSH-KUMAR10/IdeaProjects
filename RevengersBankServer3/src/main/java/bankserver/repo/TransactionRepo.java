package bankserver.repo;

import model.Transaction;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionRepo implements OperationStructure
{

    private static TransactionRepo transactionRepoObj = null;

    private static final HashMap<String, ArrayList<Transaction>> transactions = new HashMap<>();

    private TransactionRepo(){}

    public static synchronized TransactionRepo getInstance(){
        if(transactionRepoObj == null){
            transactionRepoObj = new TransactionRepo();
        }
        return transactionRepoObj;
    }

    @Override
    public Boolean create(String... args)
    {
        ArrayList<Transaction> transactionsList;
        if(transactions.containsKey(args[0])){
            transactionsList = transactions.get(args[0]);
        }else{
            transactionsList = new ArrayList<>();
        }
        transactionsList.add(new Transaction(args[0],args[1],args[2],args[3],args[4],args[5]));
        transactions.put(args[0],transactionsList);
        return true;
    }

    @Override
    public ArrayList<Transaction> read(String... args)
    {
        return transactions.get(args[0]);
    }

    @Override
    public Object update(String... args)
    {
        return null;
    }

    @Override
    public Boolean delete(String... args)
    {
        transactions.put(args[0],new ArrayList<>());
        return true;
    }
}
