package company.employees;

import company.managers.TeamManager;

import java.util.Random;

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
    private final char[] genders = {'K', 'M'};
    private Employee employee;
    private final Random r = new Random();

    public EmployeeFactory(EmployeeType type, EmployeeRole role, int capacity) {
        char g = generate(genders);
        if(type == EmployeeType.MANAGER) {
            employee = new TeamManager.ManagerBuilder(role)
                    .capacity(capacity)
                    .name((g=='K' ? generate(femaleNames) : generate(maleNames)), generate(lastNames))
                    .university(generate(universities))
                    .gender(g)
                    .country(generate(countries))
                    .email(generate(emails))
                    .build();
        } else {
            employee = new Developer.DeveloperBuilder(role)
                    .name((g=='K' ? generate(femaleNames) : generate(maleNames)), generate(lastNames))
                    .university(generate(universities))
                    .gender(g)
                    .country(generate(countries))
                    .email(generate(emails))
                    .build();
        }
    }

    public Employee getEmployee() { return employee; }

    private String generate(String[] table) {
        int n = table.length;
        return table[r.nextInt(n)];
    }

    private char generate(char[] table) {
        int n = table.length;
        return table[r.nextInt(n)];
    }
}
