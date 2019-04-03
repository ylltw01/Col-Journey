package com.sl.coljourney.datastructure.stack;

import java.util.Stack;

/**
 * 84. 柱状图中最大的矩形  难度：困难
 *
 * @author L
 */
public class LargestRectangleInHistogram {

    /**
     * 第一种解题方案：暴力求解, 时间复杂度O(n2)
     * > 分别计算每个高度 i 所能构成的最大的面积
     * >     以 i 为中心，向左边和右边分别查找，知道找到比 i 所表示的高度低的，并累加然后乘以 i 的高度
     * >     只保留最大值
     *
     * @param heights 高度数组
     * @return 在数组中所能构成的最大的矩形面积
     */
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int count = 1;
            for (int l = i - 1; l >= 0; l--) {
                if (heights[l] >= heights[i]) {
                    count++;
                } else {
                    break;
                }
            }
            for (int r = i + 1; r < heights.length; r++) {
                if (heights[r] >= heights[i]) {
                    count++;
                } else {
                    break;
                }
            }
            if (count * heights[i] > maxArea) {
                maxArea = count * heights[i];
            }
        }
        return maxArea;
    }

    /**
     * 第二种解题方案：利用单调递增栈，做到时间复杂度为O(2n)
     * > 单调递增栈：维护一个从小到大的栈，栈顶的元素最大，栈低的元素最小
     * > 求解步骤如下：
     * >     1. 顺序遍历数组至单调递增栈中，如果栈为空或者栈中元素小于等于数组的当前元素则 push 当前数组索引值（i）
     * >     2. 如果栈中元素大于数组的当期元素则需要计算栈顶元素的面积
     * >            2.1. 弹出栈顶元素，即为需要计算的高度在数组中的索引（tpIdx）
     * >            2.2. 当前遍历的索引值（i），为栈顶元素的右边界（因为当期元素的高度要低于栈顶元素的值的高度）
     * >            2.3. 因为栈顶（即 tpIdx 的值大于栈顶的下一个元素的值的高度），因此栈顶元素的左边界为 tpIdx 的下一个元素
     * >            2.4. 因此计算栈顶元素（tpIdx）所覆盖的最大矩形面积的宽度为：i - 1 - stack.peek() [ 该值为tpIdx在栈中的下一个元素 ]
     * >            2.5. 计算面积并计算其最大值，heights[tpIdx] * width
     * >     3. 在循环遍历完成之后，可能栈中还会存在元素，那么按照第 2 步一样计算。但是有一个特殊情况：
     * >            3.1. 就是计算到最后一个元素的时候，在最后一个元素弹栈之后，栈为空。因为维持的是一个单调递增栈，
     * >                 因此该元素即为整个数组中最小的一个元素，也就是说该元素的最大宽度为整个数组的长度（也就是 i 最后的值）
     * >            3.2. 计算面积并计算其最大值，heights[tpIdx] * width
     * 参考代码如下 getMaxArea 方法；
     * 参考链接：https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
     * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/28900/O(n)-stack-based-JAVA-solution
     *
     * @param heights 高度数组
     * @return 在数组中所能构成的最大的矩形面积
     */
    public int largestRectangleArea3(int[] heights) {
        int maxArea = 0, i = 0;
        Stack<Integer> stack = new Stack<>();
        while (i < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                Integer tpIdx = stack.pop();
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(heights[tpIdx] * width, maxArea);
            }
        }
        while (!stack.isEmpty()) {
            Integer tpIdx = stack.pop();
            int width = stack.isEmpty() ? i : i - 1 - stack.peek();
            maxArea = Math.max(heights[tpIdx] * width, maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
//        int[] heights = {2, 1, 5, 6, 2, 3};
//        int[] heights = {2, 1, 2, 1, 2, 1};
        int[] heights = {2, 1, 2};
        LargestRectangleInHistogram lr = new LargestRectangleInHistogram();
        System.out.println(lr.largestRectangleArea(heights));
        System.out.println(lr.largestRectangleArea3(heights));

        System.out.println(getMaxArea(heights, heights.length));

    }

    // The main function to find the maximum rectangular area under given 
    // histogram with n bars 
    private static int getMaxArea(int hist[], int n) {
        // Create an empty stack. The stack holds indexes of hist[] array 
        // The bars stored in stack are always in increasing order of their 
        // heights.
        Stack<Integer> s = new Stack<>();

        int maxArea = 0;
        // To store top of stack 
        int tp;
        // To store area with top bar as the smallest bar 
        int areaWithTop;

        // Run through all bars of given histogram 
        int i = 0;
        while (i < n) {
            // If this bar is higher than the bar on top stack, push it to stack 
            if (s.empty() || hist[s.peek()] <= hist[i]) {
                s.push(i++);
            }

            // If this bar is lower than top of stack, then calculate area of rectangle  
            // with stack top as the smallest (or minimum height) bar. 'i' is  
            // 'right index' for the top and element before top in stack is 'left index' 
            else {
                // store the top index 
                tp = s.peek();
                // pop the top 
                s.pop();

                // Calculate the area with hist[tp] stack as smallest bar 
                areaWithTop = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

                // update max area, if needed 
                if (maxArea < areaWithTop) {
                    maxArea = areaWithTop;
                }
            }
        }

        // Now pop the remaining bars from stack and calculate area with every 
        // popped bar as the smallest bar 
        while (!s.empty()) {
            tp = s.peek();
            s.pop();
            areaWithTop = hist[tp] * (s.empty() ? i : i - s.peek() - 1);

            if (maxArea < areaWithTop) {
                maxArea = areaWithTop;
            }
        }

        return maxArea;

    }

}
