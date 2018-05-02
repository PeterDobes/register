package register;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User interface of the application.
 */
public class ConsoleUI {
    /** arrayRegister.ArrayRegister of persons. */
    private ListRegister register;
    
    /**
     * In JDK 6 use Console class instead.
     * @see //readLine()
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Menu options.
     */
    private enum Option {
        PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
    }
    
    public ConsoleUI(ListRegister register) {
        this.register = register;
    }
    
    public void run() {
        while (true) {
            switch (showMenu()) {
                case PRINT:
                    printRegister();
                    break;
                case ADD:
                    addToRegister();
                    break;
                case UPDATE:
                    updateRegister();
                    break;
                case REMOVE:
                    removeFromRegister();
                    break;
                case FIND:
                    findInRegister();
                    break;
                case EXIT:
                    saveRegister();
                    return;
            }
        }
    }

    private void saveRegister() {
        register.saveRegisterIntoFile();
    }

    private String readLine() {
        //In JDK 6.0 and above Console class can be used
        //return System.console().readLine();
        
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }
    
    private Option showMenu() {
        System.out.println("Menu.");
        for (Option option : Option.values()) {
            System.out.printf("%d. %s%n", option.ordinal() + 1, option);
        }
        System.out.println("-----------------------------------------------");
        
        int selection = -1;
        String input;
        do {
            System.out.println("Option: ");
            input = readLine();
            if (!Pattern.matches("[\\D]*", input)) {
                selection = Integer.parseInt(input);
            } else {
                System.out.println("Wrong input, try again");
            }
        } while (selection <= 0 || selection > Option.values().length);
        
        return Option.values()[selection - 1];
    }

    private void printRegister() {
        for(int i = 0; i < register.getCount(); i++) {
            System.out.println((i+1)+". "+ register.getPerson(i).toString());
        }
    }
    
    private void addToRegister() {
        System.out.println("Enter Name: ");
        String name = readLine();
        System.out.println("Enter Phone Number: ");
        String phoneNumber = readLine();
        for(int i = 0; i < register.getCount(); i++) {
            if(register.findPersonByName(name) != null ||
                    register.findPersonByPhoneNumber(phoneNumber) != null) {
                System.out.println("Person with this name or phone number already exists");
                return;
            }
        }
        register.addPerson(new Person(name, phoneNumber));
    }

    private void updateRegister() {
        int index = 0;
        String input;
        printRegister();
        do {
            System.out.println("Option: ");
            input = readLine();
            if (!Pattern.matches("\\D*", input)) {
                index = Integer.parseInt(input);
            } else {
                System.out.println("Wrong input, try again");
            }
        } while (index <= 0 || index > register.getCount());

        System.out.println("Enter new name: ");
        String name = readLine();
        register.getPerson(index-1).setName(name);
        System.out.println("Enter new phone number: ");
        String phone = readLine();
        register.getPerson(index-1).setPhoneNumber(phone);
    }

    private void findInRegister() {
        System.out.println("Enter attribute to search by: ");
        String input = readLine();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(input);
        Person result;
        if(m.matches()) {
            result = register.findPersonByPhoneNumber(input);
            if (result == null) {
                result = register.findPersonByName(input);
            }
        } else {
            result = register.findPersonByName(input);
        }
        if(result == null){
            System.out.println("Nothing found");
        } else {
            System.out.println(result.toString());
        }
    }
    
    private void removeFromRegister() {
        int index = -1;
        String input;
        do {
            System.out.println("Enter index: ");
            input = readLine();
            if (Pattern.matches("\\D*", input)) {
                System.out.println("Wrong input, try again");
            } else {
                index = Integer.parseInt(input);
            }
        } while (index <= 0 || index > register.getCount());
        Person person = register.getPerson(index - 1);
        register.removePerson(person);
    }

}
