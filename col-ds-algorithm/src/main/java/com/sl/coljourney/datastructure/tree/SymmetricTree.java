package com.sl.coljourney.datastructure.tree;

/**
 * https://www.nowcoder.com/practice/1b0b7f371eae4204bc4a7570c84c2de1?tpId=117&&tqId=37724&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/symmetric-tree/
 * 101. 对称二叉树
 * 难度：简单
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * >     1
 * >    / \
 * >   2   2
 * >  / \ / \
 * > 3  4 4  3
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * >     1
 * >    / \
 * >   2   2
 * >    \   \
 * >    3    3
 * <p>
 * 题解：
 * 首先，理解题意，对称的是要 3 4 4 3 这种，而 3 4 3 4 这种不是对称，也就是节点和值都要相等
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return symmetricReverse(root, root);
    }

    public boolean symmetricReverse(TreeNode left, TreeNode right) {
        // 两个 if 判断，只要 left 和 right 任意一个为 null 都返回 false
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }

        // 校验当前节点左右是否相等
        boolean eq = left.val == right.val;
        // 左节点的左节点 与 右节点的右节点
        boolean leftSym = symmetricReverse(left.left, right.right);
        // 左节点的右节点 与 右节点的左节点
        boolean rightSym = symmetricReverse(left.right, right.left);
        return eq && leftSym && rightSym;
    }
}

/*

    1
   / \
  2   2
   \   \
   3    3

 */