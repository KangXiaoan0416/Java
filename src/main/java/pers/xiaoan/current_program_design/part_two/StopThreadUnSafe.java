package pers.xiaoan.current_program_design.part_two;

import lombok.Data;

/**
 * 2.2.2
 * stop方法弃用原因，执行一半终止，导致数据一致性被破坏
 * main方法中sleep应该是1500 原书中是150导致name一直是0
 * 原书中stopMe没有static 修饰，导致main方法中无法直接调用
 * @description:
 * @author: xiaoan
 * @createDate: 2019/12/1 3:35
 */
public class StopThreadUnSafe {

    public static User u = new User();
    @Data
    public static class User {
        private int id;
        private String name;
        public User() {
            this.id = 0;
            this.name = "0";
        }

        @Override
        public String toString() {
            return "User [id="  + id + ", name=" + name + "]";
        }
    }

    public static class ChangeObjectThread extends Thread {
        static volatile boolean stopMe = false;
        public static void stopMe() {
            stopMe = true;
        }
        @Override
        public void run() {
            while(true) {
                if(stopMe) {
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u) {
                    int v = (int) (System.currentTimeMillis() / 1000);
                    u.setId(v);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }

    public static class ReadObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if(u.getId() != Integer.parseInt(u.getName())) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(1500);
            ChangeObjectThread.stopMe();
        }
    }
}
