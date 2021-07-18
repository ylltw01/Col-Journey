package com.sl.coljourney.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * 3. 无重复字符的最长子串
 * 难度：中等
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 右指针记录最大的index
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        // 长度超过 1 的最小长度至少为 1
        int max = 1;
        Set<Character> set = new HashSet<>();
        // 右指针
        int end = 1;
        // 初始化 set 集合为 index 0
        set.add(s.charAt(0));
        for (int i = 0; i < s.length(); i++) {
            // end 指针继续向右扩散，直到与 set 中重合 【 下面代码可以简化，但是为了好理解，这样写一下 】
            while (end < s.length()) {
                if (!set.contains(s.charAt(end))) {
                    set.add(s.charAt(end));
                    end++;
                } else {
                    break;
                }
            }
            // 在 end 指针重合了之后，将从 set 集合中移除 i 处的字符
            set.remove(s.charAt(i));
            // 然后计算当前区间的最大值
            max = Math.max(max, end - i);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters lswr = new LongestSubstringWithoutRepeatingCharacters();
        int dvdf = lswr.lengthOfLongestSubstring("dvdf");
        System.out.println(dvdf);

        int au = lswr.lengthOfLongestSubstring("au");
        System.out.println(au);

        int bbbbbb = lswr.lengthOfLongestSubstring("bbbbbb");
        System.out.println(bbbbbb);
        int pwwkew = lswr.lengthOfLongestSubstring("pwwkew");
        System.out.println(pwwkew);
        int abcabcbb = lswr.lengthOfLongestSubstring("abcabcbb");
        System.out.println(abcabcbb);

    }

}
/*
p w w k e w

p = 1
pw = 2
pww = 2
w = 1
wk = 2
wke = 3
wkew = 3
kew = 3
ew = 2
w = 1



   p  w  w  k  e  w
p  0  1
w
w
k
e
w
 */