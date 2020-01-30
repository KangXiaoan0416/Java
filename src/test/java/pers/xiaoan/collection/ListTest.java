package pers.xiaoan.collection;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * a test for comparing array and linklist iter
 * @description:
 * @author: xiaoan
 * @createDate: 2019/11/3 21:46
 */
public class ListTest {
    private static int[] array;
    private static LinkedList linkedList = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        String path = "E:\\文档\\filetest\\array.txt";
        int size = 100000;
        initArray(size, 100, path);
        long t1 = System.currentTimeMillis();
        iterArray();
        long t2 = System.currentTimeMillis();
        iterLinkList();
        long t3 = System.currentTimeMillis();
        System.out.println((t2 - t1) + "ms  ;" + (t3 - t2) + "ms");
    }

    private static void iterArray() {
        for (int i : array) {
            //System.out.println(i);
        }
    }

    private static void iterLinkList(){
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            //System.out.println("list:" + iterator.next());
        }
    }
    
    private static void iterLinkList2() {
        for (Object o : linkedList) {
            //System.out.println("list:" + o.toString());
        }
    }

    /**
     * 初始化数组数据
     * @param size 数据大小
     * @param maxNum
     * @param filePath
     * @Author: xiaoan
     * @Date: 2019/11/4
     * @return: void
     */
    private static void initArray(int size, int maxNum, String filePath) throws IOException {
        File file = new File(filePath);
        String encode = "UTF-8";
        StringBuilder sb = new StringBuilder();
        array = new int[size];
        for(int i = 0; i < size; i++) {
            int number = getRandomNumber(maxNum);
            array[i] = number;
            linkedList.add(number);
            sb.append(number).append(" ");
            if(i % 1000 == 0 || i == size - 1) {
                /*FileUtils.writeStringToFile(file, sb.toString() + "", encode, true);
                sb.delete(0, sb.length()-1);*/
                System.out.println("当前进度：" + i + "/" + size);
            }
        }

    }

    private static int getRandomNumber(int maxNum) {
        Random random = new Random();
        return random.nextInt(maxNum);
    }
}
