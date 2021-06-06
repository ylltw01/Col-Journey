package com.sl.coljourney.datastructure.tree;

/**
 * https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/submissions/
 * https://www.nowcoder.com/practice/da785ea0f64b442488c125b441a4ba4a?tpId=117&&tqId=37716&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * 124. 二叉树中的最大路径和
 * 难度：困难
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 题解：
 * 还是看看官方题解
 */
public class BinaryTreeMaximumPathSum {

    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return maxSum;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算左子树的最大值，当然如果小于0 就取 0
        int leftSum = Math.max(dfs(root.left), 0);
        // 计算右子树的最大值，当然如果小于0 就取 0
        int rightSum = Math.max(dfs(root.right), 0);

        // 计算当前子树的值，是否是最大值
        maxSum = Math.max(maxSum, leftSum + rightSum + root.val);

        // 这里注意，返回的是，当前节点与左树或右树路径的最大值
        return root.val + Math.max(leftSum, rightSum);
    }

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);

        node3.left = node9;
        node3.right = node20;
        node20.left = node15;
        node20.right = node7;

        BinaryTreeMaximumPathSum pathSum = new BinaryTreeMaximumPathSum();
        int i = pathSum.maxPathSum(node3);

        System.out.println(i);
    }

}
/*

        -10
    9       20
         15    7


         3
    9       20
         15    7

 */