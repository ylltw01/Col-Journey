package com.sl.coljourney.datastructure.stack;

/**
 * 42. 接雨水  难度：困难
 *
 * @author L
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        if (height.length < 2) {
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

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        int trap = trappingRainWater.trap(height);
        System.out.println(trap);
    }

}
