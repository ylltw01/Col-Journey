package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.nowcoder.com/practice/a43a2b986ef34843ac4fdd9159b69863?tpId=117&&tqId=37739&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/permutations-ii/
 * 47. 全排列 II
 * 难度：中等
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * f(1,1,2) = f(1 + f(1, 2)) + f(2 + f(1, 1))
 * <p>
 * f(1,2,1) = f(1 + f(2, 1)) + f(2 + f(1, 1))
 * <p>
 * f(1,2,3,1) = f(1 + f(2,3,1)) + f(2 + f(1,3,1)) + f(3 + f(1,2,1))
 */
public class Permutationsii {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        List<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        recursion(ans, numList, 0);
        return ans;
    }

    private void recursion(List<List<Integer>> ans, List<Integer> numList, int idx) {
        if (idx >= numList.size() - 1) {
            ans.add(numList);
            return;
        }

        for (int i = idx; i < numList.size(); i++) {
            // 如果在之后的区间中，出现了重复的，则不回再添加，做剪枝。如：f(3 + f(1,2,1))，f(1,2,1) 中 两个 1，只计算一个
            boolean exist = false;
            for (int j = idx; j < i; j++) {
                if (numList.get(j).equals(numList.get(i))) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                // 从第二个开始交换，然后递归
                List<Integer> newNumList = new ArrayList<>(numList);
                Collections.swap(newNumList, idx, i);
                recursion(ans, newNumList, idx + 1);
            }
        }
    }

    public static void main(String[] args) {
        Permutationsii permutations = new Permutationsii();
        int[] nums = {1, 2, 1};
        List<List<Integer>> list = permutations.permuteUnique(nums);

        for (List<Integer> li : list) {
            for (Integer in : li) {
                System.out.print(in + " ");
            }
            System.out.println();
        }
    }

}

/*



 */