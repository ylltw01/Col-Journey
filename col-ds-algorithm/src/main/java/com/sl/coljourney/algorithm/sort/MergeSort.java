package com.sl.coljourney.algorithm.sort;

/**
 * 归并排序
 *
 * @author L
 */
public class MergeSort {

    public void sort(int[] a) {
        mergeSort(a, 0, a.length - 1);
    }

    /**
     * 归并排序主要方法
     * 该方法为递归方法，会将数组拆分为若干个部分，因此 start 和 end 参数在递归时也代表被拆分子数组的索引
     *
     * @param a     排序数组
     * @param start 数组开始 index
     * @param end   数组结束 index
     */
    private void mergeSort(int[] a, int start, int end) {
        // 递归退出条件, 在拆分子数组时候，如果 start 大于或等于 end 则表示已经是最细粒度了
        if (start >= end) {
            return;
        }
        // 求 start 和 end 的中间索引
        int middle = (end + start) / 2;
        // 递归拆分 start 至 middle 为一个子数组
        mergeSort(a, start, middle);
        // 递归拆分 middle + 1 至 end 为一个子数组
        mergeSort(a, middle + 1, end);

        // 归并核心方法
        merge(a, start, middle, middle + 1, end);
    }

    /**
     * 归并核心方法
     * 用于排序并合并两个有序子数组至原数组
     *
     * @param a          排序数组
     * @param start      第一个子数组开始 index
     * @param middle     第一个子数组结束 index
     * @param middleplus 第二个子数组开始 index
     * @param end        第二个子数组结束 index
     */
    private void merge(int[] a, int start, int middle, int middleplus, int end) {
        // 申请一个临时数组，用于存放排序且合并的有序数组
        int[] temp = new int[end - start + 1];
        // t 临时数组 index, m , n 分别表示两个子数组开始 index
        int t = 0, m = start, n = middleplus;
        // 排序并合并两个有序子数组
        while (m <= middle && n <= end) {
            if (a[m] > a[n]) {
                temp[t++] = a[n++];
            } else {
                temp[t++] = a[m++];
            }
        }
        // 判断是否有子数组的元素未完全合并至临时数组
        int s = m, e = end;
        if (m > middle) {
            s = n;
        } else {
            e = middle;
        }
        // 将未完全合并的元素合并至临时数组
        while (t < temp.length && s <= e) {
            temp[t++] = a[s++];
        }
        // 拷贝临时数组的数据至其对应的索引位置
        for (int tep : temp) {
            a[start++] = tep;
        }
    }

    public static void main(String[] args) {
        int[] a = {9, 2, 5, 3, 1, 7, 8, 6, 4, 98, 28};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(a);
        for (int i : a) {
            System.out.println(i);
        }
    }


}
