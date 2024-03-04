package adding.currency.person;


import java.io.*;
import adding.currency.constants.Person;

public class SerializationUtils {

    public static void serialize(PersonManager personManager) throws IOException {
        ObjectOutputStream objectInputStream = new ObjectOutputStream(new FileOutputStream(Person.PATH.value));
        objectInputStream.writeObject(personManager);
    }

    public static PersonManager deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(Person.PATH.value));
        return (PersonManager) objectInputStream.readObject();
    }

}
