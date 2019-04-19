package com.sl.coljourney.datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历  难度：困难
 * @author L
 */
public class BinaryTreePostorderTraversal {

    /**
     * 基于递归实现
     *
     * @param root 二叉树根节点
     * @return 前序遍历结果
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorder(root, result);
        return result;
    }

    private void postorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorder(root.left, result);
        postorder(root.right, result);
        result.add(root.val);
    }

    /**
     * 基于循环实现
     *
     * @param root 二叉树根节点
     * @return 前序遍历结果
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        TreeNode pre = null;
        while (temp != null || !stack.isEmpty()) {
            // 循环入栈左节点，直到该子树到左叶子节点
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            TreeNode node = stack.peek();
            // 如果栈顶的右节点是空说明到叶子节点
            // 如果栈顶的右节点等于之前已经出栈过的节点，则说明栈顶元素该出栈
            if (node.right == null || node.right == pre) {
                stack.pop();
                pre = node;
                result.add(node.val);
            } else {
                // 否则继续入栈右节点
                temp = node.right;
            }
        }
        return result;
    }

    /**
     * 基于循环实现
     *
     * @param root 二叉树根节点
     * @return 前序遍历结果
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            // 取巧实现，逆序的插入结果List
            result.add(0, node.val);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        BinaryTreePostorderTraversal traversal = new BinaryTreePostorderTraversal();

        List<Integer> integers = traversal.postorderTraversal2(node);

        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

}
