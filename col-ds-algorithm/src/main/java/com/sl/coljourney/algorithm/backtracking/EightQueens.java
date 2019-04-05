package com.sl.coljourney.algorithm.backtracking;

/**
 * @author L
 */
public class EightQueens {

    /**
     * 全局或成员变量, 下标表示行, 值表示 queen 存储在哪一列
     */
    private int[] result = new int[8];

    public void cal8queens(int row) {
        // 8 个棋子都放置好了，打印结果
        if (row == 8) {
            printQueens(result);
            // 8 行棋子都放好了，已经没法再往下递归了，所以就 return
            return;
        }
        // 每一行都有 8 中放法
        for (int column = 0; column < 8; ++column) {
            // 有些放法不满足要求
            if (isOk(row, column)) {
                // 第 row 行的棋子放到了 column 列
                result[row] = column;
                // 考察下一行
                cal8queens(row + 1);
            }
        }
    }

    private boolean isOk(int row, int column) {// 判断 row 行 column 列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        // 逐行往上考察每一行
        for (int i = row - 1; i >= 0; --i) {
            if (result[i] == column) {
                // 第 i 行的 column 列有棋子吗？
                return false;
            }
            // 考察左上对角线：第 i 行 leftup 列有棋子吗？
            if (leftup >= 0) {
                if (result[i] == leftup) {
                    return false;
                }
            }
            // 考察右上对角线：第 i 行 rightup 列有棋子吗？
            if (rightup < 8) {
                if (result[i] == rightup) {
                    return false;
                }
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.cal8queens(0);

    }
}
