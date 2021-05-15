package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * 牛客的题，惜字如金，不会解了
 */
public class MinEditDistanceNiu {

    public int minEditCost(String str1, String str2, int ic, int dc, int rc) {
        int[][] dp = new int[str1.length()][str2.length()];

        // 初始化二维数组的第一列
        int minDcRc = Math.min(dc, rc);
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                dp[i][0] = i * minDcRc;
            } else if (i == 0) {
                // 第一个字符不相等，只能替换，相当于 a 和 b
                dp[i][0] = rc;
            } else {
                dp[i][0] = dp[i - 1][0] + minDcRc;
            }
        }
        // 初始化二维数组的第一行
        for (int j = 0; j < str2.length(); j++) {
            if (str2.charAt(j) == str1.charAt(0)) {
                dp[0][j] = j * minDcRc;
            } else if (j == 0) {
                dp[0][j] = rc;
            } else {
                dp[0][j] = dp[0][j - 1] + minDcRc;
            }
        }

        int min = Math.min(ic, dc);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + min, dp[i][j - 1] + min), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + min, dp[i][j - 1] + min), dp[i - 1][j - 1] + rc);
                }
            }
        }
        return dp[str1.length() - 1][str2.length() - 1];
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

    public int minEditCost3 (String str1, String str2, int ic, int dc, int rc) {
        // write code here
        int[] res = new int[str2.length() + 1];
        int i, j, minres = Math.min(ic + dc, rc), leftTop, t;
        char[] c1 = str1.toCharArray(), c2 = str2.toCharArray();

        res[0] = 0;

        for(i = 1; i <= c2.length; i++)
            res[i] = i * ic;

        for(i = 1;i <= c1.length; i++) {
            leftTop = res[0];
            res[0] = i * dc;
            for(j = 1; j <= c2.length; j++) {
                t = res[j];
                if(c1[i-1] == c2[j-1])
                    res[j] = leftTop;
                else
                    res[j] = Math.min(Math.min(res[j] + dc, res[j-1] + ic), leftTop + minres);
                leftTop = t;
            }
        }
        return res[c2.length];
    }

    public static void main(String[] args) {
        MinEditDistanceNiu distanceNiu = new MinEditDistanceNiu();
//        int i = distanceNiu.minEditCost("abc", "adc", 5, 3, 2);
//        int i = distanceNiu.minEditCost("abc","adc",5,3,100);
        int i1 = distanceNiu.minEditCost2("a", "b", 5, 3, 100);
        int i2 = distanceNiu.minEditCost2("a", "bc", 5, 3, 100);
        System.out.println(i1);
        System.out.println(i2);

        int i3 = distanceNiu.minEditCost3("a", "b", 5, 3, 100);
        int i4 = distanceNiu.minEditCost3("a", "bc", 5, 3, 100);
        System.out.println(i3);
        System.out.println(i4);
    }

}

/*
        r   o   s
    h   1   2   3
    o   2
    r   2
    s   3
    e   4

    i n t e n t i o n
    e x e c u t i o n

    i-1, j-1

    i-1, j (删除j-1)
    i-1, j (插入一个j-1)
 */
