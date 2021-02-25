package com.sl.coljourney.algorithm.dynamicprogramming;

/**
 * https://www.nowcoder.com/practice/8c82a5b80378478f9484d87d1c5f12a4?tpId=117&tqId=37764&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 跳台阶
 * <p>
 * 题目描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * <p>
 * 示例1
 * 输入
 * 1
 * 返回值
 * 1
 * <p>
 * 示例2
 * 输入
 * 4
 * 返回值
 * 5
 *
 * 递推公式：
 * f(n) = f(n-1) + f(n-2)
 */
public class JumpFloor {

    public int jumpFloor(int target) {
        return jump(target);
    }

    private int jump(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return jump(target - 1) + jump(target - 2);
    }

}

