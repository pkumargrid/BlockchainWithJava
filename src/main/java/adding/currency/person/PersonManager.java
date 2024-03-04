package adding.currency.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonManager implements Serializable {

    List<Person> persons;
    private static volatile PersonManager instance;

    private PersonManager() {
        persons = new ArrayList<>();
    }

    public static synchronized PersonManager getInstance() {
        if (instance == null) {
            instance = new PersonManager();
        }
        return instance;
    }

    public void add(Person person) {
        persons.add(person);
    }

    public List<Person> getPersons() {
        return persons;
    }
}
