package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/beautiful-arrangement/
 * 526. 优美的排列
 * 假设有从 1 到 n 的 n 个整数。用这些整数构造一个数组 perm（下标从 1 开始），只要满足下述条件 之一 ，该数组就是一个 优美的排列 ：
 * <p>
 * perm[i] 能够被 i 整除
 * i 能够被 perm[i] 整除
 * 给你一个整数 n ，返回可以构造的 优美排列 的 数量 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * 输出：2
 * 解释：
 * 第 1 个优美的排列是 [1,2]：
 * - perm[1] = 1 能被 i = 1 整除
 * - perm[2] = 2 能被 i = 2 整除
 * 第 2 个优美的排列是 [2,1]:
 * - perm[1] = 2 能被 i = 1 整除
 * - i = 2 能被 perm[2] = 1 整除
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：1
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 15
 * <p>
 * 回溯法通用公式：
 * <p>
 * void backtrack() {
 * *   if(满足条件) {
 * *       res++;
 * *       return;
 * *   }
 * *   for i in 所有选项
 * *       visit[i]=true;
 * *       backtrack();
 * *       visit[i]=false;
 * }
 */
public class BeautifulArrangement {


//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/beautiful-arrangement/solution/you-mei-de-pai-lie-by-leetcode-solution-vea2/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    List<Integer>[] match;
    boolean[] vis;
    int num;

    public int countArrangement(int n) {
        vis = new boolean[n + 1];
        match = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        backtrack(1, n);
        return num;
    }

    public void backtrack(int index, int n) {
        if (index == n + 1) {
            num++;
            return;
        }
        for (int x : match[index]) {
            if (!vis[x]) {
                vis[x] = true;
                backtrack(index + 1, n);
                vis[x] = false;
            }
        }
    }

    /**
     * 失败了
     */
    public int countArrangement2(int n) {
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            path.add(i);
        }
        return bt(path, 1);
    }

    private int bt(List<Integer> path, int idx) {
        if (idx >= path.size()) {
            System.out.println(StringUtils.join(path, ", "));
            return 1;
        }

        if (path.get(idx) % idx == 0 || idx % path.get(idx) == 0) {
            System.out.println("idx = " + idx + ", path.get(idx) = " + path.get(idx));
            int ans = 0;
            for (int i = idx; i < path.size(); i++) {
                Collections.swap(path, i, idx);
                if (path.get(idx) % idx == 0 || idx % path.get(idx) == 0) {
                    ans += bt(path, idx + 1);
                }
                Collections.swap(path, i, idx);
            }
            return ans;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        BeautifulArrangement arrangement = new BeautifulArrangement();
        int i = arrangement.countArrangement(3);
        System.out.println(i);
    }


}
/*

0 1 2 3
  1 2 3
  2 1 3
  3 2 1

1 2 3
1 3 2
2 1 3
2 3 1
3 2 1
3 1 2

f(1,2,3) = (1 + f(2,3)) + (2 + f(1,3)) + (3 + f(1, 2))


 */