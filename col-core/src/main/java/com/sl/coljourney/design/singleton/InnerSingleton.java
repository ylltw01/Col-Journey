package com.sl.coljourney.design.singleton;

/**
 * 内部类的方式实现单例模式
 *
 * @author L
 */
public class InnerSingleton {

    private static class SingletonHolder {
        private static InnerSingleton singleton = new InnerSingleton();
    }

    private InnerSingleton() {
    }

    public static InnerSingleton getInstance() {
        return SingletonHolder.singleton;
    }
}
