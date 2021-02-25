package com.sl.coljourney.datastructure.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目描述
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能
 * set(key, value)：将记录(key, value)插入该结构
 * get(key)：返回key对应的value值
 * <p>
 * [要求]
 * set和get方法的时间复杂度为O(1)
 * 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 对于每个操作2，输出一个答案
 * <p>
 * 示例1
 * 输入
 * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
 * 返回值
 * [1,-1]
 * <p>
 * 说明
 * 第一次操作后：最常使用的记录为("1", 1)
 * 第二次操作后：最常使用的记录为("2", 2)，("1", 1)变为最不常用的
 * 第三次操作后：最常使用的记录为("3", 2)，("1", 1)还是最不常用的
 * 第四次操作后：最常用的记录为("1", 1)，("2", 2)变为最不常用的
 * 第五次操作后：大小超过了3，所以移除此时最不常使用的记录("2", 2)，加入记录("4", 4)，并且为最常使用的记录，然后("3", 2)变为最不常使用的记录
 */
public class LruSolution {

    public static void main(String[] args) {
        // [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
        int[][] arr = new int[6][3];
        arr[0][0] = 1;
        arr[0][1] = 1;
        arr[0][2] = 1;

        arr[1][0] = 1;
        arr[1][1] = 2;
        arr[1][2] = 2;

        arr[2][0] = 1;
        arr[2][1] = 3;
        arr[2][2] = 2;

        arr[3][0] = 2;
        arr[3][1] = 1;

        arr[4][0] = 1;
        arr[4][1] = 4;
        arr[4][2] = 4;

        arr[5][0] = 2;
        arr[5][1] = 2;

        int[] lru = LRU(arr, 3);
        for (int i : lru) {
            System.out.println(i);
        }

    }

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public static int[] LRU(int[][] operators, int k) {
        int size = 0;
        for (int[] operator : operators) {
            if (operator[0] == 2) {
                size++;
            }
        }
        int[] result = new int[size];
        int i = 0;
        LruCache lruCache = new LruCache(k);
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                lruCache.set(operator[1], operator[2]);
            } else if (operator[0] == 2) {
                int r = lruCache.get(operator[1]);
                result[i] = r;
                i++;
            }
        }

        return result;
    }

}

class LruCache {

    private final Map<Integer, Node> cache = new HashMap<>();

    private Node start = null;

    private Node end = null;

    private final int size;

    public LruCache(int size) {
        this.size = size;
    }

    public void set(int key, int val) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() >= size) {
                // 驱逐最后一个
                cache.remove(end.key);
                end = end.previous;
                end.next = null;
            }
            node = new Node(key, val);
            node.next = start;

            if (start != null) {
                if (end == null) {
                    end = start;
                }
                start.previous = node;
            }
            start = node;
            cache.put(key, node);
        } else {
            node.val = val;
            if (node == end) {
                end = node.previous;
                end.next = null;
            }
            if (node != start) {
                node.previous.next = node.next;
                if (node.next != null) {
                    node.next.previous = node.previous;
                }
                node.next = start;
                start.previous = node;
                start = node;
            }
        }
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        } else {
            if (node == end) {
                end = node.previous;
                end.next = null;
            }
            if (node != start) {
                node.previous.next = node.next;
                if (node.next != null) {
                    node.next.previous = node.previous;
                }
                node.next = start;
                start.previous = node;
                start = node;
            }
            return node.val;
        }
    }

}

class Node {
    int key;
    int val;
    Node previous = null;
    Node next = null;

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}


