package com.sl.coljourney.datastructure.array;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/69f4e5b7ad284a478777cb2a17fb5e6a?tpId=117&tqId=37737&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * https://leetcode-cn.com/problems/merge-intervals/
 * 56. 合并区间
 * 难度：中等
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 示例1
 * 输入
 * [[10,30],[20,60],[80,100],[150,180]]
 * 返回值
 * [[10,60],[80,100],[150,180]]
 */
public class MergeIntervals {

    /**
     * leetcode 的题
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] pre = merged.get(merged.size() - 1);
            if (intervals[i][0] <= pre[1]) {
                int[] merge = new int[2];
                merge[0] = pre[0];
                merge[1] = Math.max(pre[1], intervals[i][1]);
                merged.remove(merged.size() - 1);
                merged.add(merge);
            } else {
                merged.add(intervals[i]);
            }
        }

        return merged.toArray(new int[merged.size()][2]);
    }

    /**
     * 牛客的题
     */
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }

        intervals.sort(Comparator.comparingInt(o -> o.start));

        ArrayList<Interval> merged = new ArrayList<>();
        merged.add(intervals.get(0));
        for (int i = 1; i < intervals.size(); i++) {
            Interval pre = merged.get(merged.size() - 1);
            if (intervals.get(i).start <= pre.end) {
                Interval merge = new Interval(pre.start, Math.max(pre.end, intervals.get(i).end));
                merged.remove(merged.size() - 1);
                merged.add(merge);
            } else {
                merged.add(intervals.get(i));
            }
        }
        return merged;
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

}