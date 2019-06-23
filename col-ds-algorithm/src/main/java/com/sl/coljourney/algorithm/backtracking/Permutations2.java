package com.sl.coljourney.algorithm.backtracking;

import java.util.*;

/**
 * 全排列2 去重
 */
public class Permutations2 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        Collections.sort(numsList);
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, numsList, 0);
        return result;
    }

    private void backTrack(List<List<Integer>> result, List<Integer> numsList, int frist) {
        if (frist == numsList.size()) {
            result.add(new ArrayList<>(numsList));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = frist; i < numsList.size(); i++) {
            if (set.contains(numsList.get(i))) {
                continue;
            }
            set.add(numsList.get(i));
            Collections.swap(numsList, frist, i);
            backTrack(result, numsList, frist + 1);
            Collections.swap(numsList, frist, i);
        }
    }


    public static void main(String[] args) {
        Permutations2 permutations = new Permutations2();
        int[] nums = {2, 2, 1, 1};
        List<List<Integer>> list = permutations.permuteUnique(nums);

        for (List<Integer> li : list) {
            for (Integer in :  li) {
                System.out.print(in + " ");
            }
            System.out.println();
        }
    }


}
