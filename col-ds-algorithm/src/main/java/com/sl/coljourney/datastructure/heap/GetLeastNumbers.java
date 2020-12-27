package com.sl.coljourney.datastructure.heap;

import java.util.ArrayList;

/**
 * 最小k个数
 * https://www.nowcoder.com/practice/6a296eb82cf844ca8539b57c23e6e9bf?tpId=188&&tqId=36688&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
 * <p>
 * 题目描述
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
public class GetLeastNumbers {

    /**
     * 解决方案1：基于堆排序的方式实现
     * 构建小顶堆，数组从0开始构建情况下：对于节点 n，其父节点为 （n - 1）/ 2。左子节点为 2 * n + 1，右子节点为 2 * n + 2
     */
    public ArrayList<Integer> heapSortSolution(int[] input, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        if (k > input.length) {
            return nums;
        }

        // 堆化，小顶堆
        buildHeap(input);

        int capacity = input.length;
        for (int i = 0; i < k; i++) {
            nums.add(input[0]);
            swap(input, 0, capacity - 1);
            capacity--;
            heapify(input, 0, capacity);
        }
        return nums;
    }

    private void buildHeap(int[] input) {
        // 小顶堆
        for (int i = input.length / 2; i >= 0; i--) {
            heapify(input, i, input.length);
        }
    }

    private void heapify(int[] input, int i, int capacity) {
        while (true) {
            int minPos = i;
            if (i * 2 + 1 < capacity && input[i] > input[i * 2 + 1]) {
                minPos = i * 2 + 1;
            }
            if (i * 2 + 2 < capacity && input[minPos] > input[i * 2 + 2]) {
                minPos = i * 2 + 2;
            }
            if (minPos == i) {
                break;
            }
            swap(input, i, minPos);
            i = minPos;
        }

    }

    private void swap(int[] arr, int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    /**
     * 解决方案2：基于归并排序实现
     */
    public ArrayList<Integer> mergeSortSolution(int[] input, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        if (k > input.length) {
            return nums;
        }

        // 归并排序
        mergeSort(input, 0, input.length - 1);

        // 取前 k 个
        for (int i = 0; i < k; i++) {
            nums.add(input[i]);
        }
        return nums;
    }

    private void mergeSort(int[] input, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = (end + start) / 2;

        mergeSort(input, start, middle);
        mergeSort(input, middle + 1, end);

        merge(input, start, middle, middle + 1, end);
    }

    private void merge(int[] input, int start, int middle, int middlePlus, int end) {
        // 申请临时数组用于存储合并的有序数组
        int[] temp = new int[end - start + 1];
        //  es：未合并完的开始节点。ee：未合并完的结束节点
        int p = start, q = middlePlus, i = 0, es = 0, ee = end;

        // 合并两个有序数组
        while (p <= middle && q <= end) {
            if (input[p] > input[q]) {
                temp[i] = input[q];
                q++;

                // 如果最终一次是 q - end 范围合并到临时数组，那么 p - middle 则未合并完
                es = p;
                ee = middle;
            } else {
                temp[i] = input[p];
                p++;

                // 如果最终一次是 p - middle 范围合并到临时数组，那么 q - end 则未合并完
                es = q;
                ee = end;
            }
            i++;
        }

        // 将未合并完的写入到临时数组
        for (int j = es; j <= ee && i < temp.length; j++, i++) {
            temp[i] = input[j];
        }

        // 写回去
        for (int m = 0, n = start; m < temp.length; m++, n++) {
            input[n] = temp[m];
        }
    }


    /**
     * 解决方案3：基于快速排序实现
     */
    public ArrayList<Integer> quickSortSolution(int[] input, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        if (k > input.length) {
            return nums;
        }


        return nums;
    }


    public static void main(String[] args) {
        int[] arr = {0, 8, 2, 3, 6, 4, 1, 9};

        GetLeastNumbers leastNumbers = new GetLeastNumbers();
        ArrayList<Integer> integers = leastNumbers.mergeSortSolution(arr, 4);
        System.out.println("length: " + arr.length);

        for (int i : integers) {
            System.out.print(i + ", ");
        }


    }

}
