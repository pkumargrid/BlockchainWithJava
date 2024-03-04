package adding.currency.mining;


import adding.currency.blockchain.BlockChain;
import adding.currency.constants.Block;
import adding.currency.person.Person;
import adding.currency.person.PersonManager;
import adding.currency.person.SerializationUtils;

import java.io.Serializable;

public class Miner implements Person, Serializable {

    private final int minerId;

    private final String name;

    private int VC;

    public Miner(int minerId, String name, int VC) {
        this.minerId = minerId;
        this.name = name;
        this.VC = VC;
    }

    @Override
    public void execute(){
        try {
            PersonManager.getInstance().add(this);
            SerializationUtils.serialize(PersonManager.getInstance());
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            blockChain.addBlock(this);
        } catch (Exception e){
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getMinerId() {
        return minerId;
    }

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
