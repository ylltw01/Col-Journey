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

    public static void main(String[] args) {
        int[] a = new int[]{1332802, 1177178, 1514891, 871248, 753214, 123866, 1615405, 328656, 1540395, 968891, 1884022,
                252932, 1034406, 1455178, 821713, 486232, 860175, 1896237, 852300, 566715, 1285209, 1845742, 883142, 259266,
                520911, 1844960, 218188, 1528217, 332380, 261485, 1111670, 16920, 1249664, 1199799, 1959818, 1546744, 1904944,
                51047, 1176397, 190970, 48715, 349690, 673887, 1648782, 1010556, 1165786, 937247, 986578, 798663};
        System.out.println(a.length);
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.insertionSort(a);
        System.out.println(a.length);
    }

}
