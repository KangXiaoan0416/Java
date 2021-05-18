package main.java.part_one;

/**
 * 1.5.1 原子性
 * long 类型在32位系统中并发不安全,因为long类型是64位
 * @description:
 * @author: xiaoan
 * @createDate: 2019/12/1 3:12
 */
public class MultiThreadLong {
    public static long t = 0;
    public static class ChangeT implements Runnable {
        private long to;
        public ChangeT(long to) {
            this.to = to;
        }
        @Override
        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
        public static class ReadT implements Runnable {
            @Override
            public void run() {
                while (true) {
                    long tmp = MultiThreadLong.t;
                    if(tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L) {
                        System.out.println(tmp);
                        Thread.yield();
                    }
                }
            }
        }

        public static void main(String[] args) {
            new Thread(new ChangeT(111L)).start();
            new Thread(new ChangeT(-999L)).start();
            new Thread(new ChangeT(333L)).start();
            new Thread(new ChangeT(444L)).start();
            new Thread(new ReadT()).start();
        }
    }
}
