package com.sl.coljourney.datastructure.tree;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/
 * 958. 二叉树的完全性检验-完全二叉树
 * 难度：中等
 * 给定一个二叉树，确定它是否是一个完全二叉树。
 * <p>
 * 百度百科中对完全二叉树的定义如下：
 * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,4,5,6]
 * 输出：true
 * 解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
 * <p>
 * 示例 2：
 * <p>
 * 输入：[1,2,3,4,5,null,7]
 * 输出：false
 * 解释：值为 7 的结点没有尽可能靠向左侧。
 * <p>
 * 提示：
 * 树中将会有 1 到 100 个结点。
 * <p>
 * 题解：
 * 其实完全二叉树很简单，广度优先遍历，如果中间出现空的节点，那么他一定不是完全二叉树，如上面举例 [1,2,3,4,5,6] 和 [1,2,3,4,5,null,7]
 */
public class CheckCompletenessOfBinaryTree {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 用于存储广度优先遍历的结果，一层一层的遍历，先存左节点在存右节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int i = 0;
        boolean matchNull = false;
        while (i < queue.size()) {
            TreeNode node = queue.get(i);
            // 如果中间存在了空节点，那么它就不是完全二叉树
            if (node == null) {
                matchNull = true;
            } else {
                // 如果中间存在了空节点，下一个非空节点，将返回 false
                if (matchNull) {
                    return false;
                } else {
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            i++;
        }
        return true;
    }

    /**
     * 这个题在牛客超时了，所以改成了遍历一层删除上层元素
     */
    private boolean isCompleteTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 用于存储广度优先遍历的结果，一层一层的遍历，先存左节点在存右节点
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        boolean matchNull = false;
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode node = queue.pollFirst();
                // 如果中间存在了空节点，那么它就不是完全二叉树
                if (node == null) {
                    matchNull = true;
                } else {
                    // 如果中间存在了空节点，下一个非空节点，将返回 false
                    if (matchNull) {
                        return false;
                    } else {
                        queue.add(node.left);
                        queue.add(node.right);
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);

        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        t4.left = t8;
        t4.right = t9;
        t7.left = t10;

        CheckCompletenessOfBinaryTree completeness = new CheckCompletenessOfBinaryTree();
        boolean completeTree2 = completeness.isCompleteTree2(t1);
        boolean completeTree = completeness.isCompleteTree(t1);
        System.out.println(completeTree2);
        System.out.println(completeTree);
    }

}

/*
 [1,2,3,5,null,7,8]

        1
     2       3
   5  null  7  8

[1,2,3,4,5,6]
        1
     2       3
   4  5    6

1 2 3 null null 7 8
         1
     2         3
  null  null  7  8

 */

