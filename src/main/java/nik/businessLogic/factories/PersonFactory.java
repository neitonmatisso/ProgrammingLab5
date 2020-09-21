package nik.businessLogic.factories;

import nik.businessLogic.sourceClasses.Country;
import nik.businessLogic.sourceClasses.Person;

import java.util.Scanner;

public final class PersonFactory {

    public Person createPerson(){
        System.out.println("Введите информацию о владельце продукта");
        String name = createName();
        String passportID = createPassportID();
        Country nationaluty = createNationality();
        return new Person(name,passportID,nationaluty);

    }

    public String createName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя человека");
        return scanner.nextLine();
    }

    public String createPassportID(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите айди паспорта человека");
        return scanner.nextLine();
    }

    public Country createNationality(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите национальность для челоека. Варианты " +
                "1: - русский " +
                " 2: - индеец " +
                " 3: - француз ");
        while (true) {
            switch (scanner.nextLine()) {
                case "1":
                    return Country.RUSSIA;
                case "2":
                    return Country.INDIA;
                case "3":
                    return Country.FRANCE;
                default:
                    System.out.println("попробуйте снова");
            }
        }
    }
}
