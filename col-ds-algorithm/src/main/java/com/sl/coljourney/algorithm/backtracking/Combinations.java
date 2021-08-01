package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combinations/
 * 77. 组合
 * 难度：中等
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * <p>
 * 你可以按 任何顺序 返回答案。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4, k = 2
 * 输出：
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 输入：
 * 2
 * 1
 * 预期结果：
 * [[1],[2]]
 * <p>
 * 输入：
 * 4
 * 3
 * 预期结果：
 * [[1,2,3],[1,2,4],[1,3,4],[2,3,4]]
 * <p>
 * 参考题解：
 * https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> com = new ArrayList<>();
        combine(n, k, 1, com, ans);
        return ans;
    }

    private void combine(int n, int k, int start, List<Integer> com, List<List<Integer>> ans) {
        if (com.size() == k) {
            ans.add(new ArrayList<>(com));
            return;
        }

        for (int i = start; i <= n; i++) {
        // 剪枝：i <= n - (k - com.size) + 1
//        for (int i = start; i <= n - (k - com.size()) + 1; i++) {
            com.add(i);
            System.out.println("add后：" + i + " ：" + StringUtils.join(com, ", "));
            combine(n, k, i + 1, com, ans);
            com.remove(com.size() - 1);
            System.out.println("remove后：" + i + " ：" +  StringUtils.join(com, ", "));
        }
    }

    public static void main(String[] args) {
        Combinations combinations = new Combinations();
        List<List<Integer>> combine = combinations.combine(4, 3);
        System.out.println(combine.size());

        System.out.println(Math.pow(2, -1));

        float f1 = 0.3f;
        double f2 = 0.3;
        System.out.println(f1 == f2);


    }

}
/*
        4,2
   1     2     3    4
2 3 4   3 4   3 4   []


                             4,3
           1                   2           3        4
         2,3,4               [3,4]        [4]        []

    1,2     1,3     4       3     4
   [3,4]    [4]     []

1,2,3  1,2,4   1,3,4
 [4]     []     []

 */
