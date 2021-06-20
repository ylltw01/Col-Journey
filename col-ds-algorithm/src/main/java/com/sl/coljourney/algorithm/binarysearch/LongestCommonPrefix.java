package com.sl.coljourney.algorithm.binarysearch;

/**
 * https://www.nowcoder.com/practice/28eb3175488f4434a4a6207f6f484f47?tpId=117&&tqId=37752&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/longest-common-prefix/
 * 14. 最长公共前缀
 * 难度：简单
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 */
public class LongestCommonPrefix {

    /**
     * 二分查找
     */
    public String longestCommonPrefix(String[] strs) {
        String common = "";
        if (strs == null || strs.length == 0) {
            return common;
        }
        // 先找出最短的字符串
        int shortStrIdx = 0;
        for (int i = 0; i < strs.length; i++) {
            String s = strs[i];
            if (s.length() < strs[shortStrIdx].length()) {
                shortStrIdx = i;
            }
        }

        // 二分查找
        int begin = 0;
        int end = strs[shortStrIdx].length() - 1;

        while (begin <= end) {
            int mid = (end + begin) / 2;
            String canCommon = strs[shortStrIdx].substring(0, mid + 1);

            boolean match = true;
            for (int i = 0; i < strs.length; i++) {
                if (i == shortStrIdx) {
                    continue;
                }
                if (!strs[i].startsWith(canCommon)) {
                    match = false;
                    break;
                }
            }

            if (match) {
                begin = mid + 1;
                common = canCommon;
            } else{
                end = mid - 1;
            }
        }

        return common;
    }
}
