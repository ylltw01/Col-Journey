package com.sl.coljourney.datastructure.array;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/two-sum/submissions/
 * https://www.nowcoder.com/practice/20ef0972485e41019e39543e8e895b7f?tpId=117&tqId=37756&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 两数之和
 * <p>
 * 题目描述
 * 给出一个整数数组，请在数组中找出两个加起来等于目标值的数，
 * 你给出的函数twoSum 需要返回这两个数字的下标（index1，index2），需要满足 index1 小于index2.。注意：下标是从1开始的
 * 假设给出的数组中只存在唯一解
 * <p>
 * 例如：
 * 给出的数组为 {20, 70, 110, 150},目标值为90
 * 输出 index1 = 1, index2 = 2
 * <p>
 * 示例1
 * 输入
 * [3,2,4],6
 * 返回值
 * [2,3]
 */
public class TwoSumArray {

    /**
     * 牛客，结果索引是从1开始
     */
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[]{-1, -1};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        for (int i = 0; i < numbers.length; i++) {
            int two = target - numbers[i];
            if (map.containsKey(two) && i != map.get(two)) {
                res[0] = Math.min(i + 1, map.get(two) + 1);
                res[1] = Math.max(i + 1, map.get(two) + 1);
            }
        }
        return res;
    }

    /**
     * lettcode，结果索引是从0开始
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] res = new int[]{-1, -1};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        for (int i = 0; i < numbers.length; i++) {
            int two = target - numbers[i];
            if (map.containsKey(two) && i != map.get(two)) {
                res[0] = Math.min(i, map.get(two));
                res[1] = Math.max(i, map.get(two));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0, 4, 3, 0};
        TwoSumArray solution = new TwoSumArray();
        int[] ints = solution.twoSum(arr, 0);

        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}
