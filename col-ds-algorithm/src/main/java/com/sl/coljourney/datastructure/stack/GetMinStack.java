package com.sl.coljourney.datastructure.stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/c623426af02d4c189f92f2a99647bd34?tpId=117&tqId=37793&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 题目描述
 * 实现一个特殊功能的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 * 示例1
 * 输入
 * 复制
 * [[1,3],[1,2],[1,1],[3],[2],[3]]
 * 返回值
 * 复制
 * [1,2]
 * 备注:
 * 有三种操作种类，op1表示push，op2表示pop，op3表示getMin。你需要返回和op3出现次数一样多的数组，表示每次getMin的答案
 * <p>
 * 1<=操作总数<=1000000
 * -1000000<=每个操作数<=1000000
 * 数据保证没有不合法的操作
 * <p>
 * <p>
 * https://leetcode-cn.com/problems/min-stack/
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class GetMinStack {

    /**
     * 牛客的题
     * [[1,3],[1,2],[1,1],[3],[2],[3]]
     * [1, 2]
     */
    public int[] getMinStack(int[][] op) {
        int[] ans = new int[op.length];
        int length = 0;
        MinStack minStack = new MinStack();
        for (int[] arr : op) {
            switch (arr[0]) {
                case 1:
                    minStack.push(arr[1]);
                    break;
                case 2:
                    minStack.pop();
                    break;
                case 3:
                    ans[length] = minStack.getMin();
                    length++;
                    break;
                default:
                    break;
            }
        }
        int[] ansSub = new int[length];
        if (length > 0) {
            System.arraycopy(ans, 0, ansSub, 0, length);
        }
        return ansSub;
    }


    /**
     * leetcode 的题解，在栈中每个元素中存储一个对应的最小值即：[栈元素，该栈元素对应栈中最小值]
     */
    static class MinStack {

        Stack<Element> stack = new Stack<>();

        public MinStack() {
        }

        /**
         * push(x) —— 将元素 x 推入栈中。
         */
        public void push(int val) {
            int min = Integer.MAX_VALUE;
            if (!stack.isEmpty()) {
                min = stack.peek().minVal;
            }
            min = Math.min(min, val);
            Element e = new Element(val, min);
            stack.push(e);
        }

        /**
         * pop()—— 删除栈顶的元素。
         */
        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }

        /**
         * top()—— 获取栈顶元素。
         */
        public int top() {
            if (!stack.isEmpty()) {
                Element element = stack.peek();
                return element.val;
            } else {
                return -1;
            }
        }

        /**
         * getMin() —— 检索栈中的最小元素。
         */
        public int getMin() {
            if (!stack.isEmpty()) {
                Element element = stack.peek();
                return element.minVal;
            } else {
                return -1;
            }
        }
    }

    static class Element {
        int val;
        int minVal;

        public Element(int val, int minVal) {
            this.val = val;
            this.minVal = minVal;
        }
    }

    /**
     * 官方解法：借助辅助栈，辅助栈存储，当前栈对应的最小值
     */
    static class MinStack2 {
        Deque<Integer> xStack;
        Deque<Integer> minStack;

        public MinStack2() {
            xStack = new LinkedList<Integer>();
            minStack = new LinkedList<Integer>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int x) {
            xStack.push(x);
            minStack.push(Math.min(minStack.peek(), x));
        }

        public void pop() {
            xStack.pop();
            minStack.pop();
        }

        public int top() {
            return xStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-1);

        int min = minStack.getMin();
        System.out.println(min);
    }

}
