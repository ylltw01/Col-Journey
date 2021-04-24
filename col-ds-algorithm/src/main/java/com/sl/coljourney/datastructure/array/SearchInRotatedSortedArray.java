package com.sl.coljourney.datastructure.array;

/**
 * https://www.nowcoder.com/practice/7cd13986c79d4d3a8d928d490db5d707?tpId=117&tqId=37744&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/submissions/
 * 难度：中等
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1], target = 0
 * 输出：-1
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 */
public class SearchInRotatedSortedArray {

    /**
     * 解法：二分查找
     * 在二分这个旋转了的数组之后，始终有一段会是有序的
     * 1. 当 nums[mid + 1] > nums[mid] && (nums[mid + 1] < nums[max] || max == mid + 1)
     * >  也就是，中点的下一个元素比中点大，并且最大索引元素比中点元素大或中点就是倒数第二个节点，满足时候，后半段是有序递增的
     * >  如果 target 大于中点元素并且 target 小于等于最大索引元素，那么 target 肯定存在后半段
     * >  否则，在前半段
     * 2. 否则前半段是递增的
     * >  如果 target 大于中点元素或者 target 小于最小索引元素，那么 target 肯定存在后半段
     * >  否则，在前半段
     */
    public int search(int[] nums, int target) {
        int mid = nums.length / 2;
        int min = 0;
        int max = nums.length - 1;
        int index = -1;

        while (min <= max) {
            if (nums[mid] == target) {
                return mid;
            }

            // mid 是最后一个元素
            if (mid == max) {
                max = mid - 1;
            } else if (nums[mid + 1] > nums[mid] && (nums[mid + 1] < nums[max] || max == mid + 1)) {
                // nums[mid + 1] > nums[mid] && (nums[mid + 1] < nums[max] || max == mid + 1) 后续是递增的
                if (target > nums[mid] && target <= nums[max]) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            } else {
                // 否则，min - mid 就必然是递增的
                if (target > nums[mid] || target < nums[min]) {
                    min = mid + 1;
                } else {
                    max = mid - 1;
                }
            }
            mid = (max + min) / 2;
        }
        return index;
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray search = new SearchInRotatedSortedArray();
//        int[] arr = {4, 5, 6, 7, 0, 1, 2};
//        int[] arr = {8, 9, 1, 2, 3, 4, 5, 6, 7};
//        int[] arr = {3, 4, 5, 6, 7, 8, 9, 1, 2};
        int[] arr = {1, 3, 5};

        int r = search.search(arr, 5);
        System.out.println(r);
    }

}
