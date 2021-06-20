package com.sl.coljourney.algorithm.backtracking;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/combination-sum
 * 39. 组合总和
 * 难度：中等
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * <p>
 * 题解：
 * 深度优先遍历
 * 1. 画出递归图
 * 2. 去除重复的记录，就是不能再继续重复计算索引小于当前索引的元素
 * 3. 剪枝，排序了数组之后，提升性能
 * 4. 递归的时候，下一个 begin 就等于当前元素所在的索引值
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        Deque<Integer> path = new LinkedList<>();
        dfs(ans, path, candidates, target, 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, Deque<Integer> path, int[] candidates, int target, int begin) {
        if (target <= 0) {
            if (target == 0) {
                ans.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            // 剪枝，因为是有序的，如果 target - candidates[i] 小于 0 了，就没必要继续了往后遍历数组了
            if (target - candidates[i] < 0) {
                break;
            }
            path.addLast(candidates[i]);
            // 这里的递归的 begin 为 i
            dfs(ans, path, candidates, target - candidates[i], i);
            path.pollLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        CombinationSum combinationSum = new CombinationSum();
        List<List<Integer>> lists = combinationSum.combinationSum(candidates, 7);

        System.out.println(lists);
    }
}
