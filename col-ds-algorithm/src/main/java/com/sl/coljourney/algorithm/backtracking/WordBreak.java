package com.sl.coljourney.algorithm.backtracking;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-break/
 * 139. 单词拆分
 * 难度：中等
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * <p>
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class WordBreak {

    /**
     * 解法1：官方的动态规划解法
     */
    public boolean wordBreakDpGf(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode-solution/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 解法2：动态规划解法
     */
    public boolean wordBreakDp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (String wd : wordDict) {
                if (wd.length() > i + 1) {
                    continue;
                }
                int idx = i + 1 - wd.length();
                if (dp[idx] && wd.equals(s.substring(idx, i + 1))) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 解法3：递归
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return bt(s, wordDict);
    }

    private boolean bt(String s, List<String> wordDict) {
        if (wordDict.contains(s) || s.length() == 0) {
            return true;
        }
        // 用于插入排序，将满足 startWith 的 wordDict 集合，按从大到小的顺序插入集合中
        LinkedList<String> temp = new LinkedList<>();
        for (String wd : wordDict) {
            if (s.startsWith(wd)) {
                // 插入排序
                if (temp.isEmpty()) {
                    temp.addLast(wd);
                } else {
                    int i = 0;
                    while (i < temp.size()) {
                        if (wd.length() < temp.get(i).length()) {
                            i++;
                        } else {
                            break;
                        }
                    }
                    if (i >= temp.size()) {
                        temp.addLast(wd);
                    } else {
                        temp.add(i, wd);
                    }
                }
            }
        }
        // 从长度大到小递归
        for (String wd : temp) {
            String sub = s.substring(s.indexOf(wd) + wd.length());
            // 这里用于剪枝，如果 sub 依然是 startsWith wd，则循环截取，降低递归次数
            while (sub.startsWith(wd)) {
                // 如果截取的字符串刚刚好在 wordDict 里面，则返回 true
                if (wordDict.contains(sub)) {
                    return true;
                }
                sub = sub.substring(sub.indexOf(wd) + wd.length());
            }
            // 继续递归
            if (bt(sub, wordDict)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
//        String s = "catsandog";
//        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");

//        String s = "applepenapple";
//        List<String> wordDict = Arrays.asList("apple", "pen");

        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");

//        String s = "bb";
//        List<String> wordDict = Arrays.asList("a","b","bbb","bbbb");

//        String s = "catskicatcats";
//        List<String> wordDict = Arrays.asList("cats","cat","dog","ski");

//        String s = "catcatcatcatcatcatcatcatcatcatccc";
//        List<String> wordDict = Arrays.asList("cat", "catcatcatccc");

        boolean catsandog = wordBreak.wordBreakDp(s, wordDict);
        System.out.println(catsandog);
    }

}
