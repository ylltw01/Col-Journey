package com.sl.coljourney.algorithm.backtracking;

import java.util.List;

public class Nqueens {

    public List<List<String>> solveNQueens(int n) {
        int[] result = new int[8];
        colQueens(result, 8, 0);

        for (int i : result) {
            System.out.println(i);
        }

        return null;
    }

    private void colQueens(int[] result, int length, int n) {
        if (n == 8) {
            return;
        }
        for (int i = 0; i < length; i++) {
            // 判断放在该位置是否满足
            if (isOk(result, i, n)) {
                result[n] = i;
                colQueens(result, length, n + 1);
            }
        }
    }

    private boolean isOk(int[] result, int column, int row) {
        int left = column - 1, right = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == column) {
                return false;
            }
            if (left >= 0) {
                if (result[i] == left) {
                    return false;
                } else {
                    left --;
                }
            }
            if (right < result.length) {
                if (result[i] == right) {
                    return false;
                } else {
                    right ++;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Nqueens nqueens = new Nqueens();
        nqueens.solveNQueens(8);
    }

}
