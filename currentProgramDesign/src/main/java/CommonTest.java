package main.java;

/**
 * @description: 
 * @author: xiaoan
 * @createDate: 2019/12/1 3:32
 */
public class CommonTest {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("test");
            }
        });
        t.start();

    }
}
