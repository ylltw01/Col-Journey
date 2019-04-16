package com.sl.coljourney.datastructure.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 589. N叉树的前序遍历  难度：简单
 *
 * @author L
 */
public class NaryTreePreorderTraversal {

    /**
     * 基于递归实现
     *
     * @param root 树 root 节点
     * @return 前序遍历结果
     */
    public List<Integer> preorder(Nodes root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal(root, result);
        return result;
    }

    private void preorderTraversal(Nodes root, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (root.children == null) {
            result.add(root.val);
            return;
        }
        result.add(root.val);
        for (Nodes child : root.children) {
            preorderTraversal(child, result);
        }
    }

    /**
     * 非递归使用循环方式实现，借助于队列
     * @param root 树 root 节点
     * @return 前序遍历结果
     */
    public List<Integer> preorder2(Nodes root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        ArrayDeque<Nodes> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Nodes node = queue.pollFirst();
            if (node != null) {
                result.add(node.val);
                for (int i = node.children.size() - 1; i >= 0; i--) {
                    queue.addFirst(node.children.get(i));
                }
            }
        }
        return result;
    }

}
