package company.employees;

import company.employees.details.*;
import company.managers.TeamManager;

import java.util.Random;
import java.util.function.Predicate;

public class EmployeeFactory {

    private final String[] femaleNames = {"Alicja", "Jolanta", "Aleksandra", "Wioletta", "Katarzyna", "Klara", "Blanka",
            "Lena", "Maja", "Oliwia"};
    private final String[] maleNames = {"Jakub", "Adam", "Piotr", "Krzysztof", "Janusz", "Marcin", "Maciej", "Bartosz",
            "Jan", "Szymon"};
    private final String[] lastNames = {"Marchewka", "Burak", "Grabowski", "Stec", "Klimek", "Domagala", "Staniaszek",
            "Cypcar"};
    private final String[] emails = {"mojemail@op.pl", "randomemail@gmail.com", "taki_email@domena.xd",
            "123niemampomyslu@wp.pl", "jeszczejedennagmailu@gmail.com", "ikolejny@gmail.com"}; //TODO: ensure there are no duplicate emails
    private final String[] universities = {"AGH", "Politechnika Krakowska", "UJ", "Politechnika Warszawska",
            "Politechnika Wrocławska"};
    private final String[] countries = {"Polska", "Ukraina", "Rosja", "USA", "Hiszpania", "Francja", "Wielka Brytania",
            "Portugalia", "Niemcy", "Ekwador", "Kolumbia", "Norwegia", "Szwecja", "Dania", "Islandia", "Irlandia",
            "Czechy", "Argentyna", "Brazylia", "Meksyk"};
    private final String[] domains = {"op.pl", "gmail.com", "domena.xd", "wp.pl"};
    private Employee employee;
    private final Random r = new Random();

    public EmployeeFactory(EmployeeType type, EmployeeRole role, int capacity) {
        Gender g = generateGender();
        if(type == EmployeeType.MANAGER) {
            employee = new TeamManager.ManagerBuilder(role)
                    .capacity(capacity)
                    .gender(g)
                    .name((g.equals(Gender.FEMALE) ? new FirstName(generate(femaleNames)) :
                            new FirstName(generate(maleNames))), new LastName(generate(lastNames)))
                    .university(new University(generate(universities)))
                    .country(new Country(generate(countries)))
                    .email(new Email(generate(emails)))
                    .hiringCondition(generatePredicate(Predicates.values()[r.nextInt(Predicates.values().length)]))
                    .build();
        } else {
            employee = new Developer.DeveloperBuilder(role)
                    .name((g.equals(Gender.FEMALE) ? new FirstName(generate(femaleNames))
                            : new FirstName(generate(maleNames))), new LastName(generate(lastNames)))
                    .university(new University(generate(universities)))
                    .gender(g)
                    .country(new Country(generate(countries)))
                    .email(new Email(generate(emails)))
                    .build();
        }
    }

    public Employee getEmployee() { return employee; }

    private String generate(String[] table) {
        int n = table.length;
        return table[r.nextInt(n)];
    }

    private Gender generateGender() {
        return Gender.values()[r.nextInt(Gender.values().length)];
    }

    private Predicate<Employee> generatePredicate(Predicates condition){
        switch(condition) {

            case EMPTY:
                return PredicateFactory.noCondition();
            case GENDER:
                return PredicateFactory.chooseGender(generateGender());
            case COUNTRY:
                return PredicateFactory.chooseCountry(new Country(generate(countries)));
            case UNIVERSITY:
                return PredicateFactory.chooseUniversity(new University(generate(universities)));
            case EMAIL:
                return PredicateFactory.chooseEmailDomain(generate(domains));
            case ROLE:
                return PredicateFactory.chooseRole(EmployeeRole.values()[r.nextInt(EmployeeRole.values().length)]);
            default:
                return PredicateFactory.noCondition();
        }
    }
}
