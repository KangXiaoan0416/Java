package main.java.pers.xiaoan.princliple.inversion.improve;

/**
 * 正常实现
 * @description:
 * @author: xiaoan
 * @createDate: 2019/12/2 18:36
 */
public class DependencyInversion {
    public static void main(String[] args) {

    }

}

interface IReceiver {
    String getInfo();
}

class Email implements IReceiver {
    @Override
    public String getInfo() {
        return "电子邮件信息:Hello World";
    }
}


class Weixin implements IReceiver {
    @Override
    public String getInfo() {
        return "微信信息:Hello World";
    }
}

/**
 * // 方式2
 * @description:
 * @author: xiaoan
 * @createDate: 2019/12/2 18:37
 */
class Person {
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}













