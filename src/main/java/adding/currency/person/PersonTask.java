package adding.currency.person;

public class PersonTask implements Runnable {

    private final Person person;

    public PersonTask(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        person.execute();
    }
}
