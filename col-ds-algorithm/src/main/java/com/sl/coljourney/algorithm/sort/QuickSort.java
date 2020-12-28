package com.sl.coljourney.algorithm.sort;

/**
 * 快速排序
 * <p>
 * 快速排序，递归公式：
 * .    quickSort(p,r) = quickSort(p, q-1) + quickSort(q+1, r)
 * 终止条件：
 * .    p >= r 不用再继续分解
 * <p>
 * 伪代码如下：
 * <p>
 * // 快速排序，A是数组，n表示数组的大小
 * quick_sort(A, n) {
 * .    quick_sort_c(A, 0, n-1)
 * }
 * // 快速排序递归函数，p,r为下标
 * quick_sort_c(A, p, r) {
 * .    if p >= r then return
 * <p>
 * .    // 获取分区点
 * .    q = partition(A, p, r)
 * .    quick_sort_c(A, p, q-1)
 * .    quick_sort_c(A, q+1, r)
 * }
 * <p>
 * partition 中对数组的处理，通过游标i将 A[p,r-1]分为了两部分 A[p, i], A[i -1, r-1]
 * 其中A[p, i]：表示处理过的区间，A[i -1, r-1]：表示未处理过的区间
 * 每次处理的时候，都取出A[j] 与选择的A[pivot]进行比较，如果小于A[pivot]，则将其放在 A[i] 的位置，并且 i+1
 * 最后，交换 A[pivot] 与 A[i] 的位置
 * <p>
 * partition 函数伪代码如下：
 * <p>
 * partition(A, p, r) {
 * .    pivot := A[r]
 * .    i := p
 * .    for j := p to r-1 do {
 * .        if A[j] < pivot {
 * .        swap A[i] with A[j]
 * .        i := i+1
 * .        }
 * .    }
 * .    swap A[i] with A[r]
 * .    return i
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