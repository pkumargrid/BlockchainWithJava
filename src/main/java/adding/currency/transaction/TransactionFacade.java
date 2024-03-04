package adding.currency.transaction;

import adding.currency.client.Client;
import adding.currency.message.handler.MessageFacade;
import adding.currency.mining.Miner;
import adding.currency.person.PersonManager;
import adding.currency.person.SerializationUtils;
import adding.currency.person.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionFacade {

    public static volatile  TransactionFacade instance;

    Random random;

    private TransactionFacade(){
        random = new Random();
    }

    private List<Person> extract(List<Person> persons, Person person) {
        List<Person> clients = new ArrayList<>();
        for(Person p : persons) {
            if (p instanceof Client) {
                clients.add(p);
            }
        }
        return modify(clients, person);
    }

    private List<Person> modify(List<Person> persons, Person person) {
        List<Person> listOfPerson = new ArrayList<>();
        for(Person p : persons) {
            if (!person.getName().equals(p.getName())) {
                listOfPerson.add(p);
            }
        }
        return listOfPerson;
    }

    public synchronized void makeTransaction(Person person) throws Exception {
        MessageFacade messageFacade = MessageFacade.getInstance();
        PersonManager personManager = SerializationUtils.deserialize();
        List<Person> persons = personManager.getPersons();
        if(person instanceof Client client){
            List<Person> clients = extract(persons, client);
            if (clients.isEmpty()) return;
            int index = random.nextInt(clients.size());
            int vc = 1 +  random.nextInt(client.getVC() + 1);
            messageFacade.add(new Transaction(person, clients.get(index), vc));
        }
        else if (person instanceof Miner miner){
            List<Person> listOfPerson = modify(persons, miner);
            if (listOfPerson.isEmpty()) return;
            int index = random.nextInt(listOfPerson.size());
            int vc = 1 + random.nextInt(miner.getVC() + 1);
            messageFacade.add(new Transaction(miner, listOfPerson.get(index), vc));
        }
        else {
            throw new IllegalAccessException("Unknown type of Person " + person);
        }
    }

    public static TransactionFacade getInstance() {
        if (instance == null) {
            instance = new TransactionFacade();
        }
        return instance;
    }
}
