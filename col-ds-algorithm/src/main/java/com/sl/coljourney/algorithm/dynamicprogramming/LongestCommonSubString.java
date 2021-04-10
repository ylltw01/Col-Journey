package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/f33f5adc55f444baa0e0ca87ad8a6aac?tpId=117&tqId=37799&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 最长公共子串
 * 难度：中等
 * <p>
 * 题目描述
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
 * 题目保证str1和str2的最长公共子串存在且唯一。
 * 示例1
 * 输入
 * "1AB2345CD","12345EF"
 * 返回值
 * "2345"
 * <p>
 * 题解：
 * 使用动态规划，【状态转移表法】
 * 定义二维数组：dp[s2.length+1][s1.length+1]
 * >  dp[0][0] - dp[0][s1.length] 都为 0
 * >  dp[0][0] - dp[s2.length][0] 都为 0
 * 因为是公共子串，因此当字符串位置s1(m - 1) == s2(n -1) 时，则 dp[m][n] = dp[m - 1][n - 1] + 1
 * 然后记录最大子串的长度和最大子串的结束索引位，最后在结束后截取（如果是记录的 s1 的索引，则使用 s1 截取，反之亦然）
 * <p>
 * 如下图所示：
 * （索引）（字符串）
 * >  0	     1	2	3	4	5	6	7	8	9       （索引）
 * >  0	     	1	A	B	2	3	4	5	C	D   （字符串）
 * >  1	     1	1   0   0   0   0   0   0   0   0
 * >  2	     2	0	0	0	1   0   0   0   0   0
 * >  3	     3					2
 * >  4	     4						3
 * >  5	     5							4
 * >  6	     E
 * >  7	     F
 */
public class LongestCommonSubString {

    /**
     * longest common substring
     *
     * @param str1 string字符串 the string
     * @param str2 string字符串 the string
     * @return string字符串
     */
    public String longestCommonSubString(String str1, String str2) {
        int[][] dp = new int[str2.length() + 1][str1.length() + 1];

        int maxLength = 0;
        int maxEndIndex = -1;
        for (int i = 1; i < str2.length() + 1; i++) {
            for (int j = 1; j < str1.length() + 1; j++) {
                if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        maxEndIndex = j;
                    }
                }
            }
        }

        if (maxEndIndex != -1) {
            return str1.substring(maxEndIndex - maxLength, maxEndIndex);
        }
        return "";
    }

    public static void main(String[] args) {
        LongestCommonSubString subString = new LongestCommonSubString();
        System.out.println(subString.longestCommonSubString("1AB2345CD", "12345EF"));
        System.out.println(subString.longestCommonSubString("2LQ74WK8Ld0x7d8FP8l61pD7Wsz1E9xOMp920hM948eGjL9Kb5KJt80", "U08U29zzuodz16CBZ8xfpmmn5SKD80smJbK83F2T37JRqYfE76vh6hrE451uFQ100ye9hog1Y52LDk0L52SuD948eGjLz0htzd5YF9J1Y6oI7562z4T2"));
        /*
        1
        1A A
        1AB AB B
        1AB2 AB2 B2 2
        1AB23 AB23 B23 23 3

        dp[0][0] = 1
        dp[0][1] = A
        dp[0][2] = B

        dp[0][0] = 1
        dp[1][0] = A
        dp[2][0] = B

         */
    }

}
