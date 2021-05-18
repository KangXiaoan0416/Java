package main.java.pers.xiaoan;

import java.util.ArrayList;
import java.util.List;

/**
 * a test class for jdk8 streamApi
 * @description:
 * @author: xiaoan
 * @createDate: 2019/11/8 18:16
 */
public class StreamTest {

    public static void main(String[] args) {
        List<Person> persons = getPersons();
        System.out.println(-20 >> 2);
        persons.forEach(Person::showInfo);


    }

    private static List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Person person = new Person(i + "儿子", 20-i);
            persons.add(person);
        }
        return persons;
    }
}

class Person{
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void showInfo() {
        System.out.println(this.getName() + ":" + this.getAge());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
