package com.sl.coljourney.algorithm.dynamicprogramming;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/pascals-triangle-ii/
 * 119. 杨辉三角 II
 * 难度：简单
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * <p>
 * 示例 2:
 * <p>
 * 输入: rowIndex = 0
 * 输出: [1]
 * <p>
 * 示例 3:
 * <p>
 * 输入: rowIndex = 1
 * 输出: [1,1]
 * <p>
 * 提示:
 * <p>
 * 0 <= rowIndex <= 33
 */
public class PascalsTriangle2 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        if (rowIndex == 0) {
            ans.add(1);
            return ans;
        }
        for (int i = 2; i <= rowIndex + 1; i++) {
            List<Integer> level = new ArrayList<>();
            int idx = 0;
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    level.add(1);
                } else {
                    level.add(ans.get(idx) + ans.get(idx + 1));
                    idx++;
                }
            }
            ans = level;
        }
        return ans;
    }

    public static void main(String[] args) {
        PascalsTriangle2 pascalsTriangle2 = new PascalsTriangle2();
        List<Integer> row = pascalsTriangle2.getRow(3);
        System.out.println(StringUtils.join(row, ", "));
    }
}
