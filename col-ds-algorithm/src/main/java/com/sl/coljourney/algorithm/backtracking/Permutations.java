package com.sl.coljourney.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 全排列官方解法
 */
public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, numsList, 0);
        return result;
    }

    private void backTrack(List<List<Integer>> result, List<Integer> numsList, int frist) {
        if (frist == numsList.size()) {
            result.add(new ArrayList<>(numsList));
            return;
        }
        for (int i = frist; i < numsList.size(); i++) {
            Collections.swap(numsList, frist, i);
            backTrack(result, numsList, frist + 1);
            Collections.swap(numsList, frist, i);
        }
    }


    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = {1, 2, 3};
        List<List<Integer>> list = permutations.permute(nums);

        for (List<Integer> li : list) {
            for (Integer in :  li) {
                System.out.print(in + " ");
            }
            System.out.println();
        }
    }

}
