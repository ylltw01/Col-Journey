package com.sl.coljourney.datastructure.stack;

/**
 * 基于数组实现顺序栈
 *
 * @author L
 */
public class ArrayStack<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    private Object[] data;
    private int size;
    private int count;

    public ArrayStack() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayStack(int initialCapacity) {
        this.data = new Object[initialCapacity];
        this.size = initialCapacity;
    }

    public boolean push(E element) {
        if (count == size) {
            return false;
        }
        data[count] = element;
        ++count;
        return true;
    }

    public E pop() {
        if (count == 0) {
            return null;
        }
        E e = (E) data[count - 1];
        count--;
        return e;
    }


}
