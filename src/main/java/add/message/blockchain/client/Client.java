package add.message.blockchain.client;


import add.message.blockchain.blockchain.BlockChain;
import add.message.blockchain.constants.Block;
import add.message.blockchain.datafeeder.Initializer;
import add.message.blockchain.model.RandomMessage;

import java.io.IOException;

public class Client implements Runnable{

    MessageFacade messageFacade;

    public Client() {
        messageFacade = MessageFacade.getInstance();
    }

    public void polling() throws IOException, ClassNotFoundException {
        BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
        while(blockChain.getBlockChainSize() < Block.CAPACITY.value){
            RandomMessage randomMessage = Initializer.initialize();
            messageFacade.generateRandomMessages(randomMessage.getListOfMessage());
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
