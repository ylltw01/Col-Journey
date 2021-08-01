package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/subsets/
 * 78. 子集
 * 难度：中等
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 各种回溯算法：
 * https://leetcode-cn.com/problems/subsets/solution/dai-ma-sui-xiang-lu-78-zi-ji-hui-su-sou-6yfk6/
 */
public class Subsets {

    /**
     * 回溯解法
     * [] 表示剩下的元素
     * <p>
     * >                    123
     * >         1       2        3
     * >       [2,3]    [3]       []
     * >
     * >     12    13    23
     * >     [3]   []    []
     * >
     * >   123
     * >   []
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            sub(nums, i, path, ans);
        }
        return ans;
    }

    private void sub(int[] nums, int start, List<Integer> path, List<List<Integer>> ans) {
        if (start == nums.length) {
            return;
        }
        // path 先 add
        path.add(nums[start]);
        ans.add(new ArrayList<>(path));
        for (int i = start + 1; i < nums.length; i++) {
            sub(nums, i, path, ans);
        }
        // path 然后在 remove，这两个动作配对出现即可
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Subsets subsets = new Subsets();
        List<List<Integer>> subsets1 = subsets.subsets(nums);
        System.out.println(subsets1);
    }
}
