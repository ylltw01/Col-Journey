package com.sl.coljourney.algorithm.sort;

/**
 * 快速排序
 *
 * @author L
 */
public class QuickSort {

    public void sort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 快排入口方法
     *
     * @param a     待排序数组
     * @param start 快排拆分的子数组的开始索引
     * @param end   快排拆分的子数组的结束索引
     */
    private void quickSort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        // 核心方法，将数组分为两部分，取一个中间值，前部分是小于该值，后部分是大于该值
        int par = partition(a, start, end);
        quickSort(a, start, par - 1);
        quickSort(a, par + 1, end);
    }

    /**
     * 快速排序核心方法
     * 1. 取出一个中间值
     * 2. 使用原地分区，将数组分为两部分左边全部小于该值，右边全部大于该值
     * <p>
     * 示例如下：
     * {11, 2, 3, 4, 5}
     * msi = 0| a[0] < 5 => false| no swap | 11, 2, 3, 4, 5  | msi = 0;
     * msi = 0| a[1] < 5 => true |  swap   | 2 ,11, 3, 4, 5  | msi = 1;
     * msi = 1| a[2] < 5 => true |  swap   | 2 ,3 ,11, 4, 5  | msi = 2;
     * msi = 2| a[3] < 5 => true |  swap   | 2 ,3 ,4 ,11, 5  | msi = 3;
     * swap msi 和 5 => 结果为 2 ,3 ,4 ,5 ,11
     * <p>
     * {11, 12, 13, 14, 5}
     * msi = 0| a[0] < 5 => false| no swap | 11, 12, 13, 14, 5  | msi = 0;
     * msi = 0| a[1] < 5 => false| no swap | 11, 12, 13, 14, 5  | msi = 0;
     * msi = 0| a[2] < 5 => false| no swap | 11, 12, 13, 14, 5  | msi = 0;
     * msi = 0| a[3] < 5 => false| no swap | 11, 12, 13, 14, 5  | msi = 0;
     * swap msi 和 5 => 结果为 5, 12, 13, 14, 11
     *
     * @param a     待排序数组
     * @param start 排序数组或子数组开始索引
     * @param end   排序数组或子数组结束索引
     * @return 中间分区值索引值
     */
    private int partition(int[] a, int start, int end) {
        // 选择分区值可以有多重策略，来防止快排时间复杂度退化为O(n2)的问题，如：三位取中法
        int middle = end;
        int msi = start;
        while (start < end) {
            if (a[start] < a[middle]) {
                swap(a, start, msi);
                msi++;
            }
            start++;
        }
        swap(a, msi, middle);
        return msi;
    }

    private void swap(int[] a, int from, int to) {
        int temp = a[from];
        a[from] = a[to];
        a[to] = temp;
    }


    public static void main(String[] args) {
//        int[] a = {9, 2, 5, 3, 1, 7, 8, 6, 4, 98, 28};
//        int[] a = {11, 2, 3, 4, 5};
        int[] a = {11, 12, 13, 14, 5};
        QuickSort sort = new QuickSort();
        sort.sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

}