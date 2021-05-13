package com.sl.coljourney.datastructure.tree;

/**
 * https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=117&tqId=37757&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/balanced-binary-tree/submissions/
 * 110. 平衡二叉树
 * 难度：简单
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：true
 * <p>
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -104 <= Node.val <= 104
 */
public class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftLevel = level(root.left);
        int rightLevel = level(root.right);
        boolean thisRoot = leftLevel - rightLevel >= -1 && leftLevel - rightLevel <= 1;
        // 当前树是否为平衡二叉树  && 左树是否为平衡二叉树 && 右树是否为平衡二叉树
        return thisRoot && isBalanced(root.left) && isBalanced(root.right);
    }

    public int level(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(level(root.left), level(root.right));
    }

    public static void main(String[] args) {
        //  [1,2,2,3,null,null,3,4,null,null,4]
        TreeNode t1 = new TreeNode(1);
        TreeNode t2L = new TreeNode(2);
        TreeNode t2R = new TreeNode(2);
        TreeNode t3L = new TreeNode(3);
        TreeNode t3R = new TreeNode(3);
        TreeNode t4L = new TreeNode(4);
        TreeNode t4R = new TreeNode(4);

        t1.left = t2L;
        t1.right = t2R;

        t2L.left = t3L;
        t2R.right = t3R;

        t3L.left = t4L;
        t3R.right = t4R;

        BalancedBinaryTree binaryTree = new BalancedBinaryTree();
        boolean balanced = binaryTree.isBalanced(t1);
        System.out.println(balanced);
    }

}
