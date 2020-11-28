package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
//
// 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。 
//
// 
//
// 示例 1： 
//
// 输入："abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"
// 
//
// 示例 2： 
//
// 输入："aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa" 
//
// 
//
// 提示： 
//
// 
// 输入的字符串长度不会超过 1000 。 
// 
// Related Topics 字符串 动态规划 
// 👍 441 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public static void main(String[] args) {
//        Solution2 s= new Solution2();
//        s.countSubstrings("aaa");
//    }
    // 题目链接：https://leetcode-cn.com/problems/palindromic-substrings/
    public int countSubstrings(String s) {
        // 时间复杂度：O(N^2),两层循环
        // 空间复杂度：O(1),只用了常数级的空间
//        return func1(s);

        // 时间复杂度：O(N^2),虽然有三层循环，但实际上第二层循环最多遍历2次，所以实际上还是两层循环
        // 空间复杂度：O(1),只用了常数级的空间
        return func2(s);

    }

    /**
     * 思路二：思路一的变种，更好理解
     *
     * @param s 原字符串
     * @return 最大回文子串数
     */
    private int func2(String s) {
        // 定义结果
        int res = 0;
        // 获取字符串长度
        int len = s.length();

        int temp = 2;
        for (int i = 0; i < len; i++) {
            int right;

            if (i == len - 1) {
                temp = 1;
            }
            // 回文中心只有可能是奇数个或者偶数个
            // 如果是奇数个，那么就是一个字符作为回文中心
            // 如果是偶数个，那么就是两个字符作为回文中心
            for (int j = 0; j < temp; j++) {
                int left = i;
                right = i + j;

                while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                    --left;
                    ++right;
                    ++res;
                }
            }
        }

        return res;
    }

    /**
     * 思路一：找回文中心
     *
     * @param s 原字符串
     * @return 最大回文子串数量
     */
    private int func1(String s) {
        // 记录结果集
        int res = 0;
        // 记录长度
        int len = s.length();
        // 遍历找出所有的回文中心点
        // 这里为什么是i<2*len-1,因为你想想，你的回文子串的回文中心点最大的可能性就是在len-1位置
        // 也就是最后一个字符自己作为子串他是一个回文的。(len - 1) * 2刚好是2len - 2。*2是因为我们要
        // 筛选出奇数情况的回文中心以及偶数情况的回文中心
        for (int i = 0; i < 2 * len - 1; i++) {
            // 得出回文左中心
            int left = i / 2;
            // 得出回文右中心
            int right = left + i % 2;

            // 如果满足回文要求
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                // 左指针往左移动，右指针往右移动，结果加一
                --left;
                ++right;
                ++res;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
