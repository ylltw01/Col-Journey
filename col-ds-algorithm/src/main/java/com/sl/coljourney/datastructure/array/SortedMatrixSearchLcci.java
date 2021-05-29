package com.sl.coljourney.datastructure.array;

/**
 * 三道题，一样的（74 题略微差别）
 * https://leetcode-cn.com/problems/sorted-matrix-search-lcci
 * https://leetcode-cn.com/problems/search-a-2d-matrix-ii/submissions/
 * https://leetcode-cn.com/problems/search-a-2d-matrix/submissions/
 * 74. 搜索二维矩阵
 * 240. 搜索二维矩阵 II
 * 面试题 10.09. 排序矩阵查找
 * 难度：中等
 * 给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
 * <p>
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 */
public class SortedMatrixSearchLcci {

    /**
     * 从右上角开始查找
     * 如果右上角的比 target 小，说明当前行都比 target 小，则往下找
     * 如果右上角的比 target 大，说明 target 可能存在当前行，则往左找
     */
    public boolean searchMatrixRight(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int row = 0;
        int col = matrix[row].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * 二分查找，效率不如上面
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int begin = 0;
        int end = matrix.length - 1;

        while (begin < end) {
            int mid = (end + begin) / 2;
            if (matrix[mid][0] == target) {
                return true;
            } else if (matrix[mid][0] < target) {
                begin = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        for (int i = 0; i <= end; i++) {
            int mb = 0;
            int me = matrix[i].length - 1;
            if (me >= 0 && matrix[i][me] >= target) {
                while (mb <= me) {
                    int mmid = (me + mb) / 2;
                    if (matrix[i][mmid] == target) {
                        return true;
                    } else if (matrix[i][mmid] < target) {
                        mb = mmid + 1;
                    } else {
                        me = mmid - 1;
                    }
                }
            }
        }
        return false;
    }

}
