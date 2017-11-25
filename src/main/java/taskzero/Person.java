package taskzero;

import com.sun.media.sound.InvalidFormatException;

import java.util.regex.Pattern;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) throws PersonFormatException {

           if (Pattern.matches("[A-Z][a-z]+", firstName)) {
               this.firstName = firstName;
           } else {
               throw new PersonFormatException("Invalid First Name Format!");
           }

           if(Pattern.matches("[A-Z][a-z]+", lastName)) {
               this.lastName = lastName;
           } else {
               throw new PersonFormatException("Invalid Last Name Format!");
           }

           if(Pattern.matches("[a-z0-9]+@[a-z]+\\.[a-z]{2,3}", email)) {
               this.email = email;
           } else {
               throw new PersonFormatException("Invalid Email Format!");
           }
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName
                + "\nEmail address: " + email + "\n";
    }

    @Override
    public int compareTo(Person p) {
        int compareFirst;
        int compareLast;
        compareFirst = firstName.compareTo(p.firstName);

        if (compareFirst == 0) {
            compareLast = lastName.compareTo(p.lastName);
            if (compareLast == 0) {
                return email.compareTo(p.email);
            } else {
                return compareLast;
            }
        } else {
            return compareFirst;
        }
    }
}
