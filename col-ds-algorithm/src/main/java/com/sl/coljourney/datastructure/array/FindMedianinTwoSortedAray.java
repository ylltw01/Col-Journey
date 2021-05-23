package com.sl.coljourney.datastructure.array;

/**
 * https://www.nowcoder.com/practice/6fbe70f3a51d44fa9395cfc49694404f?tpId=117&tqId=37808&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 题目描述
 * 给定两个有序数组arr1和arr2，已知两个数组的长度都为N，求两个数组中所有数的上中位数。
 * 上中位数：假设递增序列长度为n，若n为奇数，则上中位数为第n/2+1个数；否则为第n/2个数
 * [要求]
 * 时间复杂度为O(logN)O(logN)，额外空间复杂度为O(1)O(1)
 * <p>
 * 示例1
 * 输入
 * [1,2,3,4],[3,4,5,6]
 * 返回值
 * 3
 * 说明
 * 总共有8个数，上中位数是第4小的数，所以返回3。
 * <p>
 * 示例2
 * 输入
 * [0,1,2],[3,4,5]
 * 返回值
 * 2
 * 说明
 * 总共有6个数，那么上中位数是第3小的数，所以返回2
 * <p>
 * 解法：借助二分查找
 * 时间复杂度 O(logn)，就知道要使用二分查找
 * 1. 如果长度为1，则计算两个数组最小值返回。
 * 2  二分查找两个数组，s1、s2 代表开始索引，e1、e2 代表结束索引，开始为 0 和 数组长度 - 1。
 * 3. 计算中间节点 mid = (e + s) / 2
 * 4. 判断两个中间节点，arr1[mid1], arr2[mid2]
 * 5. 如果 arr1[mid1] == arr2[mid2], 则当前数值就是上中位数。
 * 6. 如果 arr1[mid1] < arr2[mid2], 即中位数可能出现到区间在：[mid1, e1] 和 [s2, mid2]
 * >      分两种情况，如果当前 s 到 e 有偶数数个则：s1 = mid1 + 1; e2 = mid2; 否则奇数 s1 = mid1; e2 = mid2;
 * 7. 如果 arr1[mid1] > arr2[mid2], 即中位数可能出现到区间在：[s1, mid1] 和 [mid2, e2]
 * >      分两种情况，如果当前 s 到 e 有偶数个则：e1 = mid1; s2 = mid2 + 1; 否则奇数 e1 = mid1; s2 = mid2;
 * 8. 最终返回 Math.min(arr1[s1], arr2[s2]);
 * <p>
 * 说明：偶数：索引 0 - 3，共4个元素，计算出来 mid 索引 = 1；奇数索引 0 - 4，共5个元素，计算出来 mid 索引 = 2
 * 这样，偶数的 mid 需要加 1，而奇数计算出来就在中间位置，不需要加 1
 */
public class FindMedianinTwoSortedAray {

    public int findMedianinTwoSortedAray(int[] arr1, int[] arr2) {
        // 如果只有 1 个元素，则直接计算返回
        if (arr1.length == 1) {
            return Math.min(arr1[0], arr2[0]);
        }

        // 分别计算两个数组的开始和结束
        int s1 = 0, e1 = arr1.length - 1;
        int s2 = 0, e2 = arr2.length - 1;

        int oddNum;
        while (s1 < e1) {
            // 二分数组
            int mid1 = (e1 + s1) / 2;
            int mid2 = (e2 + s2) / 2;
            // 计算是否为奇数
            oddNum = (e1 - s1 + 1) % 2;

            if (arr1[mid1] == arr2[mid2]) {
                return arr1[mid1];
            } else if (arr1[mid1] < arr2[mid2]) {
                if (oddNum == 1) {
                    s1 = mid1;
                    e2 = mid2;
                } else {
                    s1 = mid1 + 1;
                    e2 = mid2;
                }
            } else {
                if (oddNum == 1) {
                    e1 = mid1;
                    s2 = mid2;
                } else {
                    e1 = mid1;
                    s2 = mid2 + 1;
                }
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }

    public static void main(String[] args) {
        FindMedianinTwoSortedAray medianin = new FindMedianinTwoSortedAray();
        int me = medianin.findMedianinTwoSortedAray(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10});
        System.out.println(me);
    }

    /*
    {1, 3, 5, 7, 9}   {2, 4, 6, 8, 10}


     */
}
