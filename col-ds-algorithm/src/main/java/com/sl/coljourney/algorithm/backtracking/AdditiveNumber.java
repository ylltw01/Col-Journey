package com.sl.coljourney.algorithm.backtracking;

/**
 * https://leetcode-cn.com/problems/additive-number/
 * 306. 累加数
 * 难度：中等
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 * <p>
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * <p>
 * 示例 2:
 * <p>
 * 输入: "199100199"
 * 输出: true
 * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * <p>
 * 进阶:
 * 你如何处理一个溢出的过大的整数输入?
 */
public class AdditiveNumber {

    public boolean isAdditiveNumber(String num) {

        // 开始的第一个数，从 0 开始直到最后
        for (int i = 0; i < num.length(); i++) {
            String p1 = num.substring(0, i + 1);
            if (!"0".equals(p1) && p1.startsWith("0")) {
                continue;
            }
            // 开始的第二个数，从第一个的下一个开始直到最后
            for (int j = i + 1; j < num.length(); j++) {
                String p2 = num.substring(i + 1, j + 1);
                if (!"0".equals(p2) && p2.startsWith("0")) {
                    continue;
                }

                // 滚动计算满足条件的斐波那契
                long pre = Long.parseLong(p1);
                long cur = Long.parseLong(p2);
                long sum = pre + cur;
                int si = j + 1;
                int ei = si + String.valueOf(sum).length();

                while (ei <= num.length() && String.valueOf(sum).equals(num.substring(si, ei))) {
                    // 如果结束的 index 刚刚好是 num 的长度，且与上一次结果相等，则说明满足条件了
                    if (ei == num.length()) {
                        return true;
                    }
                    pre = cur;
                    cur = sum;
                    sum = pre + cur;
                    si = ei;
                    ei = si + String.valueOf(sum).length();
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        AdditiveNumber additiveNumber = new AdditiveNumber();
//        boolean add = additiveNumber.isAdditiveNumber("112358");
//        boolean add = additiveNumber.isAdditiveNumber("199100199");
//        boolean add = additiveNumber.isAdditiveNumber("10");
//        boolean add = additiveNumber.isAdditiveNumber("1023");
//        boolean add = additiveNumber.isAdditiveNumber("211738");
        boolean add = additiveNumber.isAdditiveNumber("0235813");

        System.out.println(add);
    }

}
/*
111 2 113 115

1 99 100 199

1 + 99 = 100
99 + 100 = 199

211738

    2      21
    1      17
    2

 */
