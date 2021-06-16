package com.sl.coljourney.algorithm;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/93aacb4a887b46d897b00823f30bfea1?tpId=117&&tqId=37805&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/lfu-cache/
 * 460. LFU 缓存
 * 难度：困难
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * <p>
 * 实现 LFUCache 类：
 * <p>
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。
 * 当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除最近最久未使用的键。
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * <p>
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * <p>
 * 示例：
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * <p>
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lFUCache = new LFUCache(2);
 * lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lFUCache.get(1);      // 返回 1
 * // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 * // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lFUCache.get(2);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 * // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 * // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lFUCache.get(1);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 * // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lFUCache.get(4);      // 返回 4
 * // cache=[3,4], cnt(4)=2, cnt(3)=3
 */
public class LFUCache {

    private final int capacity;

    private int minTimes;

    private final Map<Integer, CacheData> cacheMap = new HashMap<>();

    private final Map<Integer, LinkedList<CacheData>> timesMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        // 更新 key 的访问次数
        if (!cacheMap.containsKey(key) || capacity == 0) {
            return -1;
        }
        CacheData cacheData = cacheMap.get(key);
        LinkedList<CacheData> minCacheList = timesMap.get(cacheData.times);
        minCacheList.remove(cacheData);

        // 如果最小次数等于当前节点的次数 并且 当前节点获取到的当前最小次数在删除了当前节点后缓存为空，更新最小次数
        if (minTimes == cacheData.times && minCacheList.isEmpty()) {
            minTimes = cacheData.times + 1;
        }
        cacheData.times = cacheData.times + 1;

        LinkedList<CacheData> newPosition = timesMap.getOrDefault(cacheData.times, new LinkedList<>());
        newPosition.addFirst(cacheData);
        timesMap.put(cacheData.times, newPosition);
        return cacheData.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        // 如果存在，直接更新，并更新其最小次数
        if (cacheMap.containsKey(key)) {
            CacheData cacheData = cacheMap.get(key);
            cacheData.value = value;

            LinkedList<CacheData> minCacheList = timesMap.get(cacheData.times);
            minCacheList.remove(cacheData);

            // 如果最小次数等于当前节点的次数 并且 当前节点获取到的当前最小次数在删除了当前节点后缓存为空，更新最小次数
            if (minTimes == cacheData.times && minCacheList.isEmpty()) {
                minTimes = cacheData.times + 1;
            }
            cacheData.times = cacheData.times + 1;

            LinkedList<CacheData> newPosition = timesMap.getOrDefault(cacheData.times, new LinkedList<>());
            newPosition.addFirst(cacheData);
            timesMap.put(cacheData.times, newPosition);
        } else {
            // 如果不存在，
            //     -- 如果满了，则 删除 最小的次数的 key，然后新加的 key 的次数 1 即为最小访问 次数
            //     -- 如果未满，新加的 key 访问次数为 1，增加到 key 为 1 的链表头
            CacheData newData = new CacheData(key, value, 1);
            if (cacheMap.size() >= capacity) {
                LinkedList<CacheData> minCacheList = timesMap.get(minTimes);
                if (minCacheList.size() > 0) {
                    CacheData removeData = minCacheList.removeLast();
                    cacheMap.remove(removeData.key);
                }
            }
            minTimes = newData.times;
            LinkedList<CacheData> newPosition = timesMap.getOrDefault(minTimes, new LinkedList<>());
            newPosition.addFirst(newData);
            cacheMap.put(key, newData);
            timesMap.put(minTimes, newPosition);
        }
    }

    static class CacheData {
        int key;
        int value;
        int times;

        public CacheData(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    /**
     * 牛客题目
     *
     * @param operators int整型二维数组 ops opt=1 set, opt=2 get
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LFU(int[][] operators, int k) {
        List<Integer> ans = new ArrayList<>();
        LFUCache lfu = new LFUCache(k);
        for (int[] operator : operators) {
            if (operator[0] == 1) {
                lfu.put(operator[1], operator[2]);
            } else if (operator[0] == 2){
                ans.add(lfu.get(operator[1]));
            }
        }
        int[] ansArr = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ansArr[i] = ans.get(i);
        }
        return ansArr;
    }


    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);

        lfu.put(1, 1);
        lfu.put(2, 2);
        int i = lfu.get(1);
        System.out.println(i);
        lfu.put(3, 3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4, 4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));

    }

}
