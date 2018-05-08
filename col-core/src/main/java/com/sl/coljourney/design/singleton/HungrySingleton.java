package com.sl.coljourney.design.singleton;

/**
 * 饿汉单例模式
 *
 * @author L
 */
public class HungrySingleton {

    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return singleton;
    }

}
