package com.sl.coljourney.algorithm.backtracking;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/reconstruct-itinerary/
 * 332. 重新安排行程
 * 难度：中等
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
 * <p>
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * <p>
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * <p>
 * 示例 1：
 * <p>
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 * <p>
 * 解题：回溯
 * 1. 将所有行程，构建，key -> List[] 的 map 结构，因为可能回对应多个目的地，且目的地可能是会重复的。
 * 2. 从 JFK 机场开始，然后获取 JFK 的 List[] 最小的机场，然后 remove 掉已经处理过的机场，且继续递归。
 * 3. 如果发现获取到的 List[] 是空，则说明当前路线是错误的，那么开始回溯，且恢复刚刚 remove 的。以及 remove 掉添加到 ans 中的路线。
 * 4. 最终遍历完所有的结束。
 */
public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();

        // 开始构建 key -> List[] 的机场结构
        for (List<String> ticket : tickets) {
            List<String> list = map.getOrDefault(ticket.get(0), new ArrayList<>());
            list.add(ticket.get(1));
            map.put(ticket.get(0), list);
        }
        // 回溯
        backtrack(tickets, ans, "JFK", map);
        return ans;
    }

    private boolean backtrack(List<List<String>> tickets, List<String> ans, String start, Map<String, List<String>> map) {
        // 先添加到最终路线
        ans.add(start);
        // 递归退出条件，当最终的路线与输入的路程长度 +1，表示已经完毕退出
        if (ans.size() == tickets.size() + 1) {
            return true;
        }
        // 获取对应的目标机场 list
        List<String> list = map.get(start);
        // 回溯条件，获取到的机场 list 为空或者长度为空，则回溯
        if (list == null || list.isEmpty()) {
            return false;
        }

        // 获取当前长度
        int size = list.size();
        // 用来存储当前的目的地 list 中，已经尝试过的机场
        Set<String> notContains = new HashSet<>();
        for (int i = 0; i < size; i++) {
            // 获取当前的目的地 list 中，字母顺序最小的机场，排除尝试过的
            String min = minString(list, notContains);
            // 添加已经尝试过得
            notContains.add(min);

            // 这里从 Map 中 list 中的移除当前尝试的机场，如果移除后长度为 0，则移除 key
            list.remove(min);
            if (list.size() == 0) {
                map.remove(start);
            }
            boolean recursion = backtrack(tickets, ans, min, map);
            // 注意这里，只有当最终的结果为 true 的时候(即执行完毕)，才会返回true。否则还原并返回 false
            if (recursion) {
                return true;
            }
            // 如果当前值尝试失败，需要进行回溯的话，则需要还原。还原当前的目的地 list，还原最终路线
            list.add(min);
            map.put(start, list);
            ans.remove(ans.size() - 1);
        }
        return false;
    }

    private String minString(List<String> list, Set<String> notContains) {
        String min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            String s = list.get(i);
            if (notContains.contains(s)) {
                continue;
            }
            if (min == null || min.compareTo(s) > 0) {
                min = s;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("EZE", "TIA"));
        tickets.add(Arrays.asList("EZE", "HBA"));
        tickets.add(Arrays.asList("AXA", "TIA"));
        tickets.add(Arrays.asList("JFK", "AXA"));
        tickets.add(Arrays.asList("ANU", "JFK"));
        tickets.add(Arrays.asList("ADL", "ANU"));
        tickets.add(Arrays.asList("TIA", "AUA"));
        tickets.add(Arrays.asList("ANU", "AUA"));
        tickets.add(Arrays.asList("ADL", "EZE"));
        tickets.add(Arrays.asList("ADL", "EZE"));
        tickets.add(Arrays.asList("EZE", "ADL"));
        tickets.add(Arrays.asList("AXA", "EZE"));
        tickets.add(Arrays.asList("AUA", "AXA"));
        tickets.add(Arrays.asList("JFK", "AXA"));
        tickets.add(Arrays.asList("AXA", "AUA"));
        tickets.add(Arrays.asList("AUA", "ADL"));
        tickets.add(Arrays.asList("ANU", "EZE"));
        tickets.add(Arrays.asList("TIA", "ADL"));
        tickets.add(Arrays.asList("EZE", "ANU"));
        tickets.add(Arrays.asList("AUA", "ANU"));

        ReconstructItinerary itinerary = new ReconstructItinerary();
        List<String> ans = itinerary.findItinerary(tickets);
        System.out.println(StringUtils.join(ans, ", "));
    }


}
/*
[["EZE","TIA"],["EZE","HBA"],["AXA","TIA"],["JFK","AXA"],["ANU","JFK"],["ADL","ANU"],
["TIA","AUA"],["ANU","AUA"],["ADL","EZE"],["ADL","EZE"],["EZE","ADL"],["AXA","EZE"],
["AUA","AXA"],["JFK","AXA"],["AXA","AUA"],["AUA","ADL"],["ANU","EZE"],["TIA","ADL"],
["EZE","ANU"],["AUA","ANU"]]

EZE, [TIA, HBA, ADL, ANU]
AXA, [TIA, EZE, AUA]
JFK, [AXA, AXA]
ANU, [JFK, AUA, EZE]
ADL, [ANU, EZE, EZE]
TIA, [AUA, ADL]
AUA, [AXA, ADL, ANU]


EZE, [HBA, ADL]
AXA, [TIA, AUA]


ADL, [EZE, EZE]
TIA, [AUA, ADL]
AUA, [AXA]
["JFK","AXA","AUA","ADL","ANU","AUA","ANU","EZE","ADL","EZE","ANU","JFK","AXA","EZE","TIA","ADL","EZE"]
["JFK","AXA","AUA","ADL","ANU","AUA","ANU","EZE","ADL","EZE","ANU","JFK","AXA","EZE","TIA","AUA","AXA","TIA","ADL","EZE","HBA"]

[["EZE","AXA"],["TIA","ANU"],["ANU","JFK"],["JFK","ANU"],["ANU","EZE"],["TIA","ANU"],["AXA","TIA"],["TIA","JFK"],["ANU","TIA"],["JFK","TIA"]]

EZE, [AXA]
TIA, [ANU, ANU, JFK]
ANU, [JFK, EZE, TIA]
JFK, [ANU, TIA]
AXA, [TIA]


[["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]

JFK, [KUL, NRT]
NRT, [JFK]

JFK, [NRT]
NRT, [JFK]


JFK, [SFO, ATL]

SFO, [ATL]
ATL, [JFK]
ATL, [SFO]

                                            JFK
                               JFK ->  SFO,     JFK -> ATL

                                                   JFK ->  ATL ->  JFK

                                                     JFK -> ATL ->  JFK  -> SFO -> ATL -> SFO






 */