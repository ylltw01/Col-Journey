package com.sl.coljourney.algorithm;

/**
 * 88. 合并两个有序数组 【简单】
 * https://www.nowcoder.com/practice/89865d4375634fc484f3a24b7fe65665?tpId=117&tqId=37727&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/merge-sorted-array/submissions/
 * <p>
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * <p>
 * 题目描述
 * 给出两个有序的整数数组 A 和 B ，请将数组 B 合并到数组 A 中，变成一个有序的数组
 * 注意：
 * 可以假设 A 数组有足够的空间存放 B 数组的元素， A 和 B 中初始的元素数目分别为  m 和 n
 */
public class MergeSortedArray {

    /**
     * 从后往前合并的思路
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int nums1Index = m - 1;
        int nums2Index = n - 1;
        int maxIndex = nums1.length - 1;
        while (nums1Index >= 0 && nums2Index >= 0) {
            if (nums1[nums1Index] > nums2[nums2Index]) {
                nums1[maxIndex] = nums1[nums1Index];
                nums1Index--;
            } else {
                nums1[maxIndex] = nums2[nums2Index];
                nums2Index--;
            }
            maxIndex--;
        }

        if (nums2Index >= 0) {
            for (int i = nums2Index; i >= 0; i--) {
                nums1[maxIndex] = nums2[i];
                maxIndex--;
            }
        }
    }

    public void merge(int A[], int m, int B[], int n) {
        int aSize = m;
        int ai = 0;
        int bi = 0;
        while (bi < n) {
            while (ai < ai + aSize) {
                if (A[ai] > B[bi]) {
                    for (int j = ai + aSize; j > ai; j--) {
                        A[j] = A[j - 1];
                    }
                    A[ai] = B[bi];
                    ai++;
                    break;
                }
                ai++;
                aSize--;
            }
            if (aSize == 0) {
                A[ai] = B[bi];
                ai++;
            }
            bi++;
        }
    }

    public static void main(String[] args) {
        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        mergeSortedArray.merge2(nums1, 3, new int[]{1, 2, 3}, 3);
        nums1 = new int[]{4, 5, 6, 0, 0, 0};
        mergeSortedArray.merge2(nums1, 3, new int[]{1, 2, 3}, 3);

        for (int i : nums1) {
            System.out.println(i);
        }
    }
}
