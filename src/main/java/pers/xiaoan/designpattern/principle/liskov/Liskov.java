package pers.xiaoan.designpattern.principle.liskov;

public class Liskov {

    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3=" + a.func1(11, 3));
        System.out.println("1-8=" + a.func1(1, 8));

        B b = new B();
        System.out.println("11-3" + b.func1(11, 3));
        System.out.println("1-8" + b.func1(1, 8));
        System.out.println("11+3+9=" + b.func2(11, 3));
    }
}

class Base {
    public int func1(int a, int b) {
        return a - b;
    }
}

class A extends Base{

    @Override
    public int func1(int a, int b) {
        return a - b;
    }
}

class B extends Base {
    private A a = new A();
    @Override
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    public int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}









