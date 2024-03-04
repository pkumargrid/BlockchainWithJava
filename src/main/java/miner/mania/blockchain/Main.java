package miner.mania.blockchain;

import miner.mania.mining.Miner;
import miner.mania.constants.Block;
import miner.mania.constants.Thread;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1;
        List<java.lang.Thread> minerThreads = new ArrayList<>();
        try {
            BlockChain blockChain = BlockChain.getInstance(Block.CAPACITY.value);
            while (numberOfThreads < Thread.CAPACITY.value) {
                Miner miner = new Miner(numberOfThreads);
                java.lang.Thread minerThread = new java.lang.Thread(miner);
                minerThread.start();
                minerThreads.add(minerThread);
                numberOfThreads++;
            }
            for(java.lang.Thread thread : minerThreads){
                thread.join();
            }
            System.out.println(blockChain);
        } catch (Exception e) {
            System.out.println("Exception occurred: " +  e.getMessage());
            e.printStackTrace();
        }
    }
}
