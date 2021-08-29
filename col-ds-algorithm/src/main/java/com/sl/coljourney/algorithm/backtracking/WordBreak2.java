package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/word-break-ii/
 * 140. 单词拆分 II
 * 难度：困难
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
 * 返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * <p>
 * 示例 1：
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * 示例 2：
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * <p>
 * 示例 3：
 * <p>
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * >             pineapplepenapple
 * >
 * >       pine                   pineapple
 * > apple    applepen              pen
 * > pen        apple              apple
 * > apple
 */
public class WordBreak2 {

    /**
     * 回溯写法一，代码更精简
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        bt(s, 0, wordDict, ans, new ArrayList<>());
        return ans;
    }

    private void bt(String s, int len, List<String> wordDict, List<String> ans, List<String> path) {
        // 这里不用 contains，因为如：a aa aaaa 这种情况，会被丢失掉部分，解法二就有这个问题
        if (len >= s.length()) {
            ans.add(String.join(" ", path));
            return;
        }

        for (String wd : wordDict) {
            if (s.startsWith(wd, len) && len + wd.length() <= s.length()) {
                path.add(wd);
                bt(s, len + wd.length(), wordDict, ans, path);
                // 注意，这里的删除，是 size() - 1
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 回溯写法二：这个写法更麻烦
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new ArrayList<>();
        bt(s, wordDict, ans, new ArrayList<>());
        return ans;
    }

    private void bt(String s, List<String> wordDict, List<String> ans, List<String> path) {
        // 这里使用了 contains 方法，因此需要处理如：a aa aaaa 这种情况
        if (wordDict.contains(s) || s.length() == 0) {
            StringBuilder sb = new StringBuilder();
            for (String value : path) {
                sb.append(value);
                sb.append(" ");
            }
            if (s.length() != 0) {
                sb.append(s);
            }
            ans.add(sb.toString().trim());

            // 这里就是处理如：a aa aaaa 这种情况
            boolean jx = false;
            for (String wd : wordDict) {
                // 如果当前的 s 在 wordDict 中还存在开始的 wd 情况，则继续
                if (!wd.equals(s) && s.startsWith(wd)) {
                    jx = true;
                    break;
                }
            }
            if (!jx) {
                return;
            }
        }
        for (String wd : wordDict) {
            // 这里就要加上 !s.equals(wd) 判断了，因为 wd 与当前 s 相等的，在上面的代码已经添加过了，不判断就重复了
            if (s.startsWith(wd) && !s.equals(wd)) {
                path.add(wd);
                String sub = s.substring(s.indexOf(wd) + wd.length());
                bt(sub, wordDict, ans, path);
                // 注意，这里的删除，是 size() - 1
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        WordBreak2 wb = new WordBreak2();

//        String s = "pineapplepenapple";
//        List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");

        String s = "aaaaaaa";
        List<String> wordDict = Arrays.asList("aaaa", "aa", "a");

        List<String> list = wb.wordBreak(s, wordDict);
        System.out.println(list.size());
        for (String sub : list) {
            System.out.println(sub);
        }
    }

}
/*
["aaaa aa a","aaaa a aa","aaaa a a a","aa aaaa a","aa aa aa a","aa aa a aa","aa aa a a a","aa a aaaa","aa a aa aa","aa a aa a a","aa a a aa a","a a aa a aa","a a aa a a a","a aaaa aa","a aaaa a a","a aa aaaa","a aa aa aa","a aa aa a a","aa a a aa a","a a aa a aa","a a aa a a a","a a aaaa a","a a aa aa a","a a aa a aa","a a aa a a a","a a a aaaa","a a a aa aa","a a a aa a a","a a a a aa a","a a a a a aa","a a a a a a a"]

a
a
aaaa aa
a     a

["a a a a a a a",
"aa a a a a a",
"a aa a a a a",
"a a aa a a a",
"aa aa a a a",
"aaaa a a a",
"a a a aa a a",
"aa a aa a a",
"a aa aa a a",
"a aaaa a a",
"a a a a aa a",
"aa a a aa a",
"a aa a aa a",
"a a aa aa a",
"aa aa aa a",
"aaaa aa a",
"a a aaaa a",
"aa aaaa a",
"a a a a a aa",
"aa a a a aa",
"a aa a a aa",
"a a aa a aa",
"aa aa a aa",
"aaaa a aa",
"a a a aa aa",
"aa a aa aa",
"a aa aa aa",
"a aaaa aa",
"a a a aaaa",
"aa a aaaa",
"a aa aaaa"]
 */
