package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/increasing-subsequences/
 * 491. 递增子序列
 * 难度：中等
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是 2 。
 * <p>
 * 示例：
 * <p>
 * 输入：[4, 6, 7, 7]
 * 输出：[[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 */
public class IncreasingSubsequences {

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        // 针对  1,2,3,1,1 这种情况，对后面的 1, 1 进行剪枝
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 剪枝
            if (!set.contains(nums[i])) {
                bt(nums, i, ans, path);
                set.add(nums[i]);
            }
        }
        return ans;
    }

    private void bt(int[] nums, int start, List<List<Integer>> ans, List<Integer> path) {
        if (start >= nums.length) {
            return;
        }
        // 针对 4,7,6,7 ，1,2,3,1,1  这种存在比前面数小的进行剪枝
        // 只要是要添加的元素比已经添加的元素小，就剪枝，比如：6 比 7 小，1 比 3 小
        if (!path.isEmpty() && nums[start] < path.get(path.size() - 1)) {
            return;
        }
        // 添加当前层的开始元素
        path.add(nums[start]);

        if (path.size() >= 2) {
            ans.add(new ArrayList<>(path));
        }
        // 对当前这一层级，相同的元素进行剪枝，见下面的图
        Set<Integer> set = new HashSet<>();
        for (int i = start + 1; i < nums.length; i++) {
            // 剪枝
            if (!set.contains(nums[i])) {
                bt(nums, i, ans, path);
                set.add(nums[i]);
            }
        }

        // 移除当前层的开始元素
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{4, 6, 7, 7};
//        int[] nums = new int[]{1, 2, 3};
//        int[] nums = new int[]{4, 4, 3, 2, 1};
//        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 1, 1, 1, 1};
//        int[] nums = new int[]{4, 7, 6, 7};
        int[] nums = new int[]{1, 2, 3, 1, 1};
        IncreasingSubsequences increasingSubsequences = new IncreasingSubsequences();
        List<List<Integer>> subsequences = increasingSubsequences.findSubsequences(nums);

        for (List<Integer> subsequence : subsequences) {
            System.out.println(StringUtils.join(subsequence, ", "));
        }
    }

}
/*
================================================================================
                             1,2,3,1,1
                           1                                         1
                        [2,3,1,1]                                   [1]

            1,2   1,3  1,1                                          1,1


=================================================================================

                    4,7,6,7
               4                    7                    6               7
            [7,6,7]                                     [7]

        4,7         4,6   4,7
       [6,7]        [7]   [!]

  4,7,6   4,7,7
    [7!]    []

=================================================================================
                1,2,3
     1               2                3
   [2,3]            [3]

1,2  1,3            2,3
[3]  []             []

1,2,3
  []

=================================================================================
                        4,6,7,7
      4                   6               7       7
   [6,7,7]

  4,6   4,7  4,7
 [7,7]  [7]
=================================================================================
 */
