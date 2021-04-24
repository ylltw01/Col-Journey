package com.sl.coljourney.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=117&tqId=37775&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * <p>
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 示例2：
 * 输入
 * [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
 * 返回值
 * {1,2,5,3,4,6,7}
 * <p>
 * 限制：
 * <p>
 * 0 <= 节点个数 <= 5000* 示例1
 * <p>
 * 解法：递归
 * 1. 先看看前序和中序遍历顺序
 * >  前序：root -> left -> right
 * >  中序：left -> root -> right
 * 2. 也就是说：
 * >  对于前序数组，最前面的就是根节点，然后就是该根节点的左子树，然后就是该根节点的右子树
 * >  对于中序数组，先根据前序数组找到根节点，其左边的就是左子树，其右边的就是右子树
 * >  可以根据中序数组，其根节点左边的个数就是左子树的个数，其根节点右边的个数就是右子树的个数
 * >  然后去前序数组中，根据个数可以找到左子树的数组，左子树数组的第一个元素就是左子树数组的根节点；右子树同理
 * 3. 根据这种情况，依次递归
 * 4. 递归退出条件，子树的开始节点大于等于子树的结束节点
 * 5. 还要考虑左节点或者右节点为null的情况
 */
public class ReBuildTreeByArray {

    /**
     * 例如，给出
     * <p>
     * 前序 preorder = [3,9,20,15,7]
     * 中序 inorder = [9,3,15,20,7]
     * <p>
     * 输入
     * [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
     * <p>
     * 返回值
     * {1,2,5,3,4,6,7}
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length <= 0) {
            return null;
        }
        Map<Integer, Integer> inOrderMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }
        return reverseBuildTree(preorder, 0, 0, inorder.length - 1, inOrderMap);
    }


    /**
     * 递归从上到下生成树
     *
     * @param preorder     前序遍历的数组
     * @param rootIndex    父节点在 preorder 数组中的索引
     * @param inStartIndex 当前子数在中序遍历 inorder 中的开始索引
     * @param inEndIndex   当前子数在中序遍历 inorder 中的结束索引
     * @param inOrderMap   中序遍历 inorder 数值与索引的Map
     * @return TreeNode
     * 0 , 0 , 6
     * 1, 0, 2
     */
    public TreeNode reverseBuildTree(int[] preorder, int rootIndex, int inStartIndex, int inEndIndex, Map<Integer, Integer> inOrderMap) {
        // rootIndex >= preorder.length： 为了应对右子节点为null情况（右子节点为 null 算出来的 rootIndex 会超过数组长度）
        // inEndIndex < inStartIndex： 为了应对左子节点为null情况（左子节点为 null 算出来的 inEndIndex 会小于 inStartIndex）
        if (rootIndex >= preorder.length || inEndIndex < inStartIndex) {
            return null;
        }
        // 根据 root 节点在 preorder 中的索引，构建 root 节点
        TreeNode root = new TreeNode(preorder[rootIndex]);

        // 结束了，退出条件
        if (inStartIndex >= inEndIndex) {
            return root;
        }
        // 根据 root 节点的值，拿到 root 节点在中序中的索引
        // 3
        // 1
        Integer rootIndexInOrder = inOrderMap.get(preorder[rootIndex]);
        // 1
        // 2
        // 左子树的根节点就是前序中 root 节点的下一个值
        int leftRootIndex = rootIndex + 1;
        // 4
        // 3
        // 右子树的根节点就是前序中 root 节点的索引加上左子树的个数（左子树个数 = root在中序索引 - 左子树开始索引）加1
        int rightRootIndex = rootIndexInOrder - inStartIndex + rootIndex + 1;

        // 1, 0, 2
        // 2, 0, 0
        //                                      左子树跟节点， 左子树在中序开始节点， root 索引 - 1
        root.left = reverseBuildTree(preorder, leftRootIndex, inStartIndex, rootIndexInOrder - 1, inOrderMap);
        // 4, 4, 6
        // 3, 2, 2
        //                                      右子树跟节点，    root 索引 + 1 ，                 左子树在中序结束节点
        root.right = reverseBuildTree(preorder, rightRootIndex, rootIndexInOrder + 1, inEndIndex, inOrderMap);

        return root;
    }

    public static void main(String[] args) {
//        int[] preOrder = {1, 2, 3, 4, 5, 6, 7};
//        int[] inorder = {3, 2, 4, 1, 6, 5, 7};
//        int[] preOrder = {3, 9, 20, 15, 7};
//        int[] inorder = {9, 3, 15, 20, 7};
        // 右子节点为null情况
        int[] preOrder = {1, 2};
        int[] inorder = {2, 1};
        // 左子节点为null情况
//        int[] preOrder = {1, 2};
//        int[] inorder = {1, 2};
        ReBuildTreeByArray tree = new ReBuildTreeByArray();
        TreeNode treeNode = tree.buildTree(preOrder, inorder);
        System.out.println(treeNode.val);
    }
}
