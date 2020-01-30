package pers.xiaoan.interview.javabasic.jvm.gc;

/**
 * 循环引用 引用计数算法导致内存溢出
 * @description:
 * @author: xiaoan
 * @createDate: 2019/12/2 0:53
 */
public class ReferenceCounterProblem {
    public static void main(String[] args) {
        MyObject object1 = new MyObject();
        MyObject object2 = new MyObject();

        object1.setChildNode(object1);
        object2.setChildNode(object1);
    }
}
