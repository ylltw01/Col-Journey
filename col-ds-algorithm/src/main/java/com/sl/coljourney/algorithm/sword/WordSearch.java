package com.sl.coljourney.algorithm.sword;

/**
 * https://leetcode-cn.com/problems/word-search/
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 * 79. 单词搜索
 * 难度：中等
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * <p>
 * 示例 3：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (board.length <= 0) {
            return false;
        }
        boolean ex = false;
        char c = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (c == board[i][j]) {
                    // 只要匹配成功了，则表示可以开始上下左右查找了
                    ex = exist(board, word, i, j, 0);
                    // 匹配成功一个就返回
                    if (ex) {
                        return true;
                    }
                }
            }
        }
        return ex;
    }

    private boolean exist(char[][] board, String word, int xidx, int yidx, int wi) {
        // 边界限制，剪枝，如果匹配完了字符串，返回true
        if (wi == word.length()) {
            return true;
        } else if (xidx >= board.length || yidx >= board[0].length || xidx < 0 || yidx < 0) {
            return false;
        }

        char c = word.charAt(wi);
        // 如果当前不等于，直接退出
        if (board[xidx][yidx] != c) {
            return false;
        }
        // 这里将 [xidx][yidx] 的值替换为 0，防止重复使用该值，也是一个取巧，不用判断当前 [xidx][yidx] 是由那个坐标过来的
        char t = board[xidx][yidx];
        board[xidx][yidx] = '0';
        // 匹配字符串的下一个字符
        wi++;
        // 这里就是 4 种情况的递归，上下左右
        boolean ans = exist(board, word, xidx + 1, yidx, wi) ||
                exist(board, word, xidx - 1, yidx, wi) ||
                exist(board, word, xidx, yidx + 1, wi) ||
                exist(board, word, xidx, yidx - 1, wi);
        // 将 [xidx][yidx] 替换回去
        board[xidx][yidx] = t;
        return ans;
    }

    public static void main(String[] args) {
        // [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
        //"ABCCED"

        // [["a","b"],
        // ["c","d"]]
        // "acdb"
        char[][] arr = {
                {'A', 'B', 'C', 'E' },
                {'S', 'F', 'C', 'S' },
                {'A', 'D', 'E', 'E' }};
        WordSearch wordSearch = new WordSearch();
        boolean ex = wordSearch.exist(arr, "ABCCED");
        System.out.println(ex);


        // [["a","b"],
        //  ["c","d"]]
        // "acdb"

        // [["a","b"],
        //  ["c","d"]]
        // "dbac"
        char[][] arr2 = {
                {'a', 'b' },
                {'c', 'd' }};
        boolean ex2 = wordSearch.exist(arr2, "acdb");
        System.out.println(ex2);

        boolean ex21 = wordSearch.exist(arr2, "dbac");
        System.out.println(ex21);

        // [["a","a","a","a"],["a","a","a","a"],["a","a","a","a"]]
        // "aaaa aaaa aaaa a"

        char[][] arr3 = {
                {'a', 'a', 'a', 'a' },
                {'a', 'a', 'a', 'a' },
                {'a', 'a', 'a', 'a' }};
        boolean ex3 = wordSearch.exist(arr3, "aaaaaaaaaaaaa");
        System.out.println(ex3);

    }
}
