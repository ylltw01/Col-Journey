package com.sl.coljourney.design.singleton;

/**
 * 基于双检锁方式实现单例模式
 *
 * @author L
 */
public class LazySingleton {

    private static volatile LazySingleton singleton;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (singleton == null) {
            synchronized (LazySingleton.class) {
                if (singleton == null) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }
}
