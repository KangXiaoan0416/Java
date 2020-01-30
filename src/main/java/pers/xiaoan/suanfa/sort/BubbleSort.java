package pers.xiaoan.suanfa.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 * @description: 冒泡排序算法
 * 算法原理
 * 冒泡排序算法的原理如下：(概念来源百度百科 https://baike.baidu.com/item/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F/4602306?fr=aladdin)
 * 1. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 2. 对每一对相邻元素做同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 * 3. 针对所有的元素重复以上的步骤，除了最后一个。
 * 4. 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
 * @author: xiaoan
 * @createDate: 2019/10/17 12:15
 */
public class BubbleSort {
    
    /**
     * 数组排序
     * @param array 排序数组
     * @Author: xiaoan
     * @Date: 2019/10/17        
     * @return: void
     */
    private void bubbleSort(int[] array) {
        int size  = array.length;
        // 定义一个变量，用于提前跳出循环
        boolean isSortOver = false;
        showArray(array);
        long start = System.currentTimeMillis();
        /** 中间变量 */
        int temp;
        // 1.第一层循环，取最差算法（需要循环size-1才能完成排序例如：{3，1，4，8, 6,7} 即第一个不是最小值）
        for(int i = 0; i < size - 1; i++) {
            if(isSortOver) {
                System.out.println("循环提前结束: " + i);
                break;
            }
            // 2.第二层循环, 这里控制用 j < size - 1 - i ,多一个-i，留着思考
            int maxNum = size - 1 - i;
            int count = 0;
            for(int j = 0; j < maxNum; j++) {
                if(array[j] > array[j+1]) {
                    temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }else {
                    count++;
                }
            }
            if(count == maxNum) {
                isSortOver = true;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("排序完成，耗时：" + (end - start) + "ms");
        showArray(array);
    }

    /**
     * 数组显示方法
     * @param array 数组
     * @Author: xiaoan
     * @Date: 2019/10/17
     * @return: void
     */
    private void showArray(int[] array) {
        System.out.println(" 数组: ( " + Arrays.toString(array) + ")");
    }

    /**
     * 获取随机数组
     * @param count 数组大小
     * @param maxNum 数值范围
     * @Author: xiaoan
     * @Date: 2019/10/17
     * @return: int[]
     */
    private int[] randomArray(int count, int maxNum) {
        int[] array = new int[count];
        for(int i = 0; i < count; i++) {
            Random random = new Random();
            int num = random.nextInt(maxNum);
            array[i] = num;
        }
        return array;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = bubbleSort.randomArray(1000, 1000);
        bubbleSort.bubbleSort(array);
    }
}























