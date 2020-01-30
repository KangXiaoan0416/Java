package pers.xiaoan.interview.javabasic.thread;

/***
 * @author: xiaoan
 * @createDate: 2020/1/6 20:53
 */
public class WaitSleepDemo {

    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread A get lock");
                        Thread.sleep(20);
                        System.out.println("Thread A do wait method");
                        Thread.sleep(1000);
                        System.out.println("thread A is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("thread B is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("Thread B get lock");
                        System.out.println("Thread B is sleeping 10 ms");
                        Thread.sleep(10);
                        System.out.println("thread B is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
