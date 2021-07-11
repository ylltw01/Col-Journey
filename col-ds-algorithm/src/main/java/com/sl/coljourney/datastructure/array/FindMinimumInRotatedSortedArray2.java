package com.sl.coljourney.datastructure.array;

/**
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/submissions/
 * 154. 寻找旋转排序数组中的最小值 II
 * 难度：困难
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/submissions/
 * 153. 寻找旋转排序数组中的最小值
 * 难度：中等
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/submissions/
 * 剑指 Offer 11. 旋转数组的最小数字
 * 难度：简单
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * <p>
 * 示例 2：
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 */
public class FindMinimumInRotatedSortedArray2 {

    public int minArray(int[] numbers) {
        int ans = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < ans) {
                return numbers[i];
            }
        }
        return ans;
    }

    public int findMin(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < ans) {
                return nums[i];
            }
        }
        return ans;
    }

}
