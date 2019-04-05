package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N皇后  难度：困难
 *
 * @author L
 */
public class Nqueens {

    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        List<List<String>> result = new ArrayList<>();
        colQueens(result, queens, n, 0);
        return result;
    }

    private void colQueens(List<List<String>> result, int[] queens, int n, int row) {
        if (row == n) {
            formatResult(result, queens, n);
            return;
        }
        for (int column = 0; column < n; column++) {
            // 判断放在该位置是否满足
            if (isOk(queens, column, row)) {
                queens[row] = column;
                colQueens(result, queens, n, row + 1);
            }
        }
    }

    private void formatResult(List<List<String>> result, int[] queens, int n) {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (queens[i] == j) {
                    line.append("Q");
                } else {
                    line.append(".");
                }
            }
            solution.add(line.toString());
        }
        result.add(solution);
    }

    private boolean isOk(int[] queens, int column, int row) {
        int left = column - 1, right = column + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (queens[i] == column) {
                return false;
            }
            if (left >= 0) {
                if (queens[i] == left) {
                    return false;
                }
            }
            if (right < queens.length) {
                if (queens[i] == right) {
                    return false;
                }
            }
            left--;
            right++;
        }
        return true;
    }


    public static void main(String[] args) {
        Nqueens nqueens = new Nqueens();
        List<List<String>> result = nqueens.solveNQueens(4);
        for (List<String> res : result) {
            for (String re : res) {
                System.out.println(re);
            }
            System.out.println(" --------------------------------- ");
        }
    }

}
