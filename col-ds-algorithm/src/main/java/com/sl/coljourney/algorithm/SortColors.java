package com.sl.coljourney.algorithm;

/**
 * https://leetcode-cn.com/problems/sort-colors/
 * 75. 颜色分类
 * 难度：中等
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * <p>
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * 进阶：
 * <p>
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int end = nums.length;
        int l1 = -1;
        for (int i = 0; i < end; i++) {
            // 如果等于 2 ，则放到最后一个不为 2 的地方
            if (nums[i] == 2) {
                int ei = end;
                while (ei > i) {
                    if (nums[ei - 1] == 2) {
                        ei--;
                    } else {
                        int temp = nums[i];
                        nums[i] = nums[ei - 1];
                        nums[ei - 1] = temp;
                        // 如果更新后的 nums[i] == 1，且 1 的靠左的下标为 -1，则更新
                        if (nums[i] == 1 && l1 == -1) {
                            l1 = i;
                        } else if (nums[i] == 0) {
                            // 如果更新后的 nums[i] == 0，则重新算 nums[i] == 0 的位置
                            i--;
                        }
                        break;
                    }
                }
                end = ei - 1;
            } else if (nums[i] == 0 && l1 != -1) {
                // 如果为 0 ，则和 1 交换
                int temp = nums[i];
                nums[i] = nums[l1];
                nums[l1] = temp;
                l1++;
            } else if (nums[i] == 1 && l1 == -1) {
                // 为 1 更新 1 的靠左的下标为 i
                l1 = i;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 2};
        SortColors sortColors = new SortColors();
        sortColors.sortColors(arr);

        System.out.println(arr);
    }

}

/*

2,0,2,1,1,0

2,0,2,1,0,1

2,1,1,0,2,0

1,1,0,2,2,0

1,1,2,2,0,0

1,0,0,2,2,1


 */