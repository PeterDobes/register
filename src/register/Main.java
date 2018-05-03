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

    public static void main(String[] args) {
//        Register register = new ArrayRegister(20);
        ListRegister register = new ListRegister();

        //register.addPerson(new Person("Janko Hrasko", "0900123456"));

        ConsoleUI ui = new ConsoleUI(register);
        ui.run();
    }
}
