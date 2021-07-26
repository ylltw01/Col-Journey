package com.sl.coljourney.algorithm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/minimum-window-substring/
 * 76. 最小覆盖子串
 * 难度：困难
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * <p>
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * <p>
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 */
public class MinimumWindowSubstring {

    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/minimum-window-substring/solution/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 自己写的
     */
    public String minWindow2(String s, String t) {
        String ans = "";
        if (s == null || t == null || t.length() > s.length()) {
            return ans;
        }
        if (s.equals(t)) {
            return s;
        }
        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> temp = new HashMap<>();

        for (char c : t.toCharArray()) {
            Integer times = tmap.getOrDefault(c, 0);
            tmap.put(c, times + 1);
        }

        int li = -1;
        int ri = 0;
        while (li < ri) {
            if (ri < s.length()) {
                char c = s.charAt(ri);
                if (tmap.containsKey(c)) {
                    if (li == -1) {
                        li = ri;
                    }
                    Integer times = temp.getOrDefault(c, 0);
                    temp.put(c, times + 1);
                }
                ri++;
            }

            while (isMatch(tmap, temp)) {
                String ts = s.substring(li, ri);
                ans = "".equals(ans) || ts.length() < ans.length() ? ts : ans;
                char liChar = s.charAt(li);
                Integer liTimes = temp.getOrDefault(liChar, 0);
                if (liTimes <= 1) {
                    temp.remove(liChar);
                } else {
                    temp.put(liChar, liTimes - 1);
                }
                li++;
            }
            if (ri > s.length() - 1) {
                li++;
            }
        }
        return ans;
    }

    private boolean isMatch(Map<Character, Integer> tmap, Map<Character, Integer> temp) {
        boolean match = true;
        if (tmap.size() != temp.size()) {
            match = false;
        }
        for (Character key : temp.keySet()) {
            if (temp.get(key) < tmap.get(key)) {
                match = false;
                break;
            }
        }
        return match;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring substring = new MinimumWindowSubstring();
        String s = substring.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);

        String s1 = substring.minWindow("bba", "ab");
        System.out.println(s1);

        String s2 = substring.minWindow("bbaa", "aba");
        System.out.println(s2);

        String s3 = substring.minWindow("bbaac", "aba");
        System.out.println(s3);
    }

/*
ADOBECODEBANC  ABC

ADOBEC
BECODEBA
CODEBA
BANC

 */


}
