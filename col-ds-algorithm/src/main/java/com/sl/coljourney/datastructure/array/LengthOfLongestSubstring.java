package com.sl.coljourney.datastructure.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
 * https://www.nowcoder.com/practice/b56799ebfd684fb394bd315e89324fb4?tpId=117&tqId=37816&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 题目描述
 * 给定一个数组arr，返回arr的最长无的重复子串的长度(无重复指的是所有数字都不相同)。
 * 示例1
 * 输入
 * [2,3,4,5]
 * 返回值
 * 4
 * 示例2
 * 输入
 * [2,2,3,4,3]
 * 返回值
 * 3
 */
public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        return 0;
    }

    /**
     * [2,2,3,4,3]
     */
    public int maxLength(int[] arr) {
        Map<Integer, Integer> arrIndexMap = new HashMap<>();
        int max = 0;
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arrIndexMap.containsKey(arr[i])) {
                Integer preSame = arrIndexMap.get(arr[i]);
                if (preSame >= start) {
                    max = Math.max(i - start, max);
                    start = preSame + 1;
                }
            } else if (arr.length - 1 == i) {
                max = Math.max(arr.length - start, max);
            }
            arrIndexMap.put(arr[i], i);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 3, 4, 5, 3, 4, 5};
        LengthOfLongestSubstring solution = new LengthOfLongestSubstring();
        int maxLength = solution.maxLength(arr);
        System.out.println(maxLength);
    }

}
