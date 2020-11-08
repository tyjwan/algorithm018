package Algorithm.leetcode.leetcode.editor.cn;
//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 982 👎 0


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class permutations {
    // 题目链接：https://leetcode-cn.com/problems/permutations/

    // 最终的结果集
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    // 存储每一个结果
    List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        /**
         * 方法一：递归回溯
         *
         * 时间复杂度: O(N * N!), N!是全排列的时间复杂度，N是每一次递归调用结果都要复制一份再加入到res结果集中
         * 空间复杂度：O(N), 存储结果集的空间复杂度为O(N), 递归调用的深度也是O(N)
         */

        // 将int数组的值存储到ArrayList里面，方便我们后面做交换操作
        for (int num : nums) {
            temp.add(num);
        }

        // 得到数组的长度
        int length = nums.length;
        // 递归调用
        dfs(length, 0);

        return res;
    }

    /**
     * 深度遍历
     *
     * @param length 数组的长度
     * @param start  当前层的未遍历边界
     */
    public void dfs(int length, int start) {
        // 递归出口
        // 如果当前的未遍历边界已经和数组长度一样，那就证明没有未遍历的元素了，我们都遍历过了，则返回即可。
        if (start == length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // 处理当前层逻辑
        for (int i = start; i < length; i++) {
            // 我们要当前层的这个元素
            Collections.swap(temp, start, i);
            // 递归调用
            dfs(length, start + 1);
            // 当前层的元素不要，那么我们要将状态恢复一下，把刚刚交换过的元素交换回来
            Collections.swap(temp, start, i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
