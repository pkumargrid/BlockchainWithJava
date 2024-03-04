package adding.currency.constants;

public enum Person {

    PATH(System.getProperty("user.dir") + "/src/datafeeder/persons.ser"),
    TEST_PATH(System.getProperty("user.dir") + "/Blockchain with Java/task/src/datafeeder/persons.ser");

    public final String value;

    Person(String value) {
        this.value = value;
    }

}
