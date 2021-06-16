package com.sl.coljourney.datastructure.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=117&&tqId=37784&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * 239. 滑动窗口最大值
 * 难度：困难
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * <p>
 * 示例 4：
 * <p>
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * <p>
 * 示例 5：
 * <p>
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *
 * 题解：
 * 1. 大顶堆实现，java 中提供的优先队列可以实现 PriorityQueue
 * 2. 双向单调递减队列实现：
 * >  2.1. 递减队列：队列头为最大元素，依次递减。如果下一个元素大于队尾元素，则删除队尾元素，继续判断下一个队尾元素，直到满足递减为止；如果下一个元素小于队尾元素，则直接追加队尾。
 * >  2.2. 由于滑动窗口有大小，即右边进，左边需要出。
 * >  2.3. 如果队列头的元素滑除了左边的范围，即该元素已经不再当前窗口中了，表示则队列头的元素需要出队。
 * >  2.4. 然后再最近右边进队元素，进队的过程如 2.1 所描述。
 * >  2.5. 当前窗口最大元素就为队头元素。
 */
public class SlidingWindowMaximum {

    /**
     * leetcode
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        // 双向单调递减队列
        LinkedList<Integer> queue = new LinkedList<>();
        // 默认添加第一个元素
        queue.addLast(0);
        for (int i = 1; i < k; i++) {
            // 入队，为了保持单调递减，需要删除所有小于当前入队元素的队列元素
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[i]) {
                queue.removeLast();
            }
            // 入队
            queue.addLast(i);
        }
        // 第一个窗口的最大值
        ans[0] = nums[queue.getFirst()];

        int left = 1;
        for (int j = k; j < nums.length; j++) {
            // 判断队头元素是否满足当前窗口，如果不满足则移除队头元素
            if (queue.getFirst() < left) {
                queue.removeFirst();
            }
            // 入队，为了保持单调递减，需要删除所有小于当前入队元素的队列元素
            while (!queue.isEmpty() && nums[queue.getLast()] <= nums[j]) {
                queue.removeLast();
            }
            // 入队
            queue.addLast(j);

            // 当前窗口的最大值
            ans[left] = nums[queue.getFirst()];
            left++;
        }
        return ans;
    }

    /**
     * 牛客
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (size == 0 || size > num.length) {
            return ans;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(0);
        for (int i = 1; i < size; i++) {
            while (!queue.isEmpty() && num[queue.getLast()] <= num[i]) {
                queue.removeLast();
            }
            queue.addLast(i);
        }
        ans.add(num[queue.getFirst()]);

        int left = 1;
        for (int j = size; j < num.length; j++) {
            if (queue.getFirst() < left) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && num[queue.getLast()] <= num[j]) {
                queue.removeLast();
            }
            queue.addLast(j);

            ans.add(num[queue.getFirst()]);
            left++;
        }
        return ans;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum maximum = new SlidingWindowMaximum();
        // int[] arr = new int[]{1, 3, -1, -3, -4, 3, 6, 7};
        int[] arr = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] ints = maximum.maxSlidingWindow(arr, 3);
        System.out.println(Arrays.toString(ints));
    }

}
/*

1  [3 -1 -3] -4 3  6  7       3  -1
1  3 [-1 -3 -4] 3  6  7       -1  -3

[1  3  -1] -3  5  3  6  7     {1} {3} {3, -1} 3
1 [3  -1  -3] 2  3  6  7      {3, -1, -3}  3
1  3 [-1  -3  2] 3  6  7       2
1  3 -1  [-3  2  3]  6  7      3
1  3 -1  -3  [2  3  6]  7      6


[7 6 5] 4 3 2 1          7
7 [6 5 4] 3 2 1          6

[1 2 3] 4 5 6

 */