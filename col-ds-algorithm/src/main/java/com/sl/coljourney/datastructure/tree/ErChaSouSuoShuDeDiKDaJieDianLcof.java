package com.sl.coljourney.datastructure.tree;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 4
 * <p>
 * 解法：
 * 本文解法基于此性质：二叉搜索树的中序遍历为 递增序列 。
 * <p>
 * 根据以上性质，易得二叉搜索树的 中序遍历倒序 为 递减序列 。
 * 因此，求 “二叉搜索树第 kk 大的节点” 可转化为求 “此树的中序遍历倒序的第 kk 个节点”。
 */
public class ErChaSouSuoShuDeDiKDaJieDianLcof {
    int result;
    int k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        reverse(root);
        return result;
    }

    public void reverse(TreeNode root) {
        if (root != null) {
            reverse(root.right);
            k -= 1;
            if (k == 0) {
                result = root.val;
                return;
            }
            reverse(root.left);
        }
    }

    public static void main(String[] args) {
        // [5,3,6,2,4,null,null,1]
        // 3
        //  [3,1,4,null,2], k = 1
        TreeNode root = new TreeNode(3);
        root.right = new TreeNode(4);
        TreeNode treeNode1 = new TreeNode(1);
        root.left = treeNode1;
        treeNode1.right = new TreeNode(2);
        ErChaSouSuoShuDeDiKDaJieDianLcof lcof = new ErChaSouSuoShuDeDiKDaJieDianLcof();
        System.out.println(lcof.kthLargest(root, 1));
    }

}
