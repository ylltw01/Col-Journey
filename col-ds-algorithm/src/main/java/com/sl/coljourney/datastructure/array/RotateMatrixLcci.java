package com.sl.coljourney.datastructure.array;

/**
 * https://www.nowcoder.com/practice/2e95333fbdd4451395066957e24909cc?tpId=117&&tqId=37790&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/rotate-image/
 * https://leetcode-cn.com/problems/rotate-matrix-lcci/
 * 面试题 01.07. 旋转矩阵
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * <p>
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * ==================
 * 0,0  0,1  0,2  0,3
 * 1,0            1,3
 * 2,0            2,3
 * 3,0  3,1  3,2  3,3
 * ==================
 * 1,1    1,2
 * <p>
 * <p>
 * 2,1    2,2
 * ==================
 * 2,2    2,1
 * <p>
 * 1,2    1,1
 * ==================
 */
public class RotateMatrixLcci {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 层级
        int level = 1;
        // 最大长度 4 - 3 = 1
        int ml = n - level;
        // 开始的 index 3 - 1 = 2
        int sti = 0;
        while (sti < ml) {
            // 注意这里的 ml - sti，内圈的移动次数，就是最大 index 减去开始 index
            for (int i = 0; i < ml - sti; i++) {
                int temp = matrix[sti][sti];
                for (int top = sti + 1; top <= ml; top++) {
                    int cur = matrix[sti][top];
                    matrix[sti][top] = temp;
                    temp = cur;
                }

                for (int right = sti + 1; right < ml; right++) {
                    int cur = matrix[right][ml];
                    matrix[right][ml] = temp;
                    temp = cur;
                }
                // 2,2   2,1   2,0
                for (int bottom = ml; bottom >= sti + 1; bottom--) {
                    int cur = matrix[ml][bottom];
                    matrix[ml][bottom] = temp;
                    temp = cur;
                }

                for (int left = ml; left >= sti; left--) {
                    int cur = matrix[left][sti];
                    matrix[left][sti] = temp;
                    temp = cur;
                }
            }
            level++;
            ml = n - level;
            sti = level - 1;
        }
    }

    /**
     * 牛客的题目，参数不一样
     */
    public int[][] rotateMatrixNiu(int[][] mat, int n) {
        // 层级
        int level = 1;
        // 最大长度 4 - 3 = 1
        int ml = n - level;
        // 开始的 index 3 - 1 = 2
        int sti = 0;
        while (sti < ml) {
            // 注意这里的 ml - sti，内圈的移动次数，就是最大 index 减去开始 index
            for (int i = 0; i < ml - sti; i++) {
                int temp = mat[sti][sti];
                for (int top = sti + 1; top <= ml; top++) {
                    int cur = mat[sti][top];
                    mat[sti][top] = temp;
                    temp = cur;
                }

                for (int right = sti + 1; right < ml; right++) {
                    int cur = mat[right][ml];
                    mat[right][ml] = temp;
                    temp = cur;
                }

                for (int bottom = ml; bottom >= sti + 1; bottom--) {
                    int cur = mat[ml][bottom];
                    mat[ml][bottom] = temp;
                    temp = cur;
                }

                for (int left = ml; left >= sti; left--) {
                    int cur = mat[left][sti];
                    mat[left][sti] = temp;
                    temp = cur;
                }
            }
            level++;
            ml = n - level;
            sti = level - 1;
        }
        return mat;
    }

}
