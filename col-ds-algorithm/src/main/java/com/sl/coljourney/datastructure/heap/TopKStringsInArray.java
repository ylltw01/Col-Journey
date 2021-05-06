package com.sl.coljourney.datastructure.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.nowcoder.com/practice/fd711bdfa0e840b381d7e1b82183b3ee?tpId=117&tqId=37809&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * <p>
 * 字符串出现次数的Top K 问题
 * 题目描述
 * 给定一个字符串数组，再给定整数k，请返回出现次数前k名的字符串和对应的次数。
 * 返回的答案应该按字符串出现频率由高到低排序。如果不同的字符串有相同出现频率，按字典序排序。
 * 对于两个字符串，大小关系取决于两个字符串从左到右第一个不同字符的 ASCII 值的大小关系。
 * 比如"ah1x"小于"ahb"，"231"<”32“
 * 字符仅包含数字和字母
 * <p>
 * [要求]
 * 如果字符串数组长度为N，时间复杂度请达到O(N \log K)O(NlogK)
 * <p>
 * 示例1
 * 输入
 * ["a","b","c","b"],2
 * 返回值
 * [["b","2"],["a","1"]]
 * 说明
 * "b"出现了2次，记["b","2"]，"a"与"c"各出现1次，但是a字典序在c前面，记["a","1"]，最后返回[["b","2"],["a","1"]]
 * <p>
 * 示例2
 * 输入
 * ["123","123","231","32"],2
 * 返回值
 * [["123","2"],["231","1"]]
 * 说明
 * "123"出现了2次，记["123","2"]，"231"与"32"各出现1次，但是"231"字典序在"32"前面，记["231","1"]，最后返回[["123","2"],["231","1"]]
 * <p>
 * 示例3
 * 输入
 * ["abcd","abcd","abcd","pwb2","abcd","pwb2","p12"],3
 * 返回值
 * [["abcd","4"],["pwb2","2"],["p12","1"]]
 * <p>
 * 解法：
 * 1. 用 Map 结构统计出各字符串出现次数
 * 2. 构建大顶堆
 * 3. 根据大顶堆，分别计算top k 的值
 */
public class TopKStringsInArray {

    public String[][] topKstrings(String[] strings, int k) {
        Map<String, Integer> strTimesMap = new HashMap<>();
        for (String s : strings) {
            if (strTimesMap.containsKey(s)) {
                strTimesMap.put(s, strTimesMap.get(s) + 1);
            } else {
                strTimesMap.put(s, 1);
            }
        }

        NodeStr[] nodes = new NodeStr[strTimesMap.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : strTimesMap.entrySet()) {
            nodes[i] = new NodeStr(entry.getKey(), entry.getValue());
            i++;
        }

        buildHeap(nodes);

        String[][] ans = new String[k][2];
        int capacity = nodes.length - 1;
        for (int j = 0; j < ans.length; j++) {
            ans[j] = new String[]{nodes[0].str, String.valueOf(nodes[0].times)};
            swap(nodes, 0, capacity);
            heapity(nodes, 0, capacity);
            capacity--;
        }
        return ans;
    }

    private void buildHeap(NodeStr[] nodes) {
        for (int i = nodes.length / 2; i >= 0; i--) {
            heapity(nodes, i, nodes.length);
        }
    }

    private void heapity(NodeStr[] nodes, int from, int capacity) {
        while (from < capacity) {
            int leftIndex = from * 2 + 1;
            if (leftIndex >= capacity) {
                break;
            }
            int maxNode = maxNode(nodes, from, leftIndex);

            int rightIndex = from * 2 + 2;
            if (rightIndex < capacity) {
                maxNode = maxNode(nodes, maxNode, rightIndex);
            }
            if (maxNode == from) {
                break;
            }

            swap(nodes, from, maxNode);
            from = maxNode;
        }
    }

    private void swap(NodeStr[] nodes, int from, int to) {
        NodeStr temp = nodes[from];
        nodes[from] = nodes[to];
        nodes[to] = temp;
    }

    private int maxNode(NodeStr[] nodes, int rootIndex, int otherIndex) {
        if (otherIndex >= nodes.length) {
            return rootIndex;
        }
        NodeStr root = nodes[rootIndex];
        NodeStr other = nodes[otherIndex];
        if (root.times == other.times) {
            return root.str.compareTo(other.str) < 0 ? rootIndex : otherIndex;
        } else {
            return root.times > other.times ? rootIndex : otherIndex;
        }
    }

    static class NodeStr {
        String str;
        int times;

        public NodeStr(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static void main(String[] args) {
//        String[] str = {"a", "b", "c", "b"};
        String[] str = {"abcd", "abcd", "abcd", "pwb2", "abcd", "pwb2", "p12"};
//        String[] str = {"123","123","231","32"};
        TopKStringsInArray top = new TopKStringsInArray();
//        String[][] strings = top.topKstrings(str, 2);
        String[][] strings = top.topKstrings(str, 3);
//        String[][] strings = top.topKstrings(str, 2);

        for (String[] string : strings) {
            for (String s : string) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}

