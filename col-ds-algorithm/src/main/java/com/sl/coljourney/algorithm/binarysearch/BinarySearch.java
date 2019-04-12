package com.sl.coljourney.algorithm.binarysearch;

/**
 * 704. 二分查找 难度：简单
 *
 * @author L
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        int max = nums.length - 1, min = 0;
        while (min <= max) {
            int mid = (max + min) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 5, 6, 7, 8, 10};
        BinarySearch search = new BinarySearch();
        System.out.println(search.search(nums, 10));
    }

}
