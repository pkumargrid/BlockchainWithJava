package adding.currency.person;


import adding.currency.client.Client;
import adding.currency.mining.Miner;


public interface PersonFactory {

    Person createPerson(int id, String name, int VC) throws Exception;

    static PersonFactory getInstance(String type) {
        if(type.equalsIgnoreCase("client")) {
            return Client::new;
        }
        else if(type.equalsIgnoreCase("miner")) {
            return Miner::new;
        }
        else{
            throw new IllegalArgumentException("Unknown person type: " + type);
        }
    }
}
