package com.sl.coljourney.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 94. 二叉树的中序遍历  难度：中等
 *
 * @author L
 */
public class BinaryTreeInorderTraversal {

    /**
     * 中序遍历，基于递归
     *
     * @param root 二叉树根节点
     * @return 中序遍历结果
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }

    /**
     * 中序遍历，基于循环
     *
     * @param root 二叉树根节点
     * @return 中序遍历结果
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null || !stack.isEmpty()) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            TreeNode node = stack.pop();
            result.add(node.val);
            temp = node.right;
        }
        return result;
    }

}
