package company.employees;

import company.employees.details.*;
import company.managers.TeamManager;
import company.predicates.PredicateFactory;
import company.predicates.PredicateInfo;
import company.predicates.Predicates;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
    private final String[] countries = {"Poland", "Ukraine", "Russia", "USA", "Spain", "France", "UK",
            "Portugal", "Germany", "Ecuador", "Colombia", "Norway", "Sweden", "Denmark", "Iceland", "Ireland",
            "Cech Repblic", "Argentine", "Brasil", "Mexico"};
    private final String[] domains = {"op.pl", "gmail.com", "domena.xd", "wp.pl"};
    private Employee employee;
    private final Random r = new Random();

    public EmployeeFactory(EmployeeType type, EmployeeRole role, int capacity) {
        Gender g = generateGender();
        int number = r.nextInt(Predicates.values().length);
        String[] table = chooseTable(number);
        String conditionValue = table[r.nextInt(table.length)];
        Predicates pred = Predicates.values()[number];
        Predicate<Employee> p = generatePredicate(pred, conditionValue);
        if(type == EmployeeType.MANAGER) {
            employee = new TeamManager.ManagerBuilder(role)
                    .capacity(capacity)
                    .gender(g)
                    .name((g.equals(Gender.FEMALE) ? new FirstName(generate(femaleNames)) :
                            new FirstName(generate(maleNames))), new LastName(generate(lastNames)))
                    .university(new University(generate(universities)))
                    .country(new Country(generate(countries)))
                    .email(new Email(generate(emails)))
                    .hiringCondition(new PredicateInfo(pred, conditionValue))
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

    private String[] chooseTable(int i) {
        String[] table = {""};
        switch(i) {
            case(0):
                break;
            case(3):
                table = countries;
                break;
            case(1):
                table = universities;
                break;
            case(4):
                table = domains;
                break;
            case(2):
                table = Stream.of(Gender.values())
                        .map(Gender::name)
                        .toArray(String[]::new);
                break;
        }
        return table;
    }

    private Predicate<Employee> generatePredicate(Predicates condition, String value){
        switch(condition) {

            case EMPTY:
                return PredicateFactory.noCondition();
            case GENDER:
                Gender g = null;
                for(Gender x : Gender.values()) {
                    if(x.name().toLowerCase().equals(value)) {
                        g = x;
                    }
                }
                return PredicateFactory.chooseGender(g);
            case COUNTRY:
                return PredicateFactory.chooseCountry(new Country(value));
            case UNIVERSITY:
                return PredicateFactory.chooseUniversity(new University(value));
            case EMAIL:
                return PredicateFactory.chooseEmailDomain(value);
            default:
                return PredicateFactory.noCondition();
        }
    }

    public String getFemaleName() {
        return generate(femaleNames);
    }

    public String getMaleName() {
        return generate(maleNames);
    }

    public String getLastName() {
        return generate(lastNames);
    }

    public String getUniversity() {
        return generate(universities);
    }

    public String getCountry() {
        return generate(countries);
    }

    public String getEmail() {
        return generate(emails);
    }
}
