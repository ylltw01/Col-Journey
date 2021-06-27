package com.sl.coljourney.datastructure.stack;

import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/54275ddae22f475981afa2244dd448c6?tpId=117&&tqId=37774&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * <p>
 * 示例 2：
 * <p>
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 */
public class TwoStacksImplementQueues {

    private Stack<Integer> stackIn;

    private Stack<Integer> stackDelete;

    // public CQueue() {
    public TwoStacksImplementQueues() {
        stackIn = new Stack<>();
        stackDelete = new Stack<>();
    }

    public void appendTail(int value) {
        stackIn.push(value);
    }

    public int deleteHead() {
        // 先把 stackDelete 中所有的元素弹出去，stackDelete 都是在队列中的前面的元素
        if (stackDelete.isEmpty()) {
            while (!stackIn.isEmpty()) {
                stackDelete.push(stackIn.pop());
            }
        }
        if (stackDelete.isEmpty()) {
            return -1;
        } else {
            return stackDelete.pop();
        }
    }

    public static void main(String[] args) {
//["CQueue","deleteHead","appendTail","deleteHead","appendTail","appendTail","deleteHead","deleteHead","deleteHead",
// "appendTail","deleteHead","appendTail","appendTail","appendTail","appendTail","appendTail","appendTail","deleteHead","deleteHead","deleteHead","deleteHead"]
//[[],[],[12],[],[10],[9],[],[],[],[20],[],[1],[8],[20],[1],[11],[2],[],[],[],[]]
        TwoStacksImplementQueues queues = new TwoStacksImplementQueues();
        System.out.println(queues.deleteHead());
        queues.appendTail(12);
        System.out.println(queues.deleteHead());
        queues.appendTail(10);
        queues.appendTail(9);
        System.out.println(queues.deleteHead());
        System.out.println(queues.deleteHead());
        System.out.println(queues.deleteHead());
        queues.appendTail(20);
        System.out.println(queues.deleteHead());
        queues.appendTail(1);
        queues.appendTail(8);
        queues.appendTail(1);
        queues.appendTail(11);
        queues.appendTail(2);

        System.out.println(queues.deleteHead());
        System.out.println(queues.deleteHead());
        System.out.println(queues.deleteHead());
        System.out.println(queues.deleteHead());


    }

}
