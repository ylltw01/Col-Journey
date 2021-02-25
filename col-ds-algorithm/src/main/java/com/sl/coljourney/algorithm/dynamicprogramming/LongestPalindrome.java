package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * 原题：https://www.nowcoder.com/practice/b4525d1d84934cf280439aeecc36f4af?tpId=117&tqId=37789&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 解析：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
 * 最长回文字符串
 * <p>
 * 题目描述
 * 对于一个字符串，请设计一个高效算法，计算其中最长回文子串的长度。
 * <p>
 * 给定字符串A以及它的长度n，请返回最长回文子串的长度。
 * <p>
 * 示例1
 * 输入
 * "abc1234321ab",12
 * 返回值
 * 7
 * <p>
 * <p>
 * 递推公式：
 * dp[i][j] = dp[i+1][j-1] && str[i] == str[j]
 */
public class LongestPalindrome {

    public int longestPalindrome(String str, int n) {
        int max = 0;
        boolean[][] dp = new boolean[n][n];
        for (int stepLen = 0; stepLen < n; stepLen++) {
            for (int i = 0; i + stepLen < n; i++) {
                int j = i + stepLen;
                // 步长为0时，i 和 j 相等，即都是同一个字符
                if (stepLen == 0) {
                    dp[i][j] = true;
                } else if (stepLen == 1) {
                    // 步长为1时，i 和 j 相邻，只需要判断str[i]  和 str[j] 是否相等
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    // 步长大于等于2的时候，就需要借助递推公式了
                    dp[i][j] = dp[i + 1][j - 1] && (str.charAt(i) == str.charAt(j));
                }

                if (dp[i][j]) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {

        LongestPalindrome solution = new LongestPalindrome();

        int aba = solution.longestPalindrome("1aba2", 5);

        System.out.println(aba);

    }

}
