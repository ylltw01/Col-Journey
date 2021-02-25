package com.sl.coljourney.algorithm.sort;

/**
 * https://www.nowcoder.com/practice/e016ad9b7f0b45048c58a9f27ba618bf?tpId=117&tqId=37791&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/submissions/
 * 数组中第K个最大元素
 * <p>
 * 题目描述
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 * <p>
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 */
public class FindKthSolution {

    public int findKth(int[] a, int n, int k) {
        return quickSort(a, 0, a.length - 1, k);
    }

    private int quickSort(int[] a, int start, int end, int k) {
        int partitionIndex = partition(a, start, end);
        if (start <= end) {
            if (a.length - k == partitionIndex) {
                return a[partitionIndex];
            } else if (a.length - k > partitionIndex) {
                return quickSort(a, partitionIndex + 1, end, k);
            } else {
                return quickSort(a, start, partitionIndex - 1, k);
            }
        }
        return -1;
    }

    private int partition(int[] a, int start, int end) {
        int middle = end;

        int msi = start;
        while (start < end) {
            if (a[start] < a[middle]) {
                swap(a, start, msi);
                msi++;
            }
            start++;
        }
        swap(a, middle, msi);
        return msi;
    }

    private void swap(int[] a, int from, int to) {
        int temp = a[from];
        a[from] = a[to];
        a[to] = temp;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1332802, 1177178, 1514891, 871248, 753214, 123866, 1615405, 328656, 1540395, 968891, 1884022,
                252932, 1034406, 1455178, 821713, 486232, 860175, 1896237, 852300, 566715, 1285209, 1845742, 883142, 259266,
                520911, 1844960, 218188, 1528217, 332380, 261485, 1111670, 16920, 1249664, 1199799, 1959818, 1546744, 1904944,
                51047, 1176397, 190970, 48715, 349690, 673887, 1648782, 1010556, 1165786, 937247, 986578, 798663};
        FindKthSolution solution = new FindKthSolution();
        System.out.println(solution.findKth(a, a.length, 24));
    }

}
