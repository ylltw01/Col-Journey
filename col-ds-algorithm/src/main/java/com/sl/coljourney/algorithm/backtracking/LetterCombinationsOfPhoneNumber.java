package com.sl.coljourney.algorithm.backtracking;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 * 17. 电话号码的字母组合
 * 难度：中等
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 2: abc
 * 3: def
 * 4: ghi
 * 5: jkl
 * 6: mno
 * 7: pqrs
 * 8: tuv
 * 9: wxyz
 */
public class LetterCombinationsOfPhoneNumber {

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return ans;
        }

        Map<Character, String> digitMap = new HashMap<>();
        digitMap.put('2', "abc");
        digitMap.put('3', "def");
        digitMap.put('4', "ghi");
        digitMap.put('5', "jkl");
        digitMap.put('6', "mno");
        digitMap.put('7', "pqrs");
        digitMap.put('8', "tuv");
        digitMap.put('9', "wxyz");

        backtracking(digitMap, digits, 0, new StringBuilder(), ans);
        return ans;
    }

    private void backtracking(Map<Character, String> digitMap, String digits, int idx, StringBuilder sb, List<String> ans) {
        if (idx >= digits.length()) {
            ans.add(sb.toString());
        } else {
            String numString = digitMap.get(digits.charAt(idx));
            for (int i = 0; i < numString.length(); i++) {
                // 先 append
                sb.append(numString.charAt(i));
                backtracking(digitMap, digits, idx + 1, sb, ans);
                // 在 remove 减少新增 StringBuilder 个数，减少执行时间，减少内存使用
                sb.deleteCharAt(idx);
            }
        }
    }

    private void backtracking2(Map<Character, String> digitMap, String digits, int idx, StringBuilder sb, List<String> ans) {
        if (idx >= digits.length()) {
            ans.add(sb.toString());
        } else {
            String numString = digitMap.get(digits.charAt(idx));
            for (int i = 0; i < numString.length(); i++) {
                backtracking(digitMap, digits, idx + 1, new StringBuilder(sb).append(numString.charAt(i)), ans);
            }
        }
    }

}
/*
2 3 4

23 24 34
234

2345

23 24 25 34 35 45
234 245 345
2345

23456
2345 2456

 */
