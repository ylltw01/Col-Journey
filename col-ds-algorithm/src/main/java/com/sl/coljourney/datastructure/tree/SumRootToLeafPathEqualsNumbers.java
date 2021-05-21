package com.sl.coljourney.datastructure.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.nowcoder.com/practice/840dd2dc4fbd4b2199cd48f2dadf930a?tpId=117&tqId=37718&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 题目描述
 * 给定一个二叉树和一个值 sum，请找出所有的根节点到叶子节点的节点值之和等于 sum 的路径，
 * 例如：
 * 给出如下的二叉树，sum=22 sum=22，
 * <p>
 * 返回
 * [
 * [5,4,11,2],
 * [5,8,9]
 * ]
 * <p>
 * 示例1
 * 输入
 * {1,2},1
 * 返回值
 * []
 * <p>
 * 示例2
 * 输入
 * {1,2},3
 * 返回值
 * [[1,2]]
 */
public class SumRootToLeafPathEqualsNumbers {

    /**
     * 参考：通过代码榜榜首代码
     * 基于回溯和递归
     *
     * @param root TreeNode类
     * @param sum  int整型
     * @return int整型ArrayList<ArrayList <>>
     */
    public ArrayList<ArrayList<Integer>> pathSum2(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), root, sum);
        return ans;
    }

    private void dfs(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp, TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        // 这表示在最后的根节点了
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                temp.add(root.val);
                ans.add(new ArrayList<>(temp));
                temp.remove(temp.size() - 1);
            }
        } else {
            // 添加当前节点的值
            temp.add(root.val);
            // 计算当前节点的左子节点，注意 sum - root.val
            dfs(ans, temp, root.left, sum - root.val);
            // 计算当前节点的右子节点，注意 sum - root.val
            dfs(ans, temp, root.right, sum - root.val);
            // 计算玩当前节点的所有子节点，然后删除当前节点的值
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 最后一个用例跑不过，还未找到原因
     */
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<Integer> rootKey = new ArrayList<>();
        Map<ArrayList<Integer>, Integer> map = new HashMap<>();
        map.put(rootKey, 0);

        dfs(rootKey, root, map, true);

        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (Map.Entry<ArrayList<Integer>, Integer> entry : map.entrySet()) {
            if (entry.getValue() == sum && !entry.getKey().isEmpty()) {
                ans.add(entry.getKey());
            }
        }

        return ans;
    }

    private void dfs(ArrayList<Integer> rootKey, TreeNode root, Map<ArrayList<Integer>, Integer> map, boolean canRemove) {
        if (root == null) {
            return;
        }

        int sum = map.get(rootKey);
        sum = sum + root.val;

        ArrayList<Integer> newRootKey = new ArrayList<>(rootKey);
        newRootKey.add(root.val);

        if (canRemove) {
            map.remove(rootKey);
        }
        map.put(newRootKey, sum);

        if (root.left != null) {
            dfs(newRootKey, root.left, map, root.right == null);
        }

        if (root.right != null) {
            dfs(newRootKey, root.right, map, true);
        }

    }
}

/*

[0,-1,0,-1,0,1,0,0,-1,0,-1,1,1,1],
[0,-1,0,-1,0,1,0,0,-1,0,-1,1,1,1,0,0],

[0,-1,0,-1,0,1,-1,0,1,-1,-1,0,1,1,-1,1,1,0,0,0],
[0,-1,0,-1,0,1,-1,0,1,-1,-1,0,1,1,-1,1,1,0,0],
 */
