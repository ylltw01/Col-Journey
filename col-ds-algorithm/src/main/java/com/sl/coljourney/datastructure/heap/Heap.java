package com.sl.coljourney.datastructure.heap;

/**
 * 大顶堆
 */
public class Heap {

    private int[] arr;

    private int capacity;

    public Heap(int size) {
        arr = new int[++size];
        capacity = 0;
    }

    /**
     * 堆化
     * 将新增的节点，放在数组最后的位置，然后从下至上开始堆化
     */
    public void insertFromDown(int value) {
        if (capacity >= arr.length) {
            return;
        }
        int i = ++capacity;
        arr[i] = value;
        while (i / 2 > 0 && arr[i] > arr[i / 2]) {
            swap(arr, i, i / 2);
            i = i / 2;
        }
    }


    /**
     * 删除堆顶元素，即大顶堆删除最大元素
     * 讲最后一个节点放在最开始的节点，然后从上至下开始堆化
     */
    public void removeMax() {
        if (capacity == 0) {
            return;
        }
        // 将最后节点放到顶点，然后堆化
        arr[1] = arr[capacity];
        arr[capacity] = 0;
        --capacity;

        int maxPos = 1;
        while (maxPos < capacity) {
            int i = maxPos;
            /*
                5
               /\
              /  \
             6    7
             这里的判断就是要求出，这3个节点中的最大的那个，然后作为顶点
             */
            if (maxPos * 2 <= capacity && arr[maxPos] < arr[maxPos * 2]) {
                i = maxPos * 2;
            }
            if (maxPos * 2 + 1 <= capacity && arr[i] < arr[maxPos * 2 + 1]) {
                i = maxPos * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(arr, maxPos, i);
            maxPos = i;
        }
    }

    private void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int v : arr) {
            sb.append(v).append(", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        Heap heap = new Heap(9);
        heap.insertFromDown(1);
        heap.insertFromDown(2);
        heap.insertFromDown(3);
        heap.insertFromDown(4);
        heap.insertFromDown(5);
        heap.insertFromDown(6);
        heap.insertFromDown(7);
        heap.insertFromDown(8);
        heap.insertFromDown(9);

        System.out.println(heap.toString());

        heap.removeMax();

        System.out.println(heap.toString());

    }


}
