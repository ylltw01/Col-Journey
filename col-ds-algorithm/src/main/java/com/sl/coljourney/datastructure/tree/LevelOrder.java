package com.sl.coljourney.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * https://www.nowcoder.com/practice/04a5560e43e24e9db4595865dc9c63a3?tpId=117&tqId=37723&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 二叉树层序遍历
 * <p>
 * 题目描述
 * 给定一个二叉树，返回该二叉树层序遍历的结果，（从左到右，一层一层地遍历）
 * 例如：
 * 给定的二叉树是{3,9,20,#,#,15,7},
 * <p>
 * 该二叉树层序遍历的结果是
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 示例1
 * 输入
 * {1,2}
 * 返回值
 * [[1],[2]]
 * <p>
 * 示例2
 * 输入
 * {1,2,3,4,#,#,5}
 * 返回值
 * [[1],[2,3],[4,5]]
 */
public class LevelOrder {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> orders = new ArrayList<>();
        if (root == null) {
            return orders;
        }
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.push(root);
        while (true) {
            LinkedList<TreeNode> nextNodeQueue = new LinkedList<>();
            ArrayList<Integer> tier = new ArrayList<>();
            while (!nodeQueue.isEmpty()) {
                TreeNode treeNode = nodeQueue.removeFirst();
                tier.add(treeNode.val);
                if (treeNode.left != null) {
                    nextNodeQueue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    nextNodeQueue.add(treeNode.right);
                }
            }
            orders.add(tier);
            if (nextNodeQueue.isEmpty()) {
                break;
            }
            nodeQueue = nextNodeQueue;
        }
        return orders;
    }

}
