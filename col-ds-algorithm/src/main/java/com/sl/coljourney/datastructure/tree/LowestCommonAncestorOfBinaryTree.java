package com.sl.coljourney.datastructure.tree;

/**
 * https://www.nowcoder.com/practice/e0cc33a83afe4530bcec46eba3325116?tpId=117&tqId=37826&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/submissions/
 * 236. 二叉树的最近公共祖先
 * 难度：中等
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * <p>
 * 提示：
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * <p>
 * 解法：
 * 1. Map映射法，将所有关系都存map中，然后递归get
 * 2. 递归法：
 * >     递归到子节点，如果子节点与输入节点一致，则返回当前节点
 * >     如果当前节点的左右节点返回的都不为空，则公共节点为当前节点
 * <p>
 * >                   15
 * >           7            18
 * >      5        9
 * >    3   6   8   11
 * 如上图：
 * 例1：
 * >   如果 p = 3，q = 6
 * >   那么在 root = 3 或者 root = 6 时候，分别返回 3、6
 * >   然后递归到 root = 5, left 和 right 都不为空，则 root = 5 就是最近公共节点
 * <p>
 * 例2：
 * >   如果 p = 5，q = 11
 * >   那么在 root = 3 时候，返回 3
 * >   那么在 root = 5 时候，由于left 返回了3， right 返回了 null， 那么返回 3
 * <p>
 * >   那么在 root = 11 时候，返回 11
 * >   那么在 root = 9 时候，由于left 返回了 null， right 返回了 11， 那么返回 11
 * <p>
 * >   那么在 root = 7 时候，由于left（节点5） 返回了 3， righ（节点9）t 返回了 11， left 和 right 都不为空，则 root = 7 就是最近公共节点
 */
public class LowestCommonAncestorOfBinaryTree {

    /**
     * leetcode
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return reverseTree(root, p, q);
    }

    public TreeNode reverseTree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = null;
        if (root.left != null) {
            left = reverseTree(root.left, p, q);
        }

        TreeNode right = null;
        if (root.right != null) {
            right = reverseTree(root.right, p, q);
        }

        if (right != null && left != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }

    /**
     * 牛客
     */
    public int lowestCommonAncestor(TreeNode root, int o1, int o2) {
        return reverseTree(root, o1, o2).val;
    }

    public TreeNode reverseTree(TreeNode root, int o1, int o2) {
        if (root == null) {
            return null;
        }
        if (root.val == o1 || root.val == o2) {
            return root;
        }
        TreeNode left = null;
        if (root.left != null) {
            left = reverseTree(root.left, o1, o2);

        }
        TreeNode right = null;
        if (root.right != null) {
            right = reverseTree(root.right, o1, o2);
        }
        if (right != null && left != null) {
            return root;
        } else if (left != null) {
            return left;
        } else {
            return right;
        }
    }

}
