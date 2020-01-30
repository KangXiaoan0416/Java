package pers.xiaoan.singleton.enumsingleton;

/**
 * 枚举单例
 * @author: xiaoan
 * @createDate: 2019/12/11 12:33
 */
public class EnumSingleton {
    private EnumSingleton() {}

    private enum Singleton{
        /** 接口 */
        INSTANCE;
        private final EnumSingleton instance;
        Singleton() {
            instance = new EnumSingleton();
        }
        private EnumSingleton getInstance() {
            return instance;
        }
    }

    public static EnumSingleton getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
}

