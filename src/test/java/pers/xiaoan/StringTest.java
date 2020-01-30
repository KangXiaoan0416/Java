package pers.xiaoan;

public class StringTest {
    public static void main(String[] args) {
        String str = "0";
        showString(str);
        System.out.println(str);


        StringBuffer stringBuffer = new StringBuffer("0");
        showStringBuffer(stringBuffer);
        System.out.println(stringBuffer.toString());
    }

    public static void showString(String string) {
        string = string + "1";
        System.out.println("string:" + string);
    }

    public static void showStringBuffer(StringBuffer stringBuffer) {
        stringBuffer.append("1");
        System.out.println("StringBuffer:" + stringBuffer.toString());
    }
}
