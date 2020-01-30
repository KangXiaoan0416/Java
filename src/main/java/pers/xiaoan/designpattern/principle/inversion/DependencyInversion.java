package pers.xiaoan.designpattern.principle.inversion;

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

class Email {
    public String getInfo() {
        return "电子邮件信息:Hello World";
    }
}

/**
 * 完成Person接收消息的功能
 * 方案1分析
 * 1. 简单，比较容易想到
 * 2. 如果获取的对象是微信，短信等等，则新增类，同时Person也要增加相应的方法
 * 3. 解决思路：引入一个抽象接口IReceiver,表示接收者，这样Person类与IReceiver发生依赖
 *
 * @description:
 * @author: xiaoan
 * @createDate: 2019/12/2 18:37
 */
class Person {
    public void receive(Email email) {
        System.out.println(email.getInfo());
    }
}
