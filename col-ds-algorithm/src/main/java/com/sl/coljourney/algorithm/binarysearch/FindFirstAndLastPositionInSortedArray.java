package com.sl.coljourney.algorithm.binarysearch;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置  难度：中等
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @author L
 */
public class FindFirstAndLastPositionInSortedArray {

    /**
     * 基于二分查找的循环实现，查找到之后再向后和向前查找
     *
     * @param nums   查找的有序数组
     * @param target 查找的值
     * @return 返回第一个和最后一个位置索引数组
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        int max = nums.length - 1, min = 0;
        while (min <= max) {
            int mid = (max + min) / 2;
            if (nums[mid] == target) {
                if (mid + 1 >= nums.length || nums[mid + 1] != target) {
                    result[1] = mid;
                } else {
                    for (int i = mid + 1; i < nums.length; i++) {
                        if (nums[i] == target) {
                            result[1] = i;
                        }
                    }
                }
                if (mid - 1 < 0 || nums[mid - 1] != target) {
                    result[0] = mid;
                } else {
                    for (int i = mid - 1; i >= 0; i--) {
                        if (nums[i] == target) {
                            result[0] = i;
                        }
                    }
                }
                min = nums.length;
            } else if (nums[mid] > target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return result;
    }

    /**
     * 基于二分查找的递归实现
     *
     * @param nums   查找的有序数组
     * @param target 查找的值
     * @return 返回第一个和最后一个位置索引数组
     */
    public int[] searchRange2(int[] nums, int target) {
        int[] result = {-1, -1};
        search(result, nums, target, nums.length - 1, 0);
        return result;
    }

    private void search(int[] result, int[] nums, int target, int max, int min) {
        if (min > max) {
            return;
        }
        int mid = (max + min) / 2;
        if (nums[mid] == target) {
            if (mid + 1 >= nums.length || nums[mid + 1] != target) {
                result[1] = mid;
            } else {
                search(result, nums, target, max, mid + 1);
            }
            if (mid - 1 < 0 || nums[mid - 1] != target) {
                result[0] = mid;
            } else {
                search(result, nums, target, mid - 1, min);
            }
        } else if (nums[mid] > target) {
            search(result, nums, target, mid - 1, min);
        } else {
            search(result, nums, target, max, mid + 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
//        int[] nums = {1};
        FindFirstAndLastPositionInSortedArray position = new FindFirstAndLastPositionInSortedArray();
        int[] ints = position.searchRange2(nums, 8);
        for (int a : ints) {
            System.out.println(a);
        }
    }

}
