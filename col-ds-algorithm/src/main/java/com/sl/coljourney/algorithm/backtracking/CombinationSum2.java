package com.sl.coljourney.algorithm.backtracking;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/75e6cd5b85ab41c6a7c43359a74e869a?tpId=117&&tqId=37742&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/combination-sum-ii/
 * 40. 组合总和 II
 * 难度：中等
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 题解：
 * 这个题和 39.组合总和 的区别就是出去重复的元素
 * 剪枝，即同一层只会使用相同元素的第一个元素
 */
public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            // 小剪枝：同一层相同数值的结点，从第 2 个开始，候选数更少，结果一定发生重复，因此跳过，用 continue
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            // 剪枝，因为是有序的，如果 target - candidates[i] 小于 0 了，就没必要继续了往后遍历数组了
            if (target - candidates[i] < 0) {
                break;
            }
            path.add(candidates[i]);
            dfs(ans, path, candidates, target - candidates[i], i + 1);
            path.pollLast();
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        CombinationSum2 combinationSum = new CombinationSum2();
        List<List<Integer>> lists = combinationSum.combinationSum2(candidates, 8);

        System.out.println(lists);
    }
}
