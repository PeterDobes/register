package register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListRegister implements Register {

    private int count;
    private List<Person> persons;

    public ListRegister() {
        persons = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public int getSize() {
        return persons.size();
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
