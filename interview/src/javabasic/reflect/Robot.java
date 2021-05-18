package javabasic.reflect;

/**
 * 反射机制例子
 * @description:
 * @author: xiaoan
 * @createDate: 2019/11/13 0:18
 */
public class Robot {


    private String name;
    public void sayHi(String helloSentence) {
        System.out.println(helloSentence + " " + name);
    }

    private String throwHello(String tag) {
        return "Hello" + tag;
    }

    static {
        System.out.println("Hello Robot");
    }
}
