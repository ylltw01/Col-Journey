package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * * https://leetcode-cn.com/problems/longest-common-subsequence/
 * 1143. 最长公共子序列
 * 难度：中等
 * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * <p>
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * <p>
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * 示例 2：
 * <p>
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * 示例 3：
 * <p>
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * <p>
 * 题解：
 * 使用动态规划，【状态转移表法】
 * 定义二维数组：dp[s2.length+1][s1.length+1]
 * >  dp[0][0] - dp[0][s1.length] 都为 0
 * >  dp[0][0] - dp[s2.length][0] 都为 0
 * <p>
 * 公共子序列与公共子串不同：公共子串必须是连续的；但是公共子序列不需要，只要顺序一致即可，不要求连续。
 * 因此，如果判断到字符串匹配到的情况下，后续的字符串也需要填值。
 * <p>
 * 如果匹配：1. 则当前dp[i][j] = dp[i - 1][j - 1] + 1，即左上角的值加 1
 * 如果未匹配上：1. 如果其左边已经匹配上了，因为公共子序列的不要求连续的特性，则需要将左边的值往右边传递。
 * >           2. 如果其左边未被匹配上，那么就取其上方的值即，dp[i - 1][j] 的值。
 * >           综合一下：直接取左边传递和上方的最大值即可。
 * <p>
 * 例1如下：
 * >    A	B	C	D	E
 * A	1	1	1	1	1
 * C	1	1	2	2	2
 * E	1	1	2	2	3
 * <p>
 * 例2如下：带有重复的字符
 * >    A	B	C	B	A
 * A	1	1	1	1	1
 * B	1	2	2	2	2
 * C	1	2	3	3	3
 * B	1	2	2	4	4
 * C	1	2	3	4	4
 * B	1	2	2	4	4
 * A	1	2	2	4	5
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text2.length() + 1][text1.length() + 1];

        int maxCount = 0;
        for (int i = 1; i < text2.length() + 1; i++) {
            for (int j = 1; j < text1.length() + 1; j++) {
                if (text1.charAt(j - 1) == text2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
                maxCount = Math.max(maxCount, dp[i][j]);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        // 3
        System.out.println(subsequence.longestCommonSubsequence("abcde", "ace"));
        // 2
        System.out.println(subsequence.longestCommonSubsequence("ezupkr", "ubmrapg"));
        // 5
        System.out.println(subsequence.longestCommonSubsequence("hofubmnylkra", "pqhgxgdofcvmr"));
        // 5
        System.out.println(subsequence.longestCommonSubsequence("abcba", "abcbcba"));

    }


}
