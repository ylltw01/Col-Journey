package com.sl.coljourney.algorithm;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/letter-case-permutation/
 * <p>
 * 784. 字母大小写全排列
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例：
 * 输入：S = "a1b2"
 * 输出：["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入：S = "3z4"
 * 输出：["3z4", "3Z4"]
 * <p>
 * 输入：S = "12345"
 * 输出：["12345"]
 * <p>
 * 提示：
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String s) {
        LinkedList<String> queue = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            String si = String.valueOf(c);
            if (queue.isEmpty()) {
                if (si.toLowerCase().equals(si.toUpperCase())) {
                    queue.addLast(si);
                } else {
                    queue.addLast(si.toLowerCase());
                    queue.addLast(si.toUpperCase());
                }
            } else {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    String hc = queue.remove();
                    if (si.toLowerCase().equals(si.toUpperCase())) {
                        queue.addLast(hc + si);
                    } else {
                        queue.addLast(hc + si.toLowerCase());
                        queue.addLast(hc + si.toUpperCase());
                    }
                }
            }
        }

        return queue;
    }

    public static void main(String[] args) {
        LetterCasePermutation letter = new LetterCasePermutation();
        letter.letterCasePermutation("a1b2");
    }


}
