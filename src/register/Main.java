package register;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        Register register = new ArrayRegister(20);
        ListRegister register = new ListRegister();

        try (FileInputStream is = new FileInputStream(register.getFilename());
             ObjectInputStream ois = new ObjectInputStream(is)) {
            List<Person> readPerson = (List<Person>) ois.readObject();
            for (Person de : readPerson) {
                register.addPerson(de);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No previous records exist or file 'RegisterFile.bin' is corrupt.");
        }

        register.addPerson(new Person("Janko Hrasko", "0900123456"));

        ConsoleUI ui = new ConsoleUI(register);
        ui.run();
    }
}
