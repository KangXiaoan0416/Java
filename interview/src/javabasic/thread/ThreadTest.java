package javabasic.thread;

/*** 
 * @author: xiaoan
 * @createDate: 2020/1/6 20:28
 */
public class ThreadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                attack();
            }
        };
    }

    private static void attack() {
        System.out.println("fight");
        System.out.println("Current Thread is:" + Thread.currentThread().getName());
    }
}
