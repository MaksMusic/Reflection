package Test.model.bank;

import Test.model.Person;

import java.util.ArrayList;
import java.util.List;

public class BankFantom extends Bank implements WorkWithFinance,Translations{
    private double balance = 100_000;
    private String name  = "FANTOM BANK";
    private List<Person> personList = new ArrayList<>(100);


    public BankFantom(double balance, String name) {
        this.balance = balance;
        this.name = name;

    }

    public BankFantom() {
        personList.add(new Person("Tomas",29));
    }

    public void addPerson(Person person){
        personList.add(person);
        System.out.println(person.getName() + " добавлен");
    }

    public void printPersonList(){
        personList.forEach(System.out::println);
    }

    public String getName() {
        return name;
    }
}
