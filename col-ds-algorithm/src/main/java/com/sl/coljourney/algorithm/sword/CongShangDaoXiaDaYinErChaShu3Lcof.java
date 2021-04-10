package com.sl.coljourney.algorithm.sword;


import com.sl.coljourney.datastructure.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://www.nowcoder.com/practice/47e1687126fa461e8a3aff8632aa5559?tpId=117&tqId=37722&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * 难度：中等
 * <p>
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * <p>
 * 例如:
 * 给定二叉树:[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 */
public class CongShangDaoXiaDaYinErChaShu3Lcof {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int i = 1;
        while (!stack.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            Stack<TreeNode> nextStack = new Stack<>();

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                level.add(node.val);
                if (i % 2 != 1) {
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                } else {
                    if (node.left != null) {
                        nextStack.push(node.left);
                    }
                    if (node.right != null) {
                        nextStack.push(node.right);
                    }
                }
            }
            result.add(level);
            stack = nextStack;
            i++;
        }

        return result;
    }

}
