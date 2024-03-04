package add.message.blockchain.blockchain;


import add.message.blockchain.client.Client;
import add.message.blockchain.mining.Miner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1;
        try {
            List<Thread> clientThreads = new ArrayList<>();
            List<Thread> minerThreads = new ArrayList<>();
            BlockChain blockChain = BlockChain.getInstance(add.message.blockchain.constants.Block.CAPACITY.value);
            while (numberOfThreads < add.message.blockchain.constants.Thread.CAPACITY.value) {
                Miner miner = new Miner(numberOfThreads);
                Client client = new Client();
                Thread clientThread = new Thread(client, "Client: " + numberOfThreads);
                Thread minerThread = new Thread(miner, "Miner: " + numberOfThreads);
                clientThread.start();
                minerThread.start();
                clientThreads.add(clientThread);
                minerThreads.add(minerThread);
                numberOfThreads++;
            }
            for(Thread thread : minerThreads){
                thread.join();
            }
            for(Thread thread : clientThreads){
                thread.join();
            }
            System.out.println(blockChain);
        } catch (Exception e) {
            System.out.println("Exception occurred: " +  e.getMessage());
            e.printStackTrace();
        }
    }
}
