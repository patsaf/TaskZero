package taskzero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PersonGenerator {

    private List<Person> personList;

    public PersonGenerator(int quantity) {
        int sum = 0;
        personList = new ArrayList<>();

        while (sum < quantity) {
            try {
                Person person = new Person(generateName(generateLength()), generateName((generateLength())),
                        generateEmail(generateLength()));
                personList.add(person);
                sum++;
            } catch (PersonFormatException e) {}
        }
        Collections.sort(personList);
    }

    public int generateLength() {
        Random r = new Random();
        return (3+r.nextInt(10));
    }

    public String generateName(int length) {
        char[] name = new char[length];
        Random r = new Random();

        for(int i=0; i<length; i++) {
            name[i] = (char) (r.nextInt('z'-'a') + 'a');
        }
        name[0] = Character.toUpperCase(name[0]);
        return new String(name);
    }

    public String generateEmail(int length) {
        final String alphabet = "0123456789abcdefghijklmnopqrestuwyxz";
        final int n = alphabet.length();
        Random r = new Random();
        int domainLength = r.nextInt(5)+3;
        int xLength = r.nextInt(2)+2;
        char[] email = new char[length+domainLength+xLength+2]; //email format will be: username@domain.xxx

        for(int i=0; i<length; i++) {
            email[i] = alphabet.charAt(r.nextInt(n)); //generate username
        }
        email[length] = '@';
        for(int i=length+1; i<=length+domainLength; i++) {
            email[i] = alphabet.charAt(r.nextInt(n)); //generate domain
        }
        email[length+domainLength+1] = '.';
        for(int i=length+domainLength+2; i<email.length; i++) {
            email[i] = (char) (r.nextInt('z'-'a')+'a'); //generate xxx
        }
        return new String(email);
    }

    public void print() {
        int i = 1;
        for(Person x: personList) {
            System.out.println(i+ ". " + x);
            i++;
        }
    }
}