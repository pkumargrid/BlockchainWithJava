package adding.currency.client;

import adding.currency.blockchain.BlockChain;
import adding.currency.exceptions.ValidationException;
import adding.currency.person.Person;
import adding.currency.person.PersonManager;
import adding.currency.person.SerializationUtils;
import adding.currency.transaction.TransactionFacade;
import adding.security.constants.Block;

import java.io.Serializable;

public class Client implements Person, Serializable {

    private final String name;

    private int VC;

    public Client(int clientId, String name, int VC) {
        this.name = name;
        this.VC = VC;
    }

    @Override
    public void execute() {
        try{
            PersonManager.getInstance().add(this);
            SerializationUtils.serialize(PersonManager.getInstance());
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            TransactionFacade transactionFacade = TransactionFacade.getInstance();
            while(blockChain.getBlockChainSize() < Block.CAPACITY.value) {
                try {
                    transactionFacade.makeTransaction(this);
                } catch (ValidationException ignored){

                }
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override

    public String getName() {
        return name;
    }

    @Override
    public int getVC() {
        return VC;
    }

    public void setVC(int VC) {
        this.VC = VC;
    }
}
