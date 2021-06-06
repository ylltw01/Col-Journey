package com.sl.coljourney.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.nowcoder.com/practice/c9addb265cdf4cdd92c092c655d164ca?tpId=117&&tqId=37748&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/generate-parentheses/
 * 22. 括号生成
 * 难度：中等
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * ()() (())
 * <p>
 * 解题：
 * 括号必须由左括号开始，因此首先从左括号开始，即 left == right 时候
 * 第二个括号，有两种情况，一种先加左括号，一种先加右括号
 * 最后判断好边界，left 和 right 都必须大于 0 ，如果 left 不大于 0，说明 left 已经添加完了，剩下的都添加 right。right 也是一样
 * 最终，left == 0 && right == 0，即完成
 */

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generate(ans, "", n, n);
        return ans;
    }

    private void generate(List<String> ans, String parentheses, int left, int right) {
        if (left == 0 && right == 0) {
            ans.add(parentheses);
            return;
        }

        if (left == right) {
            // 如果剩下的左括号和右括号相等，优先添加左括号
            generate(ans, parentheses + "(", left - 1, right);
        } else {
            // 如果剩下的左括号和右括号不相等，那么有两种方案，一种先加左括号，一种先加右括号
            if (left > 0) {
                generate(ans, parentheses + "(", left - 1, right);
            }
            if (right > 0) {
                generate(ans, parentheses + ")", left, right - 1);
            }
        }
    }

    public static void main(String[] args) {
        GenerateParentheses parentheses = new GenerateParentheses();
        List<String> strings = parentheses.generateParenthesis(2);
        for (String s : strings) {
            System.out.println(s);
        }
    }


}
