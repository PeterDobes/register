package register;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListRegister implements Register, Serializable {

    private int count;
    private List<Person> persons;
    public static final String FILENAME = "RegisterFile.bin";

    public ListRegister() {
        persons = new ArrayList<>();
    }

    public void saveRegisterIntoFile() {
        try (FileOutputStream os = new FileOutputStream(FILENAME);
             ObjectOutputStream oos = new ObjectOutputStream(os);) {
            oos.writeObject(persons);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

        @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getSize() {
        return persons.size();
    }

    public String getFilename() {
        return FILENAME;
    }

    @Override
    public Person getPerson(int index) {
        return persons.get(index);
    }

    @Override
    public void addPerson(Person person) {
        if(findPersonByName(person.getName()) == null &&
                findPersonByPhoneNumber(person.getPhoneNumber()) == null) {
            persons.add(person);
            count++;
        }
    }

    @Override
    public Person findPersonByName(String name) {
        for(int i = 0; i < getCount(); i++) {
            if(persons.get(i).getName().equals(name)) {
                return persons.get(i);
            }
        }
        return null;
    }

    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        for(int i = 0; i < getCount(); i++) {
            if(persons.get(i).getPhoneNumber().equals(phoneNumber)) {
                return persons.get(i);
            }
        }
        return null;
    }

    @Override
    public void removePerson(Person person) {
        if (persons.contains(person)) {
            persons.remove(person);
            count--;
        }
    }
}
