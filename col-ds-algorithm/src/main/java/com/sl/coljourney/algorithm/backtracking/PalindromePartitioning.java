package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 * 131. 分割回文串
 * 难度：中等
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * <p>
 * 回文串 是正着读和反着读都一样的字符串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "a"
 * 输出：[["a"]]
 * <p>
 * 题解参考：
 * https://mp.weixin.qq.com/s/Pb1epUTbU8fHIht-g_MS5Q
 * <p>
 * <p>
 * "a","a","b"
 * "aa","b"
 * >                 aab
 * >           a|[ab]             aa|[b]        aab|[]
 * >     a|a|[b]    a|ab|[]       aa|b|[]
 * > a|a|b|[]
 * <p>
 * 题解：
 * 这个题，最最最关键的是，要理解怎么去切，如上图的切法
 * 例如：aab
 * 第一轮切出：a + [ab] ; aa + [b]  ; aab + []
 * 第二轮切出：a + [ab] 切出：a + a + [b] ; a + ab + []
 * >         aa + [b]  切出： aa + b + []
 * 第三轮切出：a + a + [b]  切出：a + a + b
 * <p>
 * 其中，只有当前这轮切出的子字符串是回文的时候才继续递归
 * 递归退出条件：所有字符都切完
 */
public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> path = new ArrayList<>();
        partitions(s, 0, ans, path);
        return ans;
    }

    private void partitions(String s, int start, List<List<String>> ans, List<String> path) {
        if (start >= s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start + 1; i <= s.length(); i++) {
            String sub = s.substring(start, i);
            // 判断当前的截取字段是否是回文，是则继续递归
            if (isPalindrome(sub)) {
                path.add(sub);
                partitions(s, i, ans, path);
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * a
     * a b a
     * aa
     */
    private boolean isPalindrome(String sub) {
        int l = 0;
        int r = sub.length() - 1;
        while (l <= r) {
            if (sub.charAt(l) != sub.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }


    public static void main(String[] args) {
        PalindromePartitioning partitioning = new PalindromePartitioning();
        List<List<String>> aabaa = partitioning.partition("aabaa");

        for (List<String> strings : aabaa) {
            System.out.println(StringUtils.join(strings, ", "));
        }
    }

}
/*
"a","a","b"
"aa","b"
                aab
        a|[ab]        aa|[b]    aab|[]
   a|a|[b]  a|ab|[]   aa|b|[]
a|a|b|[]

                aba
        a[ba]     ab[a]  aba[]
    ab[a]  aba[]  aba[]


a, a, b, a, a
a, a, b, aa
a, aba, a
aa, b, a, a
aa, b, aa
aabaa

                    aabaa




 */
