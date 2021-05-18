package javabasic.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/***
 * @author: xiaoan
 * @createDate: 2020/1/6 20:40
 */
public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        new Thread(task).start();
        if(!task.isDone()) {
            System.out.println("task has not finished, please wait");
        }
        System.out.println("task return:" + task.get());
    }
}
