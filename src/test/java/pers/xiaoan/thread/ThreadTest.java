package pers.xiaoan.thread;

public class ThreadTest {
    public static void main(String[] args) {
        test();
    }
    private static void test() {
        Person person = new Person();
        Thread thread = new Thread(person);
        thread.start();
        System.out.println("3");
    }
}

class Person implements Runnable{
    Person() {
        System.out.println("1");
    }
    Person(String name){
        System.out.println("2" + name);
    }
    @Override
    public void run() {
        System.out.println("4");
    }

}

class Child extends Person{
    private Person person;
    Child() {
        person = new Person("小明");
    }
    @Override
    public void run() {
        System.out.println(5);
    }
}
