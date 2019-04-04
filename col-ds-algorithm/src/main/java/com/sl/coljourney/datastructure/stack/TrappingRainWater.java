package com.sl.coljourney.datastructure.stack;

import java.util.Stack;

/**
 * 42. 接雨水  难度：困难
 *
 * @author L
 */
public class TrappingRainWater {

    /**
     * 第一种解题方案：
     * >  先求出数组中最高的点；
     * >  然后依次从两边向中间开始遍历，并计算累加之间的差值
     *
     * @param height 高度数组
     * @return 返回能装最大量水
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // 找出最高的点
        int maxIdx = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > height[maxIdx]) {
                maxIdx = i;
            }
        }
        int trap = 0, root = height[0];
        // 遍历左边
        for (int i = 1; i < maxIdx; i++) {
            if (height[i] > root) {
                root = height[i];
            } else {
                trap += root - height[i];
            }
        }
        // 遍历右边
        root = height[height.length - 1];
        for (int i = height.length - 1; i > maxIdx; i--) {
            if (height[i] > root) {
                root = height[i];
            } else {
                trap += root - height[i];
            }
        }
        return trap;
    }

    /**
     * 第一种解题方案：基于单调递减栈
     * >  单调递减栈：维护一个从大到小的栈，栈顶的元素最小，栈低的元素最大
     * >  主要求解思路：
     * >      1. 遍历数组，存入递减栈
     * >      2. 找出每个能存水的区间，在递减栈中栈底元素最大，当要存入的栈的元素大于了栈顶元素时，需要计算存水量并调整栈
     * >             2.1. 如果待存入的元素大于栈顶并且大于栈底元素，那么栈底到该元素正好形成一个区间，用栈底元素分别减去栈
     * >                  中元素并累加
     * >             2.2. 如果待存入的元素大于栈顶但小于栈底元素，那么则使用待存入的元素和栈中元素判断，直到栈中元素大于等于
     * >                  该值
     * >      3. 如果栈为空，则说明是2.1的情况，则存入遍历的当前数组元素。否则，则说明是2.2的情况，那么需要补齐弹栈的元素
     * <p>
     * 举例如下：{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
     * |栈底元素|    原栈中元素   |    入栈元素    |     入栈后元素    |  累加值  |    说明
     * |   0    |        0        |    1           |      1            |  0       |     0 弹栈, 1入栈
     * |   1    |        1        |    0           |      1,0          |  0       |     0 入栈
     * |   1    |        1,0      |    2           |      2            |  1       |     2 入栈，1,0,2 正好形成一个区间，1 - 0 = 1
     * |   2    |        2        |    1           |      2,1          |  1       |     1 入栈
     * |   2    |        2,1      |    0           |      2,1,0        |  1       |     0 入栈
     * |   2    |        2,1,0    |    1           |      2,1,1,1      |  2       |     1 入栈，大于栈顶0，计算 1,0,1 区间 1 - 0 =1，并补齐 1
     * |   2    |        2,1,1,1  |    3           |      3            |  5       |     3 入栈，2,1,1,1,3 正好形成一个区间，计算并累加
     * |   3    |        3        |    2           |      3,2          |  5       |     2 入栈，
     * |   3    |        3,2      |    1           |      3,2,1        |  5       |     1 入栈，
     * |   3    |        3,2,1    |    2           |      3,2,2,2      |  6       |     2 入栈，大于栈顶1，计算 2,1,2 区间 2 - 1 = 1，并补齐 2
     * |   3    |        3,2,2,2  |    1           |      3,2,2,2,1    |  6       |     1 入栈，后续无法形成区间，计算完毕
     *
     * @param height 高度数组
     * @return 返回能装最大量水
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int trap = 0, bottom = height[0];
        Stack<Integer> stack = new Stack<>();
        for (int hei : height) {
            if (stack.isEmpty() || hei < stack.peek()) {
                stack.push(hei);
            } else {
                int count = 0;
                while (!stack.isEmpty() && hei > stack.peek()) {
                    if (bottom <= hei) {
                        trap += bottom - stack.pop();
                    } else {
                        trap += hei - stack.pop();
                        count++;
                    }
                }
                if (stack.isEmpty()) {
                    stack.push(hei);
                    bottom = hei;
                } else {
                    for (int j = 0; j < count + 1; j++) {
                        stack.push(hei);
                    }
                }
            }
        }
        return trap;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int trap = trappingRainWater.trap(height);
        System.out.println(trap);

        int trap2 = trappingRainWater.trap2(height);
        System.out.println(trap2);
    }

}
