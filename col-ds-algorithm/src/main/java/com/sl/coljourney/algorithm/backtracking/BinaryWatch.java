package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/binary-watch/
 * 401. 二进制手表
 * 难度：中等
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 * 例如，下面的二进制手表读取 "3:25" 。
 * <p>
 * <p>
 * （图源：WikiMedia - Binary clock samui moon.jpg ，许可协议：Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0) ）
 * <p>
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 * <p>
 * 小时不会以零开头：
 * <p>
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * 分钟必须由两位数组成，可能会以零开头：
 * <p>
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：turnedOn = 9
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= turnedOn <= 10
 */
public class BinaryWatch {

    public List<String> readBinaryWatch1(int turnedOn) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i <= 11; i++) {
            for (int j = 0; j <= 59; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    ans.add(i + ":" + (j < 10 ? "0" : "") + j);
                }
            }
        }
        return ans;
    }

    /**
     * 递归实现
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> ans = new ArrayList<>();
        if (turnedOn > 8) {
            return ans;
        }

        int[] hour = {1, 2, 4, 8};
        int[] minute = {1, 2, 4, 8, 16, 32};

        // 大于 5 不能继续从 0 开始
        int hs = 0;
        if (turnedOn > 5) {
            hs = turnedOn - 5;
        }
        for (int i = hs; i < 4; i++) {
            List<Integer> hourTimes = new ArrayList<>();
            times(hour, i, 0, 0, 0, hourTimes, 11);
            int min = Math.min(5, turnedOn - i);
            if (min < 0) {
                break;
            }
            List<Integer> minuteTimes = new ArrayList<>();
            times(minute, min, 0, 0, 0, minuteTimes, 59);
            for (int h : hourTimes) {
                for (int m : minuteTimes) {
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }

    /**
     * 递归图
     * >         1        2      4       8
     * >     2  4  8    4  8     8
     * >
     * >
     * >
     * >         1        2      4       8
     * >     2  4  8      4      8
     * >  4  8  8         8
     */
    private void times(int[] arr, int step, int start, int count, int value, List<Integer> times, int max) {
        if (count >= step) {
            if (value <= max) {
                times.add(value);
            }
            return;
        }
        // 这里的 i < arr.length 不用限制其开始的 index，因为如果 step 大于了会结束循环并退出
        // 限制了开始的 index （例如： i <= arr.length - step） 反而是错误的
        for (int i = start; i < arr.length; i++) {
            times(arr, step, i + 1, count + 1, value + arr[i], times, max);
        }
    }


    public static void main(String[] args) {
        int[] hour = {1, 2, 4, 8};
        BinaryWatch binaryWatch = new BinaryWatch();

        List<Integer> times = new ArrayList<>();
        int step = 3;
        binaryWatch.times(hour, step, 0, 0, 0, times, 11);
        System.out.println(StringUtils.join(times, ", "));


    }
}
/*
        1        2      4       8
    2  4  8    4  8     8



        1        2      4       8
    2  4  8      4      8
 4  8  8         8


1,2,4,8
1+2, 1+4, 1+8 |
1+2+4, 1+4+8 | 2+4+8


    1
时： 1
分： 1
1 = 0 + 1,  1 + 0
2 = 0 + 2， 1 + 1， 2 + 0
3 = 0 + 3， 1 + 2， 2 + 1，3 + 0
4 = 0 + 4， 1 + 3， 2 + 2，3 + 1，4 + 0
5 = 0 + 5， 1 + 4， 2 + 3，3 + 2，4 + 1

1 2 3 4 16 32

1+2，1+3，1+4，1+16，1+32 | 2+3，2+4，2+26，2+32 | 3+4，3+16， 3+32 | 4+16，4+32 | 16+32

1+2+3，1+3+4，1+4+16，1+16+32 | 2+3+4，2+4+16，2+16+32 | 3+4+16，3+16+32 | 4+16+32




*/