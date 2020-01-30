package pers.xiaoan.interview.javabasic.thread;

/***
 * @author: xiaoan
 * @createDate: 2020/1/6 20:28
 */
public class MyThread extends Thread{
    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }
}


