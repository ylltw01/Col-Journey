package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * <p>
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSum3 {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        combination(k, n, 0, 0, path, ans);
        return ans;
    }

    private void combination(int k, int n, int sum, int start, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == k - 1) {
            if (n - sum > path.get(path.size() - 1) && n - sum < 10) {
                path.add(n - sum);
                ans.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            return;
        }

        for (int i = start + 1; i <= 9; i++) {
            path.add(i);
            combination(k, n, sum + i, i, path, ans);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum3 sum3 = new CombinationSum3();
        List<List<Integer>> lists = sum3.combinationSum3(3, 9);

        System.out.println(lists.size());
    }

}
