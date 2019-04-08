package com.sl.coljourney.algorithm.sort;

/**
 * 插入排序
 *
 * @author L
 */
public class InsertionSort {

    public void insertionSort(int[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        for (int i = 1; i < a.length; i++) {
            // 记录当前待排序的数据
            int value = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                // 如果比当前的数据大，则移动至当前元素后边
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            // 插入当前待排序数据
            a[j + 1] = value;
        }
    }

}
