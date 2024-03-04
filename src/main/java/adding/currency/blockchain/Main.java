package adding.currency.blockchain;



import adding.currency.model.RandomMessage;
import adding.currency.person.Person;
import adding.currency.person.PersonFactory;
import adding.currency.person.PersonTask;
import adding.currency.datafeeder.Initializer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberOfThreads = 1;
        PersonFactory clientFactory = PersonFactory.getInstance("client");
        PersonFactory minerFactory = PersonFactory.getInstance("miner");
        try {
            RandomMessage randomMessage = Initializer.initialize();
            List<Thread> clientThreads = new ArrayList<>();
            List<Thread> minerThreads = new ArrayList<>();
            BlockChain blockChain = BlockChain.getInstance(adding.currency.constants.Block.CAPACITY.value);
            while (numberOfThreads < adding.currency.constants.Thread.CAPACITY.value) {
                Person client = clientFactory.createPerson(numberOfThreads, randomMessage.getRandomName(),0);
                Person miner = minerFactory.createPerson(numberOfThreads, "miner" + numberOfThreads, 0);
                Thread clientThread = new Thread(new PersonTask(client));
                Thread minerThread = new Thread(new PersonTask(miner));
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
