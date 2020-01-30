package pers.xiaoan.suanfa.rabbit;

import java.math.BigDecimal;

/**
 *
 * 生兔子问题
 * @description: 有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第3个月后每个月又生一对兔子。假如兔子都不死，计算第50个月兔子的总数？
 * @author: xiaoan
 * @createDate: 2019/10/24 22:28
 */
public class Rabbit {

    public static void main(String[] args) throws Exception{
        Rabbit rabbit = new Rabbit();
        int month = 45;
        long t1 = System.currentTimeMillis();
        BigDecimal count = rabbit.getRabbitCount(month);
        long t2 = System.currentTimeMillis();
        int count2 = rabbit.f(month);
        long t3 = System.currentTimeMillis();
        System.out.println((t2 - t1) + " " + (t3 - t2));
        System.out.println(count + "," + count2);
    }

    /**
     * 获取兔子总数量
     * @param month 月份
     * @Date: 2019/10/24
     * @return: int
     */
    private BigDecimal getRabbitCount(int month) {
        int oneMonth = 1;
        int twoMonth = 0;
        int oldRabbit = 0;
        BigDecimal totalCount;

        for(int i = 0; i < month - 1; i++) {
            oldRabbit += twoMonth;
            twoMonth = oneMonth;
            oneMonth = oldRabbit;
        }
        totalCount = BigDecimal.valueOf(oneMonth + twoMonth + oldRabbit);
        return totalCount;
    }

    private int f(int x) {
        if(x == 1 || x == 2 ) {
            return 1;
        }
        else
            return f(x-1) + f(x-2);
    }
}
