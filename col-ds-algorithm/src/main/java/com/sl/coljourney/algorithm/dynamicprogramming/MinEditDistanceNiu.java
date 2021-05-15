package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/05fed41805ae4394ab6607d0d745c8e4?tpId=117&tqId=37801&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 最小编辑代价
 * 难度：较难
 * <p>
 * 题目描述
 * 给定两个字符串str1和str2，再给定三个整数ic，dc和rc，分别代表插入、删除和替换一个字符的代价，请输出将str1编辑成str2的最小代价。
 * <p>
 * 示例1
 * 输入
 * "abc","adc",5,3,2
 * 返回值
 * 2
 * <p>
 * 示例2
 * 输入
 * "abc","adc",5,3,100
 * 返回值
 * 8
 * <p>
 * 题解：
 * 【【注意】】：在解题中，只能修改 str1，只考虑修改 str1 的情况。别考虑其他的，否则，容易想岔。
 * 思路参考：leetcode 官方解法：https://leetcode-cn.com/problems/edit-distance/solution/bian-ji-ju-chi-by-leetcode-solution/
 * <p>
 * 在初始化数组中：
 * 1. dp 数组的长度比两个字符串大 1 的长度，这是为了留出一个空字符串与其他字符串比较
 * 2. 在初始化 dp[i][0] 系列的时候，相当于拿 str2 的空串与 str1 所有字符比较，那么 D[i][0] 相当于对 str1 执行 i 次删除操作
 * 3. 同理，在初始化 dp[0][j] 系列的时候，相当于拿 str1 的空串与 str2 所有字符比较，那么 D[0][j] 相当于对 str1 执行 j 次插入操作
 * <p>
 * 在进行循环赋值的时候：
 * > 如果 str[i] == str[j], 那么：dp[i][j] = dp[i-1][j-1]
 * > 如果 str[i] != str[j], 那么就有三种情况：
 * 【【首先有一个共识】】：此时，dp[i-1][j] 是已经进行过最小编辑次数计算的
 * 1. dp[i-1][j]：基于以上共识，因此 dp[i][j] 的编辑次数可以是，dp[i-1][j] 将 str1[i] 删除。即 dp[i][j] = dp[i-1][j] + dc。
 * 2. dp[i][j-1]：基于以上共识，因此 dp[i][j] 的编辑次数可以是，dp[i][j-1] 将 str2[j] 插入到 str1 末尾 。即 dp[i][j] = dp[i][j-1] + ic。
 * 3. dp[i-1][j-1]：基于以上共识，因此 dp[i][j] 的编辑次数可以是，将 str1 的 str1[i] 替换为 str2[j]。即  dp[i][j] = dp[i-1][j-1] + rc。
 * 4. 计算以上 3 个表达式的最小值。
 */
public class MinEditDistanceNiu {

    public int minEditCost(String str1, String str2, int ic, int dc, int rc) {
        int wlen1 = str1.length();
        int wlen2 = str2.length();

        // 只要是有任何一个为空串，则返回另外一个字符串的长度
        if (wlen1 * wlen2 == 0) {
            return wlen1 + wlen2;
        }
        // 注意，这里初始化的是比两个字符串大 1 的长度
        int[][] dp = new int[wlen1 + 1][wlen2 + 1];

        // 初始化第一列，此时的第一列相当于是 word2 的空串 （数组长度比字符串长1）与 word1 所有字符比较，那么 D[i][0] 相当于对 word1 执行 i 次删除操作
        for (int i = 0; i <= wlen1; i++) {
            dp[i][0] = i * dc;
        }

        // 初始化第一行，D[0][j] 相当于对 word1 执行 j 次插入操作。
        for (int j = 0; j <= wlen2; j++) {
            dp[0][j] = j * ic;
        }

        for (int i = 1; i <= wlen1; i++) {
            for (int j = 1; j <= wlen2; j++) {
                if (str1.charAt(i - 1) != str2.charAt(j - 1)) {
                    // 相当于对 str1 删除了 str1[i]
                    int left = dp[i - 1][j] + dc;
                    // 相当于对 str1 末尾增加一个字符 str2[j]
                    int down = dp[i][j - 1] + ic;
                    // 相当于将 str1 的 str1[i] 替换为 str2[j]
                    int leftDown = dp[i - 1][j - 1] + rc;
                    dp[i][j] = Math.min(left, Math.min(down, leftDown));
                } else {
                    // 如果相等，则与 dp[i - 1][j - 1] 一致
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[wlen1][wlen2];
    }

    public int minEditCost2(String str1, String str2, int ic, int dc, int rc) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i * dc;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j * ic;
        }
        for (int i = 1; i <= m; i++) {
            char c1 = str1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = str2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int insert = dp[i][j - 1] + ic;
                    int delete = dp[i - 1][j] + dc;
                    int replace = dp[i - 1][j - 1] + rc;
                    dp[i][j] = Math.min(replace, Math.min(insert, delete));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        MinEditDistanceNiu distanceNiu = new MinEditDistanceNiu();
//        int i1 = distanceNiu.minEditCost("a", "b", 5, 3, 100);
//        int i2 = distanceNiu.minEditCost("a", "bc", 5, 3, 100);

        int i1 = distanceNiu.minEditCost2("a", "b", 5, 3, 100);
        int i2 = distanceNiu.minEditCost2("a", "bc", 5, 3, 100);
        System.out.println(i1);
        System.out.println(i2);

    }

}

/*
      0  1
      #  b
0  #  0  5
1  a  3

 (1，1)

 (0, 1)
 (1, 0)
 (0, 0)
 */
