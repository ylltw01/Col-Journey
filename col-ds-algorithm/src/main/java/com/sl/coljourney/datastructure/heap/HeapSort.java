package com.sl.coljourney.datastructure.heap;

/**
 * 堆排序
 * 构建小顶堆，即最后排序结果为从大到小的逆序
 */
public class HeapSort {

    public void heapSort(int[] arr) {
        // 构建小顶堆
        buildHeap(arr);

        // 1. 将小顶堆，堆顶元素即最小元素与最末尾元素交换
        // 2. 交换完成之后，最末尾元素为最小
        // 3. 然后在进行堆化，堆顶元素即为最小元素
        for (int capacity = arr.length; capacity > 1; capacity--) {
            swap(arr, 1, capacity - 1);
            heapity(arr, 1, capacity - 1);
        }
    }

    /**
     * 从 n/2 开始堆化，原地构建小顶堆
     * 堆作为完全二叉树，其 n/2 + 1 到 n 的节点为叶子节点（即使从更节点开始构建，结果也是一样）
     */
    private void buildHeap(int[] arr) {
        for (int i = arr.length / 2; i > 0; i--) {
            heapity(arr, i, arr.length);
        }
    }

    /**
     * 最小堆，堆化，比较父节点以及其两个子节点，最小的作为父节点
     * .    7
     * .   /\
     * .  /  \
     * . 6    5
     */
    private void heapity(int[] arr, int i, int capacity) {
        while (i < capacity) {
            int minPos = i;
            if (i * 2 < capacity && arr[i] > arr[i * 2]) {
                minPos = i * 2;
            }
            if (i * 2 + 1 < capacity && arr[minPos] > arr[i * 2 + 1]) {
                minPos = i * 2 + 1;
            }
            if (minPos == i) {
                break;
            }
            swap(arr, i, minPos);
            i = minPos;
        }

    }

    private void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {0, 8, 2, 3, 6, 4, 1, 9};

        System.out.println("length: " + arr.length);

        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(arr);

        for (int i : arr) {
            System.out.print(i + ", ");
        }
    }

}
