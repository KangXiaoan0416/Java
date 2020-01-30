package pers.xiaoan.interview.javabasic.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: xiaoan
 * @createDate: 2019/11/13 0:20
 */
public class ReflectSample {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class rc = Class.forName("pers.xiaoan.interview.javabasic.reflect.Robot");
        Robot r = (Robot) rc.newInstance();
        System.out.println("Class name is " + rc.getName());

        Method getHello = rc.getDeclaredMethod("throwHello", String.class);
        getHello.setAccessible(true);
        Object str = getHello.invoke(r, "Bob");
        System.out.println(str);
    }
}
