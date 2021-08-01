package com.sl.coljourney.algorithm.backtracking;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/subsets-ii/
 * 90. 子集 II
 * 难度：中等
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class Subsets2 {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        List<Integer> path = new ArrayList<>();
        // 剪枝
        Set<Integer> existSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!existSet.contains(nums[i])) {
                sub(nums, i, path, ans);
                existSet.add(nums[i]);
            }
        }
        return ans;
    }

    private void sub(int[] nums, int start, List<Integer> path, List<List<Integer>> ans) {
        if (start == nums.length) {
            return;
        }
        path.add(nums[start]);
        ans.add(new ArrayList<>(path));
        // 剪枝
        Set<Integer> existSet = new HashSet<>();
        for (int i = start + 1; i < nums.length; i++) {
            if (!existSet.contains(nums[i])) {
                sub(nums, i, path, ans);
                existSet.add(nums[i]);
            }
        }
        path.remove(path.size() - 1);
    }

}
/*
         122
     1     2    2
   [2,2]  [2]   []

1,2   1,2  2,2
[2]   []   []

1,2,2
 []

 */
