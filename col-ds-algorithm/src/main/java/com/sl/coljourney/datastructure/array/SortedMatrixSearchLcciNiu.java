package com.sl.coljourney.datastructure.array;

/**
 * https://www.nowcoder.com/practice/3afe6fabdb2c46ed98f06cfd9a20f2ce?tpId=117&&tqId=37788&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * 题目: 矩阵元素查找
 * 中等  通过率：42.14%  时间限制：3秒  空间限制：64M
 * 知识点 二分 分治
 * <p>
 * 描述
 * 已知int一个有序矩阵mat，同时给定矩阵的大小n和m以及需要查找的元素x，且矩阵的行和列都是从小到大有序的。设计查找算法返回所查找元素的二元数组，代表该元素的行号和列号(均从零开始)。保证元素互异。
 * <p>
 * 示例1
 * 输入：
 * [[1,2,3],[4,5,6]],2,3,6
 * 返回值：
 * [1,2]
 */
public class SortedMatrixSearchLcciNiu {

    public int[] findElement(int[][] mat, int n, int m, int x) {
        int[] ans = new int[2];
        if (mat == null || mat.length == 0) {
            return ans;
        }
        int row = 0;
        int col = mat[row].length - 1;
        while (row < mat.length && col >= 0) {
            if (mat[row][col] == x) {
                ans[0] = row;
                ans[1] = col;
                return ans;
            } else if (mat[row][col] > x) {
                col--;
            } else {
                row++;
            }
        }
        return ans;
    }

}
