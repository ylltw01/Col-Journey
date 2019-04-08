package com.sl.coljourney.algorithm.sort;

/**
 * 冒泡排序
 *
 * @author L
 */
public class BubbleSort {

    public void bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            // 标志位，标志是否在一次判断中是否交换了数据
            boolean change = false;
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    // 存在数据交换
                    change = true;
                }
            }
            // 如果在一次冒泡中，已经没有数据交换了，表示数据已经是有序了，提前退出
            // 这是因为冒泡是两两比较，如果对整个数组两两比较不存在数据交换，则数组肯定是有序的了
            if (!change) {
                break;
            }
        }
    }

    public void bubbleSort2(int[] a) {
        for (int i = 0; i < a.length; i++) {
            boolean change = false;
            for (int j = i; j < a.length - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    change = true;
                }
            }
            if (!change) {
                break;
            }
        }
    }

    public static void main(String[] args) {
//        int[] a = {2, 1, 6, 4, 2, 9, 5};
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort2(a);
        for (int i : a) {
            System.out.println(i);
        }
    }

}
