package register;

import java.util.ArrayList;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
    /** register.Person array. */
    private Person[] persons;
    
    /** Number of persons in this register. */
    private int count;
    
    /**
     * Constructor creates an empty register with maximum size specified.
     * @param size maximum size of the register
     */
    public ArrayRegister(int size) {
        persons = new Person[size];
        count = 0;
    }
    
    /**
     * Returns the number of persons in this register.
     * @return the number of persons in this register
     */
    @Override
    public int getCount() {
        return count;
    }
    
    /**
     * Returns the maximum number of persons in this register.
     * @return the maximum number of persons in this register.
     */
    @Override
    public int getSize() {
        return persons.length;
    }
    
    /**
     * Returns the person at the specified position in this register.
     * @param index index of the person to return 
     * @return person the person at the specified position in this register 
     */
    @Override
    public Person getPerson(int index) {
        return persons[index];
    }

    /**
     * Appends the specified person to the end of this register. 
     * @param person person to append to this register
     */
    @Override
    public void addPerson(Person person) {
        if(findPersonByName(person.getName()) == null &&
                findPersonByPhoneNumber(person.getPhoneNumber()) == null) {
            if (getCount() == getSize()) {
                Person[] newPersons = new Person[getSize() + 20];
                System.arraycopy(persons, 0, newPersons, 0, persons.length);
                persons = newPersons;
            }
            persons[count] = person;
            count++;
        }
    }       

    /**
     * Returns the person with specified name in this register or <code>null</code>,
     * if match can not be found.
     * @param name name of a person to search for
     * @return person with specified phone number
     */
    @Override
    public Person findPersonByName(String name) {
        for(int i = 0; i < getCount(); i++) {
            if(persons[i].getName().equals(name)) {
                return persons[i];
            }
        }
        return null;
    }
    

    /**
     * Returns the person with specified phone number in this register or <code>null</code>,
     * if match can not be found.
     * @param phoneNumber phone number of a person to search for
     * @return person with specified phone number
     */
    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        for(int i = 0; i < getCount(); i++) {
            if(persons[i].getPhoneNumber().equals(phoneNumber)) {
                return persons[i];
            }
        }
        return null;
    }

    /**
     * Removes the specified person from the register.
     * @param person person to remove
     */
    @Override
    public void removePerson(Person person) {
        boolean fix = false;
        for(int i = 0; i < getCount(); i++) {
            if(persons[i].equals(person)) {
                fix = true;
                count--;
            }
            if(fix) {
                persons[i] = persons[i+1];
            }
        }
    }
}
