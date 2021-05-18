package main.java.pers.xiaoan;

/**
 * @description: 计数器
 * @author: xiaoan
 * @createDate: 2019/5/17 8:56
 */
public class Counter {

    /**
     * 3 * logT
     * @param args
     * @Author: xiaoan
     * @Date: 2019/5/17
     * @return: void
     */
    public static void main(String[] args) {
        System.err.println(strangeCounter(11));
    }

    /**
     * 计算第t秒的返回值
     * @param t
     * @Author: xiaoan
     * @Date: 2019/5/17
     * @return: int
     */
    public static int strangeCounter(long t) {
        //循环最小值
        int minNum = 1;
        //当前循环初始值
        int thisNum = 3;
        int result = 0;
        //结束循环的总数
        int overNum = 0;
        for(int i = 0; i < t; i++) {
            //当前值
            result = thisNum - (i - overNum) * 1;
            if(result == minNum) {
                overNum += thisNum;
                thisNum *= 2;
            }
        }
        return result;
    }

    /**
     * 递归数字和
     * @param n
     * @param k
     * @Author: xiaoan
     * @Date: 2019/5/17
     * @return: int
     */
    public static int superDigit(String n, int k) {
        int result = 0;

        return result;
    }
}



