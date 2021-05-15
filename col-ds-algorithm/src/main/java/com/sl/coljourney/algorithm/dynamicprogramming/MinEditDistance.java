package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://leetcode-cn.com/problems/edit-distance/submissions/
 * 72. 编辑距离
 * 难度：困难
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * 示例 2：
 * <p>
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * <p>
 * <p>
 * 解法：
 * 不匹配时三种操作：1. 添加字符，2. 删除字符，3. 替换字符
 * <p>
 * 因此当 a[i] != b[j] 时候，
 * 1. 添加一个字符，添加 b[j] 到 a[i] 位置则考察 a[i+1]，b[j]; 添加 a[i] 到 b[j] 位置则考察 a[i]，b[j+1]
 * 2. 删除一个字符，删除 a[i]，则考察 a[i+1]，b[j]；删除 b[j]，则考察 a[i]，b[j+1]
 * 3. 替换一个字符，可替换 a[i] 为 b[j]，或者替换 b[j] 为 a[i] ，继续考察 a[i+1] 和 b[j+1]
 * <p>
 * 综合以上：
 * 如果基于回溯算法，当  a[i] != b[j] 时，min(dist(a[i+1], b[j]), dist(a[i]，b[j+1]), dist(a[i+1] 和 b[j+1])) + 1
 * <p>
 * <p>
 * 回溯算法可能会出现很多重复计算的情况，这题在使用回溯算法，就会直接超时。
 * 基于动态规划：
 * 初始化一个数组 dp[a.len][b.len] 存储中间状态
 * 对于 a[i] != b[j] 就等于 min(dist(a[i+1], b[j]) + 1, dist(a[i], b[j+1]) + 1, dist(a[i-1], b[j-1]) + 1)
 * 对于 a[i] == b[j] 就等于 min(dist(a[i+1], b[j]) + 1, dist(a[i], b[j+1]) + 1, dist(a[i-1], b[j-1]))
 * <p>
 * 这是因为到
 * (a[i], b[j]) 的状态，只能会从(a[i+1], b[j]), (a[i], b[j+1]), a[i-1], b[j-1]) 三种状态得到
 * <p>
 * 无论 a[i] 是否等于 b[j]，(a[i+1], b[j]), (a[i], b[j+1]) 这两种情况，到 (a[i], b[j]) 都需要加 1
 * 因为两种情况要么是 i 不变，要么是 j 不变，能到这两种情况说明 a[i-1] 肯定不等于 b[j-1] 。
 * 而a[i] == a[j]时，dp[i-1][j-1] 已经算出了(i-1, j-1)的最短编辑距离，所以不需要加 1，反之就需要加1了。
 * <p>
 * <p>
 * 这里在初始化的dp数组的时候（这里说的是初始化dp[0][0] -- dp[i][0]）：
 * <p>
 * 这里其实就是计算 b[0] 到 a 中每个字符的编辑距离
 * 如果 b[0] == a[i], 那么 b[0] 与 a[i] 的编辑距离就为 i（i 为索引，从 0 开始）
 * 如果 i == 0, 表示是第一个元素就不想等，那么编辑距离为 1
 * 否则：就等于其左边的距离加 1
 * <p>
 * 如下例：
 * > h o r s e
 * r 1 2 2 3 4
 * o 2
 * s 3
 * <p>
 * >   r   o   s
 * h   1   2   3
 * o   2
 * r   2
 * s   3
 * e   4
 * <p>
 * 初始化 dp[0][j] 与上面类似
 */
public class MinEditDistance {

    /**
     * 基于动态规划
     */
    public int minDistanceDp(String word1, String word2) {
        if (word1 == null || "".equals(word1)) {
            if (word2 == null || "".equals(word2)) {
                return 0;
            } else {
                return word2.length();
            }
        }
        if (word2 == null || "".equals(word2)) {
            return word1.length();
        }

        int[][] dp = new int[word1.length()][word2.length()];

        // 初始化二维数组的第一列
        for (int i = 0; i < word1.length(); i++) {
            /*
            这里其实就是计算 b[0] 到 a 中每个字符的编辑距离
            如果 b[0] == a[i], 那么 b[0] 与 a[i] 的编辑距离就为 i
            如果 i == 0, 表示是第一个元素就不想等，那么编辑距离为 1
            否则：就等于其左边的距离加 1
            如下例：
              h o r s e
            r 1 2 2 3 4
            o 2
            s 3
                r   o   s
            h   1   2   3
            o   2
            r   2
            s   3
            e   4
             */
            if (word1.charAt(i) == word2.charAt(0)) {
                dp[i][0] = i;
            } else if (i == 0) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }
        // 初始化二维数组的第一行
        for (int j = 0; j < word2.length(); j++) {
            if (word2.charAt(j) == word1.charAt(0)) {
                dp[0][j] = j;
            } else if (j == 0) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1] + 1;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }

    /**
     * 基于回溯算法
     */
    public int minDistanceBack(String word1, String word2) {
        return distanceBack(0, 0, word1, word2, 0);
    }

    public int distanceBack(int i, int j, String word1, String word2, int dist) {
        if (i >= word1.length() || j >= word2.length()) {
            // word1 还未判断完
            if (i < word1.length()) {
                dist += word1.length() - i;
            }
            // word2 还未判断完
            if (j < word2.length()) {
                dist += word2.length() - j;
            }
            return dist;
        }
        if (word1.charAt(i) != word2.charAt(j)) {
            int disIp1 = distanceBack(i + 1, j, word1, word2, dist);
            int disJp1 = distanceBack(i, j + 1, word1, word2, dist);
            int disIjp1 = distanceBack(i + 1, j + 1, word1, word2, dist);
            return Math.min(Math.min(disIp1, disJp1), disIjp1) + 1;
        } else {
            return distanceBack(i + 1, j + 1, word1, word2, dist);
        }
    }

    public static void main(String[] args) {
        MinEditDistance distance = new MinEditDistance();
//        int i = distance.minDistanceBack("horse", "ros");
//        int i = distance.minDistanceBack("intention", "execution");

//                int i = distance.minDistanceDp("horse", "ros");
//        int i = distance.minDistanceDp("intention", "execution");
//        int i = distance.minDistanceDp("zoologicoarchaeologist", "zoogeologist");
        int i = distance.minDistanceDp("a", "z");

        System.out.println(i);
    }

}

/*
  h o r s e
r 1 2 2 3 4
o 2
s 3

    r   o   s
h   1   2   3
o   2
r   2
s   3
e   4

 */