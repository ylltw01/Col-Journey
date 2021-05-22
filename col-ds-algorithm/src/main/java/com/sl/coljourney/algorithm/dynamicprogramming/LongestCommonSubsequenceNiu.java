package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/6d29638c85bb4ffd80c020fe244baf11?tpId=117&tqId=37798&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 最长公共子序列 II
 * 难度：中等
 * 题目描述
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
 * <p>
 * 示例1
 * 输入
 * "1A2C3D4B56","B1D23A456A"
 * 返回值
 * "123456"
 * <p>
 * 示例2
 * 输入
 * "abc","def"
 * 返回值
 * "-1"
 * <p>
 * 示例3
 * 输入
 * "abc","abc"
 * 返回值
 * "abc"
 * <p>
 * 题解：与 leetcode LongestCommonSubsequence 类似
 * 唯一不同的是，需要返回公共自序列的字符串
 * <p>
 * 1. 与 leetcode LongestCommonSubsequence 类似使用动态规划，填写数组dp
 * 2. 从 dp[s2.length][s1.length] 逆序查找数组 dp。
 * 3. 逻辑是，如果 dp[i][j] == dp[i][j-1] 则需要继续向左查找 j--。
 * 4. 如果 dp[i][j] == dp[i-1][j] 则需要继续向上查找 i--。
 * 5. 否则将 s1.charAt(j - 1) 追加到字符串，并且 i--，j-- 即向左上角继续查找。
 * 6. 反转字符串
 * 注：两个字符串在 dp 中的顺序，如下图
 * <p>
 * >    1  a  1  a  3  1
 * >    0  0  0  0  0  0
 * 1 0  1  1  1  1  1  1
 * a 0  1  2  2  2  2. 2
 * 2 0  1  2  2  2. 2. 2
 * 3 0  1. 2. 2. 2. 3. 3
 * 1 0  1  2. 3. 3. 3  4
 * <p>
 * <p>
 * > 1  A. 2. C. 3. D. 4. B. 5. 6
 * B 0. 0. 0. 0. 0. 0. 0  1  1. 1
 * 1 1. 1  1. 1. 1. 1. 1. 1. 1. 1
 * D 1  1. 1. 1. 1. 2. 2. 2. 2  2
 * 2 1. 1. 2. 2. 2. 2. 2. 2. 2. 2
 * 3 1. 1. 2  2. 3. 3  3. 3. 3. 3
 * A 1  2. 2. 2. 3. 3  3. 3. 3. 3
 * 4 1. 2  2  2. 3. 3  4  4. 4. 4
 * 5 1. 2. 2. 2  3. 3  4. 4. 5  5
 * 6.1  2. 2. 2. 3. 3  4. 4  5. 6
 */
public class LongestCommonSubsequenceNiu {

    /**
     * 牛客题目
     */
    public String longestCommonSubsequenceNiu(String s1, String s2) {
        int[][] dp = new int[s2.length() + 1][s1.length() + 1];

        for (int i = 1; i < dp.length; i++) {
            char c1 = s2.charAt(i - 1);
            for (int j = 1; j < dp[i].length; j++) {
                char c2 = s1.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        // 如果 dp[s2.length()][s1.length()] 为 0，表示没有公共子序列
        if (dp[s2.length()][s1.length()] == 0) {
            return "-1";
        }

        int i = s2.length();
        int j = s1.length();
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i][j - 1]) {
                j--;
            } else if (dp[i][j] == dp[i - 1][j]) {
                i--;
            } else {
                sb.append(s1.charAt(j - 1));
                i--;
                j--;
            }
        }
        return sb.reverse().toString();
    }

}
