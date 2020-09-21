package nik.businessLogic.sourceClasses;

import java.util.Objects;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Поле не может быть null
    private Country nationality; //Поле не может быть null

    public Person(String name, String passportID, Country nationality) {
        this.name = name;
        this.passportID = passportID;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }


    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", passportID='" + passportID + '\'' +
                ", nationality=" + nationality +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(passportID, person.passportID)&&
                nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passportID, nationality);
    }
}