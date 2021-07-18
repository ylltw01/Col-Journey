package com.sl.coljourney.algorithm;

/**
 * https://leetcode-cn.com/problems/next-permutation/
 * 31. 下一个排列
 * 难度；中等
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 * <p>
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * [4,5,2,6,3,1]
 */
public class NextPermutation {

    /**
     * 注意到下一个排列总是比当前排列要大，除非该排列已经是最大的排列。我们希望找到一种方法，能够找到一个大于当前序列的新序列，且变大的幅度尽可能小。
     * 具体地：
     * 1.我们需要将一个左边的「较小数」与一个右边的「较大数」交换，以能够让当前排列变大，从而得到下一个排列。
     * 2.同时我们要让这个「较小数」尽量靠右，而「较大数」尽可能小。当交换完成后，「较大数」右边的数需要按照升序重新排列。这样可以在保证新排列大于原来排列的情况下，使变大的幅度尽可能小。
     * <p>
     * 以排列 [4,5,2,6,3,1] 为例：
     * 1.我们能找到的符合条件的一对「较小数」与「较大数」的组合为 2 与 3，满足「较小数」尽量靠右，而「较大数」尽可能小。
     * 2.当我们完成交换后排列变为 [4,5,3,6,2,1]，此时我们可以重排「较小数」右边的序列，序列变为 [4,5,3,1,2,6]。
     * <p>
     * 具体地，我们这样描述该算法，对于长度为 n 的排列 a：
     * 1.首先从后向前查找第一个顺序对 (i,i+1)，满足 a[i] < a[i+1]。这样「较小数」即为 a[i]。此时 [i+1,n) 必然是下降序列。
     * 2.如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 j 满足 a[i] < a[j]。这样「较大数」即为 a[j]。
     * 3.交换 a[i] 与 a[j]，此时可以证明区间 [i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n) 使其变为升序，而无需对该区间进行排序。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void nextPermutation(int[] nums) {
        // 从后往前找，直到找到 a[i] < a[i+1]
        int start = nums.length - 2;
        while (start >= 0 && nums[start] >= nums[start + 1]) {
            start--;
        }

        if (start >= 0) {
            // 如果找到，则在区间从后往前找到第一个 a[j] > a[i]
            for (int j = nums.length - 1; j > start; j--) {
                if (nums[j] > nums[start]) {
                    // 找到了，就交换 a[j] 和 a[i] 的位置
                    int temp = nums[j];
                    nums[j] = nums[start];
                    nums[start] = temp;
                    break;
                }
            }
        }

        int left = start + 1;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        NextPermutation permutation = new NextPermutation();

//        int[] arr = {1, 3, 2};
//        int[] arr = {1, 2, 3};
//        int[] arr = {3, 2, 1};
        int[] arr = {2, 3, 1};
        permutation.nextPermutation(arr);

        System.out.println(arr);
    }

}
