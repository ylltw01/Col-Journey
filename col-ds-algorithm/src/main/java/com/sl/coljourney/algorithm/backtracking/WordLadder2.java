package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/word-ladder-ii/
 * 126. 单词接龙 II
 * 难度：困难
 * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，
 * 并满足：
 * <p>
 * 每对相邻的单词之间仅有单个字母不同。
 * 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，
 * 如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 * <p>
 * 示例 1：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * <p>
 * 示例 2：
 * <p>
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 */
public class WordLadder2 {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return ans;
        }
        // 利用 BFS 得到所有的邻居节点
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        bfs(beginWord, endWord, wordList, map);
        ArrayList<String> temp = new ArrayList<>();
        // temp 用来保存当前的路径
        temp.add(beginWord);
        findLaddersHelper(beginWord, endWord, map, temp, ans);
        return ans;
    }

    private void findLaddersHelper(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
                                   ArrayList<String> temp, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        // 得到所有的下一个的节点
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<>());
        for (String neighbor : neighbors) {
            temp.add(neighbor);
            findLaddersHelper(neighbor, endWord, map, temp, ans);
            temp.remove(temp.size() - 1);

        }
    }

    public void bfs(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        boolean isFound = false;
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> subVisited = new HashSet<>();
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                // 一次性得到所有的下一个的节点
                ArrayList<String> neighbors = getNeighbors(temp, dict);
                Iterator<String> it = neighbors.iterator();//把元素导入迭代器
                while (it.hasNext()) {
                    String neighbor = it.next();
                    if (!visited.contains(neighbor)) {
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                        }
                        queue.offer(neighbor);
                        subVisited.add(neighbor);
                    }else{
                        it.remove();
                    }
                }
                map.put(temp, neighbors);
            }
            visited.addAll(subVisited);
            if (isFound) {
                break;
            }
        }
    }

    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char[] chs = node.toCharArray();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) {
                    continue;
                }
                char oldCh = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = oldCh;
            }
        }
        return res;
    }
//
//    作者：windliang
//    链接：https://leetcode-cn.com/problems/word-ladder-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-3/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    /**
     * 超时了
     */
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return ans;
        }
        List<String> path = new ArrayList<>();
        bt(beginWord, path, endWord, wordList, ans);
        return ans;
    }

    private void bt(String word, List<String> path, String endWord, List<String> wordList, List<List<String>> ans) {
        System.out.println("times");
        if (word.equals(endWord)) {
            path.add(word);
            Iterator<List<String>> iterator = ans.iterator();
            if (ans.isEmpty()) {
                ans.add(new ArrayList<>(path));
            } else {
                boolean canAdd = false;
                while (iterator.hasNext()) {
                    List<String> next = iterator.next();
                    if (path.size() == next.size()) {
                        canAdd = true;
                    } else if (path.size() < next.size()) {
                        canAdd = true;
                        iterator.remove();
                    }
                }
                if (canAdd) {
                    ans.add(new ArrayList<>(path));
                }
            }
            path.remove(word);
            return;
        }
        path.add(word);

        int diff = diffChar(word, endWord);
        if (diff == 1) {
            bt(endWord, path, endWord, wordList, ans);
        } else {
            for (String wd : wordList) {
                if (path.contains(wd) || endWord.equals(wd)) {
                    continue;
                }
                diff = diffChar(word, wd);
                if (diff == 1) {
                    bt(wd, path, endWord, wordList, ans);
                }
            }
        }
        path.remove(word);
    }

    private int diffChar(String word, String wd) {
        int diff = 0;
        char[] wdChars = wd.toCharArray();
        char[] wordChars = word.toCharArray();
        if (wdChars.length != wordChars.length) {
            return 2;
        }
        for (int i = 0; i < wdChars.length; i++) {
            if (wdChars[i] != wordChars[i]) {
                diff++;
            }
        }
        return diff;
    }

    public static void main(String[] args) {
        WordLadder2 wordLadder2 = new WordLadder2();

        int i = wordLadder2.diffChar("lest", "lose");
        System.out.println(i);

//        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
//        List<List<String>> ladders = wordLadder2.findLadders("hit", "cog", wordList);
//
//        List<String> wordList = Arrays.asList("hot", "dog", "cog", "pot", "dot");
//        List<List<String>> ladders = wordLadder2.findLadders("hot", "dog", wordList);

//        List<String> wordList = Arrays.asList("lest", "leet", "lose", "code", "lode", "robe", "lost");
//        List<List<String>> ladders = wordLadder2.findLadders("leet", "code", wordList);

        List<String> wordList = Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye");
        List<List<String>> ladders = wordLadder2.findLadders("qa", "sq", wordList);

        for (List<String> ladder : ladders) {
            System.out.println(StringUtils.join(ladder, ", "));
        }
    }
}
