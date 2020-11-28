package Algorithm.leetcode.leetcode.editor.cn;
//一只青蛙想要过河。 假定河流被等分为 x 个单元格，并且在每一个单元格内都有可能放有一石子（也有可能没有）。 青蛙可以跳上石头，但是不可以跳入水中。
//
// 给定石子的位置列表（用单元格序号升序表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一个石子上）。 开始时， 青蛙默认已站在第一个石子上，并可以
//假定它第一步只能跳跃一个单位（即只能从单元格1跳至单元格2）。 
//
// 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。 
//
//
// 请注意： 
//
// 
// 石子的数量 ≥ 2 且 < 1100； 
// 每一个石子的位置序号都是一个非负整数，且其 < 231； 
// 第一个石子的位置永远是0。 
// 
//
// 示例 1: 
//
// 
//[0,1,3,5,6,8,12,17]
//
//总共有8个石子。
//第一个石子处于序号为0的单元格的位置, 第二个石子处于序号为1的单元格的位置,
//第三个石子在序号为3的单元格的位置， 以此定义整个数组...
//最后一个石子处于序号为17的单元格的位置。
//
//返回 true。即青蛙可以成功过河，按照如下方案跳跃： 
//跳1个单位到第2块石子, 然后跳2个单位到第3块石子, 接着 
//跳2个单位到第4块石子, 然后跳3个单位到第6块石子, 
//跳4个单位到第7块石子, 最后，跳5个单位到第8个石子（即最后一块石子）。
// 
//
// 示例 2: 
//
// 
//[0,1,2,3,4,8,9,11]
//
//返回 false。青蛙没有办法过河。 
//这是因为第5和第6个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
// 
// Related Topics 动态规划 
// 👍 127 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/frog-jump/
    public boolean canCross(int[] stones) {
        // 时间复杂度：O(N^2)
        // 空间复杂度：O(N^2), hashmap最大为O(N^2),平均应该就是O(N)
        return func1(stones);
    }

    /**
     * 思路一：动态规划
     *
     * @param stones 石头位置数组
     * @return 结果
     */
    private boolean func1(int[] stones) {
        // 1. 找重复性：每个位置能够跳跃的size只有三种，每次跳跃看能不能跳到石头上（将石头位置存储到hashmap中即可）
        //    如果能跳到石头上，就更新这块石头对应的跳跃size。hash的key即为石头位置，value即为当前石头位置能够跳跃的
        //    步数的集合。最终看最后一个位置的石头对应的value是否为空，就可以知道是否能够跳过去

        // 如果第二个石头的位置不是1，那肯定跳不过去，因为第一个石头只能跳1步
        if (stones[1] != 1) {
            return false;
        }

        // 申请一个hashmap
        Map<Integer, Set<Integer>> map = new HashMap();
        // 初始化map
        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], new HashSet<>());
        }
        map.get(1).add(1);

        // 开始循环看是否能够跳跃到最后一个石头
        int len = stones.length;
        for (int i = 1; i < stones.length; i++) {
            // 获取当前位置能够跳跃的步数集合
            Set<Integer> jumpSize = map.get(stones[i]);
            // 遍历进行跳跃
            for (int jump : jumpSize) {
                // 对于每个jump步数，都可以有三种选择方式进行跳跃
                for (int step = jump - 1; step <= jump + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }

        return map.get(stones[len - 1]).size() > 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
