package com.sl.coljourney.datastructure.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/path-sum-ii/submissions/
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 难度：中等
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 target = 22，
 * <p>
 * >              5
 * >             / \
 * >           4   8
 * >           /   / \
 * >          11  13  4
 * >         /  \    / \
 * >        7    2  5   1
 * 返回:
 * <p>
 * > [
 * >   [5,4,11,2],
 * >   [5,8,4,5]
 * > ]
 */
public class PathSumEqualsTargetOfBinaryTree2 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        recursive(ans, root, path, targetSum);
        return ans;
    }

    private void recursive(List<List<Integer>> ans, TreeNode root, Deque<Integer> path, int target) {
        if (root == null) {
            return;
        }

        // 加入队列
        path.offerLast(root.val);
        if (root.left == null && root.right == null && target - root.val == 0) {
            ans.add(new ArrayList<>(path));
        }
        target -= root.val;
        recursive(ans, root.left, path, target);
        recursive(ans, root.right, path, target);

        // 移除队列，这里当左子节点（root），执行完之后，其 left 和 right 都为 null，因此这里会直接移除该左子节点（root）
        path.pollLast();
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;

        PathSumEqualsTargetOfBinaryTree2 pathSum = new PathSumEqualsTargetOfBinaryTree2();
        List<List<Integer>> lists = pathSum.pathSum(node1, 3);

        System.out.println(lists.size());
    }

}
