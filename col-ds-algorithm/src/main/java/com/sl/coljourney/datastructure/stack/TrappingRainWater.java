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

    /**
     * 双指针法真的妙，但理解起来不容易
     * <p>
     * 先明确几个变量的意思：
     * left_max：左边的最大值，它是从左往右遍历找到的
     * right_max：右边的最大值，它是从右往左遍历找到的
     * left：从左往右处理的当前下标
     * right：从右往左处理的当前下标
     * <p>
     * 定理一：在某个位置i处，它能存的水，取决于它左右两边的最大值中较小的一个。
     * 定理二：当我们从左往右处理到left下标时，左边的最大值left_max对它而言是可信的，但right_max对它而言是不可信的。（见下图，由于中间状况未知，对于left下标而言，right_max未必就是它右边最大的值）
     * 定理三：当我们从右往左处理到right下标时，右边的最大值right_max对它而言是可信的，但left_max对它而言是不可信的。
     * <p>
     * >                                     right_max
     * >  left_max                             __
     * >    __                                |  |
     * >   |  |__   __ ?????????????????????? |  |
     * > __|     |__|                       __|  |__
     * >         left                      right
     * <p>
     * 对于位置left而言，它左边最大值一定是left_max，右边最大值“大于等于”right_max，这时候，
     * 如果 left_max < right_max 成立，那么它就知道自己能存多少水了。无论右边将来会不会出现更大的right_max，都不影响这个结果。
     * 所以当 left_max < right_max 时，我们就希望去处理left下标，反之，我们希望去处理right下标。
     */
    public int trap3(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int trap = 0;

        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                trap += leftMax - height[left];
                left++;
            } else {
                trap += rightMax - height[right];
                right--;
            }
        }
        return trap;
    }

    /**
     * 牛客的题目
     * 双指针
     */
    public long maxWater(int[] arr) {
        if (arr.length <= 2) {
            return 0;
        }

        int left = 0;
        int right = arr.length - 1;
        long leftMax = arr[0];
        long rightMax = arr[arr.length - 1];

        long trap = 0;
        while (left < right) {
            if (arr[left] < arr[right]) {
                left++;
                if (arr[left] < leftMax) {
                    trap += leftMax - arr[left];
                } else {
                    leftMax = arr[left];
                }
            } else {
                right--;
                if (arr[right] < rightMax) {
                    trap += rightMax - arr[right];
                } else {
                    rightMax = arr[right];
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

        int trap3 = trappingRainWater.trap3(height);
        System.out.println(trap3);
    }

}
