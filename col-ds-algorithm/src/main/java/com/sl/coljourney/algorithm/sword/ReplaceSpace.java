package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 * 剑指 Offer 05. 替换空格
 * 难度：简单
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 */
public class ReplaceSpace {

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
