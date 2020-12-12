package Algorithm.leetcode.leetcode.editor.cn;
//给出一个区间的集合，请合并所有重叠的区间。
//
// 
//
// 示例 1: 
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: intervals = [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。 
//
// 
//
// 提示： 
//
// 
// intervals[i][0] <= intervals[i][1] 
// 
// Related Topics 排序 数组 
// 👍 725 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/merge-intervals/
    public int[][] merge(int[][] intervals) {
        // 方法一：先按照每个区间的左边元素排序，这样就可以知道哪些是可以合并的区间
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 按照每个区间左边元素进行排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> merged = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            // 得到每个区间的第一个元素
            int first = intervals[i][0];
            // 得到每个区间的第二个元素
            int second = intervals[i][1];
            // 如果合并的列表长度为0或者合并列表的最后一个区间的右区间比当前区间的左区间小
            // 证明他们不可能有交集，直接在结果集中增加一个区间即可。如（3,5），（6,7）
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < first) {
                merged.add(new int[]{first, second});
            } else {
                // 否则，就要合并区间，如（3,5），（4,6）或者（3，7）（4,5）
                merged.get(merged.size() - 1)[1] = Math.max(second, merged.get(merged.size() - 1)[1]);
            }
        }

        // 将列表转化为数组后返回
        return merged.toArray(new int[merged.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
