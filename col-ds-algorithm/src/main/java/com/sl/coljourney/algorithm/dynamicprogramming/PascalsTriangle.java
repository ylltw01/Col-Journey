package com.sl.coljourney.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle/
 * 118. 杨辉三角
 * 难度：简单
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * <p>
 * 示例 2:
 * <p>
 * 输入: numRows = 1
 * 输出: [[1]]
 * <p>
 * 提示:
 * <p>
 * 1 <= numRows <= 30
 */
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows < 1) {
            return ans;
        }
        ans.add(Collections.singletonList(1));
        for (int i = 2; i <= numRows; i++) {
            List<Integer> temp = ans.get(ans.size() - 1);
            List<Integer> level = new ArrayList<>();
            int idx = 0;
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    level.add(1);
                } else {
                    level.add(temp.get(idx) + temp.get(idx + 1));
                    idx++;
                }
            }
            ans.add(level);
        }
        return ans;
    }


}
