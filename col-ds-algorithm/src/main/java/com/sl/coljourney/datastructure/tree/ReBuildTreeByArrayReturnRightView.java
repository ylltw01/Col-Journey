package com.sl.coljourney.datastructure.tree;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/c9480213597e45f4807880c763ddd5f0?tpId=117&tqId=37848&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 题目描述
 * 请根据二叉树的前序遍历，中序遍历恢复二叉树，并打印出二叉树的右视图
 * <p>
 * 示例1
 * 输入
 * [1,2,4,5,3],[4,2,5,1,3]
 * 返回值
 * [1,3,5]
 * <p>
 * 示例2
 * {1, 2, 4, 5, 6, 3}, {5, 4, 6, 2, 1, 3}
 * 返回值
 * [1,3,4,6]
 * <p>
 * 注：右视图是连续的，4 是 2 的左节点，但是因为是 6（右节点）的父节点，所有也有 4
 * <p>
 * 备注:
 * 二叉树每个节点的值在区间[1,10000]内，且保证每个节点的值互不相同。
 */
public class ReBuildTreeByArrayReturnRightView {

    /**
     * [1,2,4,5,3],[4,2,5,1,3]
     * <p>
     * 返回值
     * <p>
     * [1,3,5]
     */
    public int[] solve(int[] xianxu, int[] zhongxu) {
        if (xianxu.length <= 0) {
            return null;
        }
        Map<Integer, Integer> inOrderMap = new HashMap<>(zhongxu.length);
        for (int i = 0; i < zhongxu.length; i++) {
            inOrderMap.put(zhongxu[i], i);
        }
        TreeNode root = reverseBuildTree(xianxu, 0, 0, zhongxu.length - 1, inOrderMap);
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode node = queue.poll();
                if (node != null) {
                    if (i == size - 1) {
                        list.add(node.val);
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
        }
        int[] result = new int[list.size()];
        int index = 0;
        for (int num : list) {
            result[index++] = num;
        }
        return result;
    }

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
//        int[] preOrder = {1, 2, 4, 5, 3};
//        int[] inOrder = {4, 2, 5, 1, 3};

        int[] preOrder = {1, 2, 4, 5, 6, 3};
        int[] inOrder = {5, 4, 6, 2, 1, 3};
        //     1
        //   2  3
        //    4
        //   5 6

        ReBuildTreeByArrayReturnRightView view = new ReBuildTreeByArrayReturnRightView();
        int[] solve = view.solve(preOrder, inOrder);
        for (int i : solve) {
            System.out.println(i);
        }
    }


}
