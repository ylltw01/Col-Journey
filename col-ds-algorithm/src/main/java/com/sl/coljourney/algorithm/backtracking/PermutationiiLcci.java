package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/permutation-ii-lcci/
 * 面试题 08.08. 有重复字符串的排列组合
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 * <p>
 * 示例1:
 * <p>
 * 输入：S = "qqe"
 * 输出：["eqq","qeq","qqe"]
 * 示例2:
 * <p>
 * 输入：S = "ab"
 * 输出：["ab", "ba"]
 * 提示:
 * <p>
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 */
public class PermutationiiLcci {

    public String[] permutation(String S) {
        List<String> ans = new ArrayList<>();
        dfs(S.toCharArray(), 0, new StringBuilder(), ans);
        return ans.toArray(new String[]{});
    }

    private void dfs(char[] array, int start, StringBuilder sb, List<String> ans) {
        if (start == array.length) {
            ans.add(sb.toString());
            return;
        }

        // set 用于减枝
        Set<Character> set = new HashSet<>();
        for (int i = start; i < array.length; i++) {
            if (!set.contains(array[i])) {
                // 先 append
                sb.append(array[i]);
                // 先 swap
                swap(array, i, start);
                // 继续
                dfs(array, start + 1, sb, ans);
                // 在 swap 回去
                swap(array, start, i);
                // 在 delete
                sb.deleteCharAt(sb.length() - 1);
                set.add(array[i]);
            }
        }
    }

    private void swap(char[] array, int f, int t) {
        char temp = array[f];
        array[f] = array[t];
        array[t] = temp;
    }

    public static void main(String[] args) {
        PermutationiiLcci lcci = new PermutationiiLcci();
        String[] ans = lcci.permutation("qqe");
        System.out.println(StringUtils.join(ans, ", "));
    }

}
/*

            abc
        a [bc]     b [ac]    c [ba]
       abc acb     bac bca   cba cab

        q             q            e
       [qe]          [qe]         [qq]
                   qq[e]  qe[q]   eqq

 */
