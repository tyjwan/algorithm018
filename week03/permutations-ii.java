package Algorithm.leetcode.leetcode.editor.cn;
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 518 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class permutations-ii {
    // 题目链接：https://leetcode-cn.com/problems/permutations-ii/

    // 定义结果集
    List<List<Integer>> res = new ArrayList<>();
    // 定义每一个符合题意的结果
    List<Integer> temp = new ArrayList<>();
    // 定义visit数组
    boolean[] visit;
    public List<List<Integer>> permuteUnique(int[] nums) {
        /**
         * 方法一：递归回溯
         *
         * 时间复杂度: O(N * N!), N!是全排列的时间复杂度，N是每一次递归调用结果都要复制一份再加入到res结果集中
         * 空间复杂度：O(N), 存储结果集的空间复杂度为O(N), 递归调用的深度也是O(N)
         */

        // 初始化visit数组，长度为nums数组的长度即可
        visit = new boolean[nums.length];
        // 对数组进行排序，这样的好处是我们能自定义规则说前面的还没访问不能访问后面的（针对有相同数字的数组而言），这样能有效避免重复结果
        Arrays.sort(nums);
        // 递归调用
        dfs(nums, 0);
        return res;
    }

    /**
     * 深度优先遍历
     *
     * @param nums 待全排列的数组
     * @param index 全排列已经选了多少个元素了
     */
    public void dfs(int[] nums, int index) {
        // 递归出口
        // 如果index已经等于数组的长度，证明某一个全排列的结果已经得到了，加入到结果集，返回即可
        if (index == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        // 对数组进行循环，循环里面进行递归调用
        for (int i = 0; i < nums.length; i++) {
            // 如果这个元素已经visit过，肯定是在上面的层次访问过了。也就是已经加入过temp结果中，那么直接跳过这次循环
            // 如果当前元素和前一个元素是一样的，并且前一个没有访问过，那这个也不能让他访问，否则结果集中就会出现重复的结果
            if (visit[i] || (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1])) {
                continue;
            }

            // 当前元素要加入进来,即将它加入到结果中，并且置标记数组为true
            temp.add(nums[i]);
            visit[i] = true;
            // 递归调用
            dfs(nums, index + 1);

            // 当前元素不加入进来，即将它remove掉，并且置标记数组为false
            visit[i] = false;
            temp.remove(index);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
