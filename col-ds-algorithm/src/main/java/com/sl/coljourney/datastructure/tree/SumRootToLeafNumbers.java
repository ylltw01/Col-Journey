package com.sl.coljourney.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.nowcoder.com/practice/185a87cd29eb42049132aed873273e83?tpId=117&tqId=37715&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 * 129. 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * 提示：
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 *
 * <p>
 * 解法：
 * 1. 深度遍历
 * 2. 广度遍历
 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }
        // 从上往下算，避免了对于节点值为 0 的很多问题
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }


    public int sumNumbers2(TreeNode root) {
        List<String> ans = root2Leaf(root);
        int sum = 0;
        for (String it : ans) {
            sum += Integer.parseInt(it);
        }
        return sum;
    }

    private List<String> root2Leaf(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> left = root2Leaf(root.left);
        List<String> right = root2Leaf(root.right);
        left.addAll(right);

        List<String> ans = new ArrayList<>();
        if (left.isEmpty()) {
            ans.add(String.valueOf(root.val));
            return ans;
        }
        for (String it : left) {
            ans.add(root.val + it);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;

        SumRootToLeafNumbers sumRoot = new SumRootToLeafNumbers();
        int i = sumRoot.sumNumbers(t1);
        System.out.println(i);

// [5,3,2,7,0,6,null,null,null,0]
// [0, 1]


        System.out.println(10 ^ 1);
    }

}
