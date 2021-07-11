package com.sl.coljourney.datastructure.tree;

import com.sl.coljourney.datastructure.tree.TreeNode;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 * 226. 翻转二叉树
 * 剑指 Offer 27. 二叉树的镜像
 * 难度：简单
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 例如输入：
 * <p>
 * >      4
 * >    /   \
 * >   2     7
 * >  / \   / \
 * > 1   3 6   9
 * 镜像输出：
 * >      4
 * >    /   \
 * >   7     2
 * >  / \   / \
 * > 9   6 3   1
 * 示例 1：
 * <p>
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

}
