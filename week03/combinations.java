package Algorithm.leetcode.leetcode.editor.cn;
//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 432 👎 0


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class combinations {
    // 题目地址：https://leetcode-cn.com/problems/combinations/

    // 存储每一个可能的结果
    List<Integer> temp = new ArrayList<>();
    // 所有结果的可能的结果集
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> combine(int n, int k) {
        /** 方法一：递归
         *
         *  时间复杂度：为 O({n \choose k} * k)，前一个组合枚举的复杂度，由于每次记录答案的复杂度为 O(k)，故这里的时间复杂度为 O({n \choose k} *k k)
         *  空间复杂度：temp为O(K), 递归开辟的栈为O(N), 所以最终为O(N + K) = O(N)
         */

        // 调用自己的递归函数
        dfs(1, n, k);
        return res;
    }

    /**
     * 深度优先遍历
     *
     * @param cur 当前遍历到第几个数字了
     * @param n   数字一共多少个
     * @param k   需要从n个中选出k个
     */
    public void dfs(int cur, int n, int k) {
        // 递归出口,也是一个剪枝操作
        // 如果说当前的这种决策导致即便剩下的数字全选也打不到k个数，那么我们可以直接在这一层就放弃这种选择
        if (temp.size() + n - cur + 1 < k) {
            return;
        }

        // 递归出口
        // 如果temp的size等于k，就证明我们找到了k个数，将temp加到结果集中，然后直接返回
        if (temp.size() == k) {
            // 这里为什么要new一个，因为temp是个全局变量，你加同一个全局变量到结果集里面有可能会发生覆盖问题。
            res.add(new ArrayList<>(temp));
            return;
        }

        // 选择当前的这个数
        temp.add(cur);
        // 继续深度遍历
        dfs(cur + 1, n, k);

        // 不选则当前的这个数,为什么只用删除这一个呢，会不会想temp是个全局变量，下面层
        // 如果做了大量的add操作，temp里面会有好多值，实际上不会，地下层返回来一定都经历过remove这句话了。所以返回到你
        // 当前这一层你就只需要关注你当前层加进去的元素啦。
        temp.remove(temp.size() - 1);
        // 继续深度遍历
        dfs(cur + 1, n, k);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
