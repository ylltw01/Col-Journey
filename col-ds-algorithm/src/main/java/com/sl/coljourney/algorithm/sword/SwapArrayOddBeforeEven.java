package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 难度：简单
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 */
public class SwapArrayOddBeforeEven {

    public int[] exchange(int[] nums) {
        int oddIdx = 0;
        int evenIdx = nums.length - 1;

        while (oddIdx < evenIdx) {
            boolean canSwap = true;
            if (nums[oddIdx] % 2 == 1) {
                oddIdx++;
                canSwap = false;
            }

            if (nums[evenIdx] % 2 == 0) {
                evenIdx--;
                canSwap = false;
            }

            if (canSwap) {
                int temp = nums[oddIdx];
                nums[oddIdx] = nums[evenIdx];
                nums[evenIdx] = temp;
                oddIdx++;
                evenIdx--;
            }
        }
        return nums;
    }

}

