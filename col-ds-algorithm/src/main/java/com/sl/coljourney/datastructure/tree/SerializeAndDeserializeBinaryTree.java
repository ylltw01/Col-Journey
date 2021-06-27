package com.sl.coljourney.datastructure.tree;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/cf7e25aa97c04cc1a68c8f040e71fb84?tpId=117&&tqId=37782&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * 297. 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * 示例 4：
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * 题解：bfs 广度优先遍历
 * serialize：广度优先遍历，跟示例输入一样的顺序
 * deserialize：还原，在还原的时候注意。二叉树可能不是完全二叉树，因此不能直接算出其子节点的idx
 * >            也应该是一层一层的广度还原。
 * >  0  1 2   3    4 5 6   7    8    9 10  11 12     13 14
 * > [1, 2,3,  null,9,4,5,  null,null,6,7, null,null  10,11]
 * 比如：2 的子节点分别是 null 和 9。【 即，当前这一层的结束的【下一个】idx和【下下个】idx 】
 * 这样就不会有空节点的困扰
 */
public class SerializeAndDeserializeBinaryTree {

    /**
     * Encodes a tree to a single string.
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder ans = new StringBuilder();
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);

        boolean allNotNull = true;
        while (!deque.isEmpty() && allNotNull) {
            int i = deque.size();
            allNotNull = false;
            while (i > 0) {
                TreeNode node = deque.pollFirst();
                if (node == null) {
                    ans.append("#");
                } else {
                    ans.append(node.val);
                    deque.addLast(node.left);
                    deque.addLast(node.right);
                    if (node.left != null || node.right != null) {
                        allNotNull = true;
                    }
                }
                ans.append(",");
                i--;
            }
        }
        return ans.substring(0, ans.length() - 1);
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }

        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Deque<TreeNode> deque = new LinkedList<>();
        deque.addLast(root);
        int idx = 1;
        while (!deque.isEmpty() && idx < arr.length) {
            int i = deque.size();
            while (i > 0) {
                TreeNode node = deque.pollFirst();
                if (node != null) {
                    if (!"#".equals(arr[idx])) {
                        TreeNode left = new TreeNode(Integer.parseInt(arr[idx]));
                        deque.addLast(left);
                        node.left = left;
                    }
                    idx += 1;
                    if (!"#".equals(arr[idx])) {
                        TreeNode right = new TreeNode(Integer.parseInt(arr[idx]));
                        deque.addLast(right);
                        node.right = right;
                    }
                    idx += 1;
                }
                i--;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);

        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        t4.left = t6;
        t4.right = t7;

        SerializeAndDeserializeBinaryTree sd = new SerializeAndDeserializeBinaryTree();
        String serialize = sd.serialize(t1);
        System.out.println(serialize);

        TreeNode root = sd.deserialize(serialize);

        System.out.println(root);
    }

}

/*
[1,2,3,null,null,4,5,6,7]

[1, 2, 3, null, null, 4, 5, null, null, 6, 7]

        1
    2        3
n    n     4   5
         6   7

 0  1 2   3    4 5 6   7    8    9 10  11 12     13 14
[1, 2,3,  null,9,4,5,  null,null,6,7, null,null  10,11]    3 * 2 + 1 = 7 （7，8）
        1
    2        3
n    9     4   5
         6   7
       10 11

[1, 2,3,  9, null,4,5,  null,null,6,7]
        1
    2        3
9    n     4   5
         6   7

 */
