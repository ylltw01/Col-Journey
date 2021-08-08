package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/gray-code/
 * 89. 格雷编码
 * 难度：中等
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * <p>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 * <p>
 * 格雷编码序列必须以 0 开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * <p>
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * <p>
 * 示例 2:
 * <p>
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 * 给定编码总位数为 n 的格雷编码序列，其长度为 2^n。当 n = 0 时，长度为 2^0 = 1。
 * 因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class GrayCode {

    /**
     * 回溯实现
     */
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        bt(n, 1, ans, sb);
        return ans;
    }

    private void bt(int n, int idx, List<Integer> ans, StringBuilder sb) {
        if (sb.length() == n) {
            ans.add(Integer.valueOf(sb.toString(), 2));
            return;
        }
        // 规律如下图，每一层都是先 0 后 1，所以就根据 idx 的数值，如果是 1 就 0， 如果是 2 就 1
        if (idx == 1) {
            for (int i = 0; i < 2; i++) {
                sb.append(i);
                bt(n, i + 1, ans, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            for (int i = 1; i >= 0; i--) {
                sb.append(i);
                bt(n, i, ans, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        GrayCode grayCode = new GrayCode();
        List<Integer> integers = grayCode.grayCode(3);
        System.out.println(StringUtils.join(integers, ", "));
    }

}

/*

            0                                  1
          [0,1]                             [1,0]

   0,0            0,1              1,1                  1,0
  [0,1]          [1,0]            [0,1]                [1,0]

0,0,0  0,0,1    0,1,1  0,1,0     1,1,0  1,1,1       1,0,1   1,0,0

 */
