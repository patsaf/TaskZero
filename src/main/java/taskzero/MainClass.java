package taskzero;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;
        System.out.println("How many instances would you like to generate?");
        input = sc.nextLine();
        PersonGenerator pg = new PersonGenerator(Integer.parseInt(input));
        pg.print();
    }
}
