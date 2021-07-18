package com.sl.coljourney.algorithm.sword;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/single-number-iii/
 * 260. 只出现一次的数字 III
 * 难度：中等
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * <p>
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 * <p>
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 */
public class SingleNumber3 {

    /**
     * 作者：w1sl1y
     * 链接：https://leetcode-cn.com/problems/single-number-iii/solution/cai-yong-fen-zhi-de-si-xiang-jiang-wen-ti-jiang-we/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] singleNumber(int[] nums) {
        // 第一步：
        // 把所有的元素进行异或操作，最终得到一个异或值。因为是不同的两个数字，所以这个值必定不为 0
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // 第二步：
        // 取异或值最后一个二进制位为 1 的数字作为 mask，如果是 1 则表示两个数字在这一位上不同。
        int mask = xor & (-xor);

        // 第三步：
        //通过与这个 mask 进行与操作，如果为 0 的分为一个数组，为 1 的分为另一个数组。这样就把问题降低成了：“有一个数组每个数字都出现两次，有一个数字只出现了一次，求出该数字”。对这两个子问题分别进行全异或就可以得到两个解。也就是最终的数组了。
        int[] ans = new int[2];
        for (int num : nums) {
            if ((num & mask) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }

    public int[] singleNumber2(int[] nums) {
        Set<Integer> s1 = new HashSet<>();
        for (Integer i : nums) {
            if (s1.contains(i)) {
                s1.remove(i);
            } else {
                s1.add(i);
            }
        }

        int[] res = new int[2];
        int i = 0;
        for (Integer s : s1) {
            res[i++] = s;
        }
        return res;
    }

    public static void main(String[] args) {

        int xor = 3;
        System.out.println(Integer.toBinaryString(xor));
        System.out.println(Integer.toBinaryString(-xor));
        System.out.println(Integer.toBinaryString(-3));
        int mask = xor & (-xor);
        System.out.println(Integer.toBinaryString(mask));

    }

}
