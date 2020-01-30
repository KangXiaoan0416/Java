package pers.xiaoan.interview.javabasic.reflect;

/**
 * @description: 
 * @author: xiaoan
 * @createDate: 2019/11/13 1:10
 */
public class LoadDifference {
    public static void main(String[] args) throws ClassNotFoundException {
        //ClassLoader cl = Robot.class.getClassLoader();
        Class r = Class.forName("pers.xiaoan.interview.javabasic.reflect.Robot");
    }
}
