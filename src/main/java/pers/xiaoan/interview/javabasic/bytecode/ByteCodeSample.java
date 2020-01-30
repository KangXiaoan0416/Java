package pers.xiaoan.interview.javabasic.bytecode;

/**
 * @description:
 * @author: xiaoan
 * @createDate: 2019/11/13 0:07
 */
public class ByteCodeSample {
    public static void main(String[] args) {
        try {
            Class.forName("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
