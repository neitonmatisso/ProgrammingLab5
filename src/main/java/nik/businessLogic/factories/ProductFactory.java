package nik.businessLogic.factories;

import com.google.gson.Gson;
import nik.businessLogic.sourceClasses.Coordinates;
import nik.businessLogic.sourceClasses.Country;
import nik.businessLogic.sourceClasses.Person;
import nik.businessLogic.sourceClasses.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class ProductFactory {
    private final PersonFactory personFactory = new PersonFactory();
    private static int curId = 0;

    public String getNewProduct(){
        Integer id = curId++;
        Coordinates coordinates = createCoordinates();
        String name = createName();
        String partNumber = createPartNumber();
        float price = createPrice();
        Person person = personFactory.createPerson();

        return new Gson().toJson(new Product(id,name,coordinates,price,partNumber,person));

    }

    public Coordinates createCoordinates(){
        Scanner scanner = new Scanner(System.in);
            while (true) {
                 try {
                     System.out.println("Введите x координату");
                     int x = scanner.nextInt();
                     System.out.println("Введите y координату");
                     int y = scanner.nextInt();
                     return new Coordinates(x, y);
                 } catch (InputMismatchException ignored) {
                     System.out.println("Ошибка ввода координат. Попробуйте снова");
                 }
            }
    }

    public String createProductWithParams(List<String> params){
        Integer id = curId++;
        Coordinates coordinates = new Coordinates(Integer.parseInt(params.get(0)), Integer.parseInt(params.get(1)));
        String name = params.get(2);
        String partNumber = params.get(3);
        float price = Float.parseFloat(params.get(4));
        Person person = new Person(params.get(5), params.get(6), Country.valueOf(params.get(7)));
        return new Gson().toJson(new Product(id,name,coordinates,price,partNumber,person));
    }

    public String createName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите имя продукта");
        return scanner.nextLine();
    }

    public String createPartNumber(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите номер партии");
        return scanner.nextLine();
    }

    public float createPrice(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите цену координату");
                int price = scanner.nextInt();
                return price;
            } catch (InputMismatchException ignored) {
                System.out.println("Ошибка ввода цены. Попробуйте снова");
            }
        }
    }

}
