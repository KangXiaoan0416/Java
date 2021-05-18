package javabasic.reflect;

/**
 * @description:
 * @author: xiaoan
 * @createDate: 2019/12/2 0:51
 */
public class ClassLoaderChecker {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader("C:\\Users\\pc\\Desktop", "MyClassLoader");
        Class c = myClassLoader.loadClass("Wali");
        System.out.println(c.getClassLoader());
        c.newInstance();
    }
}
