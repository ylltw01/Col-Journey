package com.sl.coljourney.algorithm.backtracking;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/matchsticks-to-square/
 * 473. 火柴拼正方形
 * 难度：中等
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * <p>
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,2,2,2]
 * 输出: true
 * <p>
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 * <p>
 * 输入: [3,3,3,3,4]
 * 输出: false
 * <p>
 * 解释: 不能用所有火柴拼成一个正方形。
 * 注意:
 * <p>
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 */
public class MatchsticksToSquare {

//
//    作者：sdwwld
//    链接：https://leetcode-cn.com/problems/matchsticks-to-square/solution/hui-su-suan-fa-jie-jue-ji-you-hua-chao-g-9iyl/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public boolean makesquare(int[] nums) {
        int total = 0;
        // 统计所有火柴的长度
        for (int num : nums) {
            total += num;
        }
        // 如果所有火柴的长度不是4的倍数，直接返回false
        if (total == 0 || (total & 3) != 0) {
            return false;
        }
        // 先排序
        Arrays.sort(nums);
        // 回溯，从最长的火柴开始
        return backtrack(nums, nums.length - 1, total >> 2, new int[4]);
    }

    /**
     * index表示访问到当前火柴的位置，target表示正方形的边长，size是长度为4的数组，
     * 分别保存正方形4个边的长度
     */
    private boolean backtrack(int[] nums, int index, int target, int[] size) {
        if (index == -1) {
            // 如果火柴都访问完了，并且size的4个边的长度都相等，说明是正方形，直接返回true，
            // 否则返回false
            return size[0] == size[1] && size[1] == size[2] && size[2] == size[3];
        }
        // 到这一步说明火柴还没访问完
        for (int i = 0; i < size.length; i++) {
            // 如果把当前火柴放到size[i]这个边上，他的长度大于target，我们直接跳过。或者
            // size[i] == size[i - 1]即上一个分支的值和当前分支的一样，上一个分支没有成功，
            // 说明这个分支也不会成功，直接跳过即可。
            if (size[i] + nums[index] > target || (i > 0 && size[i] == size[i - 1]) || (index == nums.length - 1 && i != 0)) {
                continue;
            }
            // 如果当前火柴放到size[i]这个边上，长度不大于target，我们就放上面
            size[i] += nums[index];
            // 然后在放下一个火柴，如果最终能变成正方形，直接返回true
            if (backtrack(nums, index - 1, target, size)) {
                return true;
            }
            // 如果当前火柴放到size[i]这个边上，最终不能构成正方形，我们就把他从
            // size[i]这个边上给移除，然后在试其他的边
            size[i] -= nums[index];
        }
        // 如果不能构成正方形，直接返回false
        return false;
    }

    public static void main(String[] args) {
//        int[] arr = {1, 1, 2, 2, 2};
        int[] arr = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};

        MatchsticksToSquare matchsticksToSquare = new MatchsticksToSquare();
        boolean makesquare = matchsticksToSquare.makesquare(arr);
        System.out.println(makesquare);
    }
}
/*
1, 2, 1, 2

1, 1, 1, 1, 3, 3, 3, 3

3，3，3，3，1，1，1，1
0, 1, 2, 3, 4, 5, 6, 7
            0, 1, 2, 3

            [5,5,5,5,4,4,4,4,3,3,3,3]
            3,3,3,3  4,4,4,4  5,5,5,5
 */
