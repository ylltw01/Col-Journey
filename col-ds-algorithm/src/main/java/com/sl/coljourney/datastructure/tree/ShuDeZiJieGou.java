package com.sl.coljourney.datastructure.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 * 剑指 Offer 26. 树的子结构
 * 难度：中等
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * <p>
 * >      3
 * >    / \
 * >   4   5
 * >  / \
 * > 1   2
 * 给定的树 B：
 * <p>
 * >   4
 * >  /
 * > 1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 * <p>
 * 示例 2：
 * <p>
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 */
public class ShuDeZiJieGou {

    /**
     * dfs 思路，是基于记录次数，更优
     */
    int tempNum = 0;
    int bLength = 0;
    boolean result = false;

    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        bLengthDfs(B);
        dfs(A, B, B);
        return result;
    }

    public void bLengthDfs(TreeNode B) {
        if (B == null) {
            return;
        } else {
            bLength++;
            bLengthDfs(B.left);
            bLengthDfs(B.right);
        }
    }

    public void dfs(TreeNode A, TreeNode B, TreeNode tempB) {
        if (tempNum == bLength) {
            result = true;
        } else if (result || A == null || tempB == null) {
            return;
        } else {
            if (A.val == tempB.val) {
                tempNum++;
                dfs(A.left, B, tempB.left);
                dfs(A.right, B, tempB.right);
            } else {
                tempNum = 0;
                dfs(A.left, B, B);
                dfs(A.right, B, B);
            }
        }
    }
//
//    作者：rain-ru
//    链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/solution/su-kan-dfssi-lu-qing-xi-shi-jian-100-by-sz4hv/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


    /**
     * 速度有点惨，自己撸的
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        LinkedList<TreeNode> listA = new LinkedList<>();
        listA.add(A);
        LinkedList<TreeNode> listB = new LinkedList<>();
        listB.add(B);
        return isSub(listA, B, listB);
    }

    private boolean isSub(Deque<TreeNode> a, TreeNode b, Deque<TreeNode> currB) {
        if (currB.isEmpty()) {
            return true;
        }
        if (a.isEmpty()) {
            return false;
        }
        int sizeA = a.size();
        Deque<TreeNode> nextB = new LinkedList<>();
        boolean mustEq = false;
        for (int i = 0; i < sizeA; i++) {
            TreeNode nodeA = a.poll();
            if (nodeA == null) {
                continue;
            }
            if (nodeA.left != null) {
                a.addLast(nodeA.left);
            }
            if (nodeA.right != null) {
                a.addLast(nodeA.right);
            }

            if (currB.isEmpty()) {
                continue;
            }

            TreeNode nodeB = currB.peek();
            if (mustEq && nodeA.val != nodeB.val) {
                mustEq = false;
            } else if (nodeA.val == nodeB.val) {
                mustEq = true;
                if (nodeB.left != null) {
                    nextB.addLast(nodeB.left);
                }
                if (nodeB.right != null) {
                    nextB.addLast(nodeB.right);
                }
                currB.pop();
            }
        }
        if (mustEq && currB.isEmpty()) {
            return isSub(a, b, nextB);
        } else {
            currB.clear();
            currB.addLast(b);
            return isSub(a, b, currB);
        }
    }

    public static void main(String[] args) {
        TreeNode a2 = new TreeNode(2);
        TreeNode a3 = new TreeNode(3);
        TreeNode a21 = new TreeNode(2);
        TreeNode a1 = new TreeNode(1);
        a2.left = a3;
        a2.right = a21;
        a3.left = a1;

        TreeNode b3 = new TreeNode(3);
        TreeNode b2 = new TreeNode(2);
        TreeNode b21 = new TreeNode(2);
        b3.right = b2;
        b2.left = b21;

        ShuDeZiJieGou shuDeZiJieGou = new ShuDeZiJieGou();
        boolean subStructure = shuDeZiJieGou.isSubStructure(a2, b3);
        System.out.println(subStructure);
    }

}
/*

[2,3,2,1]
[3,null,2,2]

    2
  3   2
1

    3
       2
    2

[4,2,3,4,5,6,7,8,9]
[4,8,9]

[10,12,6,8,3,11]
[10,12,6,8]

[-2,1]
[-2,1,-2]

  -2
1

  -2
1   -2
 */
