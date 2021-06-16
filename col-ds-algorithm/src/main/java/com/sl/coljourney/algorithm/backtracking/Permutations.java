package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/permutations/
 * 46. 全排列
 * 难度：中等
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 题解：
 * 回溯
 * f(1,2,3) = f(1 + f(2,3))  + f(2 + f(1,3))   + f(3 + f(1,2))
 * <p>
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }

        recursion(ans, numList, 0);
        return ans;
    }

    private void recursion(List<List<Integer>> ans, List<Integer> numList, int idx) {
        if (idx >= numList.size() - 1) {
            ans.add(numList);
            return;
        }

        // 原封不动 1 2 3
        recursion(ans, numList, idx + 1);
        for (int i = idx + 1; i < numList.size(); i++) {
            // 从第二个开始交换，然后递归
            List<Integer> newNumList = new ArrayList<>(numList);
            Collections.swap(newNumList, idx, i);
            recursion(ans, newNumList, idx + 1);
        }
    }

    /**
     * 官方题解
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = permutations.permute(nums);

        for (List<Integer> li : list) {
            for (Integer in : li) {
                System.out.print(in + " ");
            }
            System.out.println();
        }
    }

}
