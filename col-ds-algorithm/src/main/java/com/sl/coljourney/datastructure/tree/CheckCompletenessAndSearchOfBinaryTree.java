package com.sl.coljourney.datastructure.tree;

import java.util.LinkedList;

/**
 * https://www.nowcoder.com/practice/f31fc6d3caf24e7f8b4deb5cd9b5fa97?tpId=117&tqId=37822&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 判断一个棵树，该二叉树是否为搜索二叉树和完全二叉树
 * 难度：中等
 * 题目描述
 * 给定一棵二叉树，已经其中没有重复值的节点，请判断该二叉树是否为搜索二叉树和完全二叉树。
 * 示例1
 * 输入
 * {2,1,3}
 * 返回值
 * [true,true]
 */
public class CheckCompletenessAndSearchOfBinaryTree {

    public boolean[] judgeIt(TreeNode root) {
        boolean[] ans = new boolean[2];
        if (root == null) {
            ans[0] = true;
            ans[1] = true;
            return ans;
        }
        ans[0] = isValidBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
        ans[1] = isCompleteTree(root);
        return ans;
    }

    private boolean isValidBst(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <= lower || root.val >= upper) {
            return false;
        }
        boolean leftBst = isValidBst(root.left, lower, root.val);
        boolean rightBst = isValidBst(root.right, root.val, upper);
        return leftBst && rightBst;
    }

    /**
     * 呵呵，这里用leecode 的解法，还超时了，最终是只能遍历一层，删除上层的才行
     */
    private boolean isCompleteTree(TreeNode root) {
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

}
