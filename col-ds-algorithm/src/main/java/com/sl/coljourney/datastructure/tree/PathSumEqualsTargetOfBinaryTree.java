package com.sl.coljourney.datastructure.tree;

/**
 * https://www.nowcoder.com/practice/508378c0823c423baa723ce448cbfd0c?tpId=117&&tqId=37719&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/path-sum/
 * 112. 路径总和
 * 难度：简单
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 */
public class PathSumEqualsTargetOfBinaryTree {

    /**
     * 递归
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        // 左节点和右节点都为空，则为根节点，返回是否满足
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // 分别考察左子树 和 右子树
        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

}
