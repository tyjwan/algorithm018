package Algorithm.leetcode.leetcode.editor.cn;
//假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
//
// 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i
//]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。 
// 
//
// 示例 1: 
//
// 
//输入: g = [1,2,3], s = [1,1]
//输出: 1
//解释: 
//你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
//虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
//所以你应该输出1。
// 
//
// 示例 2: 
//
// 
//输入: g = [1,2], s = [1,2,3]
//输出: 2
//解释: 
//你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
//你拥有的饼干数量和尺寸都足以让所有孩子满足。
//所以你应该输出2.
// 
//
// 
//
// 提示： 
//
// 
// 1 <= g.length <= 3 * 104 
// 0 <= s.length <= 3 * 104 
// 1 <= g[i], s[j] <= 231 - 1 
// 
// Related Topics 贪心算法 
// 👍 216 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class ssign-cookies {
    // 题目链接：https://leetcode-cn.com/problems/assign-cookies/description/
    public int findContentChildren(int[] g, int[] s) {
        /**
         * 方法一：先排序加贪心
         *
         * 时间复杂度：O(glogG + slogS)，复杂度还是在O(NlogN)这个级别
         * 空间复杂度：O(logG + logS），主要是排序产生的复杂度。还是在O(logN)这个级别。
         */

        // 先对两个数组进行排序
        Arrays.sort(g);
        Arrays.sort(s);
        // 定义结果变量
        int result = 0;

        // 拿到两个数组的最后一个元素的下标，因为我们是贪心，所以从后往前，只要当前饼干能够满足，就将result+1
        int gIndex = g.length - 1;
        int sIndex = s.length - 1;

        while (gIndex >= 0 && sIndex >= 0) {
            // 如果s[i] 比 g[gLen]大，证明这块饼干能够满足当前胃口最大的孩子（因为已经排好序了）
            if (s[sIndex] >= g[gIndex]) {
                // 直接将result + 1, 并且glen --
                result += 1;
                sIndex --;
            }
            // 无论当前的饼干能不能满足当前小孩，小孩的下标都要往前移动一位
            // 能满足，往前移动很好理解
            // 不能满足，证明当前最大块的饼干都不能满足你，那我剩下的所有饼干也都不能满足你，那我就不考虑你了，去找其他能够满足的。
            gIndex --;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
