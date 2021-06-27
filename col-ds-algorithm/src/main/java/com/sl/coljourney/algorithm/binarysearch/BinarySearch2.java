package com.sl.coljourney.algorithm.binarysearch;

/**
 * https://www.nowcoder.com/practice/4f470d1d3b734f8aaf2afb014185b395?tpId=117&&tqId=37829&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * 二分查找-II
 * 中等  通过率：27.82%  时间限制：1秒  空间限制：256M
 * 知识点
 * 二分
 * 描述
 * 请实现有重复数字的升序数组的二分查找
 * 给定一个 元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的第一个出现的target，如果目标值存在返回下标，否则返回 -1
 * <p>
 * 示例1
 * 输入：
 * [1,2,4,4,5],4
 * 返回值：
 * 2
 * 说明：
 * 从左到右，查找到第1个为4的，下标为2，返回2
 * <p>
 * 示例2
 * 输入：
 * [1,2,4,4,5],3
 * 返回值：
 * -1
 * 示例3
 * 输入：
 * [1,1,1,1,1],1
 * 返回值：
 * 0
 */
public class BinarySearch2 {

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = (end + start) / 2;
            if (nums[mid] == target) {
                end = mid - 1;
                ans = mid;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {2, 2, 2, 2, 2, 2, 7, 8, 10};
        int[] nums = {1, 2, 2, 4, 5, 6, 7, 8, 10};
        BinarySearch2 search = new BinarySearch2();
        System.out.println(search.search(nums, 2));
    }

}
