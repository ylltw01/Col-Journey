package com.sl.coljourney.algorithm;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/group-anagrams/
 * 49. 字母异位词分组
 * 难度：中等
 * 给定一个字符串数组，将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 示例 2:
 * <p>
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * 示例 3:
 * <p>
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 */
public class GroupAnagrams {

    /**
     * 排序
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> temp = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String s = new String(chars);
            List<String> list = temp.getOrDefault(s, new ArrayList<>());
            list.add(str);
            temp.put(s, list);
        }
        return new ArrayList<>(temp.values());
    }

}
