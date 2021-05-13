package com.sl.coljourney.datastructure.array;

/**
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 * 难度：困难
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 * <p>
 * 示例 4：
 * <p>
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 * <p>
 * 示例 5：
 * <p>
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 * <p>
 * 解法：
 * 下面的解法是合并在计算时间复杂度O(m + n)，空间复杂度O(1)
 * 官方解法是二分查找可以做到时间复杂度O(log(m + n))，空间复杂度O(1)
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int sumLen = nums1.length + nums2.length;
        int startIdx;
        int endIdx;
        if (sumLen % 2 == 0) {
            endIdx = sumLen / 2;
            startIdx = endIdx - 1;
        } else {
            startIdx = endIdx = sumLen / 2;
        }

        int nums1Idx = 0;
        int nums2Idx = 0;
        int mergeIdx = 0;

        double start = 0;
        double end = 0;
        while (nums1Idx < nums1.length && nums2Idx < nums2.length) {
            int ele;
            if (nums1[nums1Idx] < nums2[nums2Idx]) {
                ele = nums1[nums1Idx];
                nums1Idx++;
            } else {
                ele = nums2[nums2Idx];
                nums2Idx++;
            }
            if (mergeIdx == startIdx) {
                start = ele;
            }
            if (mergeIdx == endIdx) {
                end = ele;
            }
            mergeIdx++;
        }
        mergeIdx--;
        if (mergeIdx < startIdx || mergeIdx < endIdx) {
            int[] arr;
            int idx;
            if (nums1Idx >= nums1.length) {
                arr = nums2;
                idx = nums2Idx;
            } else {
                arr = nums1;
                idx = nums1Idx;
            }
            if (mergeIdx < startIdx || mergeIdx == -1) {
                start = arr[startIdx - mergeIdx - 1 + idx];
            }
            end = arr[endIdx - mergeIdx - 1 + idx];
        }
        return (start + end) / 2;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays median = new MedianOfTwoSortedArrays();
        double medianSortedArrays = median.findMedianSortedArrays(new int[]{1}, new int[]{});
//        double medianSortedArrays = median.findMedianSortedArrays(new int[]{1, 2}, new int[]{2});
//        double medianSortedArrays = median.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
//        double medianSortedArrays = median.findMedianSortedArrays(new int[]{2}, new int[]{1, 3, 4});
//        double medianSortedArrays = median.findMedianSortedArrays(new int[]{2, 2, 2, 2}, new int[]{2, 2, 2});
        System.out.println(medianSortedArrays);
    }

    // 1
    //


    // 1，2，
    // 3，4，5，6，

    // startIdx = 2
    // endIdx = 3

    // nums2Idx = 0;
    // mergeIdx = 1


}
