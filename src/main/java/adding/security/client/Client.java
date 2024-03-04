package adding.security.client;

import adding.security.blockchain.BlockChain;
import adding.security.constants.Block;
import adding.security.datafeeder.Initializer;
import adding.security.model.RandomMessage;

import java.io.IOException;
import java.util.Arrays;

public class Client implements Runnable{

    MessageFacade messageFacade;

    public Client() throws Exception {
        messageFacade = MessageFacade.getInstance();
    }

    public void polling() throws IOException, ClassNotFoundException {
        try{
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            while(blockChain.getBlockChainSize() < Block.CAPACITY.value) {
                RandomMessage randomMessage = Initializer.initialize();
                messageFacade.generateRandomMessages(randomMessage.getListOfMessage());
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void run() {
        try {
            polling();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
