package Algorithm.leetcode.leetcode.editor.cn;//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1201 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class climbing-stairs {
    // 题目链接：https://leetcode-cn.com/problems/climbing-stairs/
    public int climbStairs(int n) {
//        return func1(n);


        // 递归避免重复记录
//        return func3(n);

        // 自底向上
//        return func2(n);

        // 动态规划
        return func4(n);
    }

    /**
     * 思路四：动态规划
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(1)
     * @param n 楼梯数量
     * @return 返回有多少种跳法
     */
    private int func4(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }

        return r;
    }

    /**
     * 思路三：记录每次递归的时候得到的值，避免重复计算
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param n 楼梯数量
     * @return 返回有多少种跳法
     */
    private int func3(int n) {
        int[] a = new int[n + 1];
        return climb(n, a);
    }

    /**
     * 思路二：自底向上
     *
     * 时间复杂度：（n)
     * 空间复杂度：O(N)
     * @param n 楼梯数量
     * @return 返回有多少种跳法
     */
    private int func2(int n) {
        int[] array = new int[n + 1];
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }
        array[1] = 1;
        array[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    /**
     * 思路一：傻递归
     *
     * 时间复杂度：指数级别（2^n)
     * 空间复杂度：和递归的深度有关：logN
     */
    private int func1(int n) {
//         递归
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public int climb(int n, int[] a) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (a[n] > 0) {
            return a[n];
        }

        a[n] = climb(n - 1, a) + climb(n - 2, a);
        return a[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
