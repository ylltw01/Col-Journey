package com.sl.coljourney.algorithm;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 * 5. 最长回文子串
 * 难度：中等
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 */
public class LongestPalindromicSubstring {

    /**
     * 中心扩展
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        char[] array = s.toCharArray();
        String ans = String.valueOf(array[0]);

        for (int i = 0; i < array.length - 1; i++) {
            int left;
            int right;

            // 如果当前字符与下一个字符相等例如：abba 这种 arr[i] = b
            if (array[i] == array[i + 1]) {
                left = i - 1;
                right = i + 2;
                ans = maxLengthSb(s, array, ans, left, right);
            }
            // 其他情况，都以 arr[i] 为中心，向两边扩散
            left = i - 1;
            right = i + 1;
            ans = maxLengthSb(s, array, ans, left, right);
        }
        return ans;
    }

    private String maxLengthSb(String s, char[] array, String ans, int left, int right) {
        while (left >= 0 && right < array.length && array[left] == array[right]) {
            left--;
            right++;
        }

        if (right - left - 1 > ans.length()) {
            ans = s.substring(left + 1, right);
        }
        return ans;
    }
}
/*
0 1 2 3
c b b d

i = 1
l = 0
r = 3

aaaa

ccc

bb
abb

babadabab
 */