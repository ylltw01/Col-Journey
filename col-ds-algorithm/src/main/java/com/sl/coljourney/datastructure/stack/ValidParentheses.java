package com.sl.coljourney.datastructure.stack;

import java.util.Stack;

/**
 * 20. 有效的括号  难度：简单
 * https://leetcode-cn.com/problems/valid-parentheses/submissions/
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 *
 * @author L
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char ch : chars) {
            if ('(' == ch || '[' == ch || '{' == ch) {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                switch (ch) {
                    case ')':
                        if ('(' != stack.pop()) {
                            return false;
                        }
                        break;
                    case ']':
                        if ('[' != stack.pop()) {
                            return false;
                        }
                        break;
                    case '}':
                        if ('{' != stack.pop()) {
                            return false;
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
