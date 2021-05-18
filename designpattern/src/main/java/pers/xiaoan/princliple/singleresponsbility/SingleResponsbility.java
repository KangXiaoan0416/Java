package main.java.pers.xiaoan.princliple.singleresponsbility;

/**
 * 单一职责
 * @description:
 * @author: xiaoan
 * @createDate: 2019/11/11 1:55
 */
public class SingleResponsbility {

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}

/**
 * 交通工具类
 * @description:
 * @author: xiaoan
 * @createDate: 2019/11/11 1:56
 */
class Vehicle {
    public void  run(String vehicle) {
        System.out.println(vehicle + "在公路上运行....");
    }
}