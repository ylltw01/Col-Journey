package com.sl.coljourney.datastructure.stack;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/c215ba61c8b1443b996351df929dc4d4?tpId=117&tqId=37849&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 表达式求值
 * <p>
 * 题目描述
 * 请写一个整数计算器，支持加减乘三种运算和括号。
 * <p>
 * 示例1
 * 输入
 * "1+2"
 * 返回值
 * 3
 * <p>
 * 示例2
 * 输入
 * "(2*(3-4))*5"
 * 返回值
 * -10
 * <p>
 * 示例3
 * 输入
 * "3+2*3*4-1"
 * 返回值
 * 26
 * <p>
 * 题解：
 * 运用栈和递归
 * + 号前后的字符都存进栈中
 * - 号后的字符，存储为负数，这样在最后计算的时候直接相加即可
 * * 号属于优先级最高的，需要即时运算
 * () 号的使用递归进行计算
 */
public class ExpressSolveNiu {

    public int solve(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            int position = i;
            char c = s.charAt(i);
            String product;
            if (c == '(') {
                // 如果当前是 ( 号开始，则需要递归计算 () 中的值
                int countPar = 0;
                int parEnd = i + 1;
                while (parEnd < s.length()) {
                    char sc = s.charAt(parEnd);
                    // 处理可能存在 () 嵌套的情况
                    if (sc == '(') {
                        countPar++;
                    } else if (sc == ')') {
                        if (countPar == 0) {
                            break;
                        } else {
                            countPar--;
                        }
                    }
                    parEnd++;
                }
                product = String.valueOf(solve(s.substring(i + 1, parEnd)));
                i = parEnd;
            } else if (Character.isDigit(c)) {
                // 对于多位数字处理，直接找出该数值
                int digitEnd = i + 1;
                StringBuilder ps = new StringBuilder(String.valueOf(c));
                while (digitEnd < s.length()) {
                    if (Character.isDigit(s.charAt(digitEnd))) {
                        ps.append(s.charAt(digitEnd));
                        digitEnd++;
                    } else {
                        break;
                    }
                }
                i = digitEnd - 1;
                product = ps.toString();
            } else {
                continue;
            }

            if (position - 1 >= 0) {
                char symbol = s.charAt(position - 1);
                if (symbol == '*') {
                    // 优先计算乘法
                    product = String.valueOf(Integer.parseInt(stack.pop()) * Integer.parseInt(product));
                } else if (symbol == '-') {
                    // 减号处理，如果之前计算结果位负数，减去负数等于加上该正数
                    if (product.contains("-")) {
                        product = product.replace("-", "");
                    } else {
                        product = "-" + product;
                    }
                }
            }
            stack.push(product);
        }
        // 最后直接相加
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += Integer.parseInt(stack.pop());
        }
        return ans;
    }

    public static void main(String[] args) {
        ExpressSolveNiu expressSolveNiu = new ExpressSolveNiu();
//        int solve = expressSolveNiu.solve("(2*(3-4))*5");
//        int solve = expressSolveNiu.solve("100+100");
        int solve = expressSolveNiu.solve("((10+2)*10-(100-(10+20*10-(2*3)))*10*1*2)-2");
//        int solve = expressSolveNiu.solve("3+2*3*4-1");
        System.out.println(solve);
    }
}
