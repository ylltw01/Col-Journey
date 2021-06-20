package com.sl.coljourney.datastructure.stack;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/45fd68024a4c4e97a8d6c45fc61dc6ad?tpId=117&&tqId=37745&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * 32. 最长有效括号
 * 难度：困难
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = ""
 * 输出：0
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "(()())"
 * 输出：6
 * <p>
 * "()(()" = 2
 * "()(())" = 6
 */
public class LongestValidParentheses {

    /**
     * 基于栈的解决方案
     */
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        // 初始化的时候先仍一个 -1 进去，防止这种 ()()，这种本身就是连续的计算
        // 如：()() 这种，最终栈中只会剩下 -1，3 - -1 = 4
        stack.push(-1);
        for (int i = 0; i < s.toCharArray().length; i++) {
            // 如果是 ( ，则直接将下标仍进去
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 如果是 )，则先弹栈
                stack.pop();
                // 注意了，如果弹栈了，发现栈空了，表示当前 i 对应的 ) 没有对应的 ( ，则说明需要开启一个新的区间
                // 如：())()
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 如果栈没有为空，说明要么前面还有一个未算完的，或者有算完了，此时栈中要么是 -1 要么就是为匹配的 ) 对应的下标
                    // 如：(() 这种
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestValidParentheses parentheses = new LongestValidParentheses();
        String s = "(()";
        s = "(()())";
        s = ")()())";
        s = "()(()";
        int i = parentheses.longestValidParentheses(s);
        System.out.println(i);
    }
}
