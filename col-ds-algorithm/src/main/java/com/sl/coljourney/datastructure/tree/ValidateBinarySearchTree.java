package com.sl.coljourney.datastructure.tree;


import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 98. 验证二叉搜索树
 * 难度：中等
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4。
 * <p>
 * 题解一：
 * 1. 二叉搜索树，所有子树，是左子节点比中间节点小，中间节点比右子节点小。
 * 2. 还有一个条件就是，根节点要比所有左子节点大，而且要比所有右子节点小。
 * 3. 所以，不能进死胡同（比如定义必须大于那些node，小于那些node...），为当前节点根据树的结构定义一个值的范围。如下例子：
 * >               3
 * >            1     5
 * >         0   2   4  6
 * <p>
 * >         1 (min, 3)   5 (3, max)
 * >         0 (min, 1)  2(1, 3)  4(3, 5)  6(5, max)
 * 题解二：
 * 二叉搜索树的中序遍历，就刚刚好是一个升序排列的数组，真牛逼，为啥没想到
 */
public class ValidateBinarySearchTree {

    /**
     * 递归，加值范围
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBst(TreeNode root, long lower, long upper) {
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
     * 二叉搜索树的中序遍历，就刚刚好是一个升序排列的数组
     */
    public boolean isValidBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    public static void main(String[] args) {
        // [2147483647]
        // [3,1,5,0,2,4,6]
        /*
               3
            1     5
         0   2   4  6

         1 (min, 3)   5 (3, max)
         0 (min, 1)  2(1, 3)  4(3, 5)  6(5, max)
         */
        TreeNode t3 = new TreeNode(3);
        TreeNode t1 = new TreeNode(1);
        TreeNode t5 = new TreeNode(5);

        TreeNode t0 = new TreeNode(0);
        TreeNode t2 = new TreeNode(2);
        TreeNode t4 = new TreeNode(4);
        TreeNode t6 = new TreeNode(6);

        t3.left = t1;
        t3.right = t5;
        t1.left = t0;
        t1.right = t2;
        t5.left = t4;
        t5.right = t6;

        ValidateBinarySearchTree searchTree = new ValidateBinarySearchTree();
        boolean validBST = searchTree.isValidBST(t3);
        System.out.println(validBST);

    }

    /*
    2 1 3

      2
    1  3
    [5,4,6,null,null,3,7]

             5
         4       6
              3      7
           1    9   2 8


     */
}
