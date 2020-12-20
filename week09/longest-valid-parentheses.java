package Algorithm.leetcode.leetcode.editor.cn;
//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划 
// 👍 1085 👎 0


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    // 题目链接：https://leetcode-cn.com/problems/longest-valid-parentheses/
    public int longestValidParentheses(String s) {
        // 时间复杂度：O(N),一层for循环
        // 空间复杂度：O(N),用了一个栈
//        return func1(s);

        // 时间复杂度：O(N),一层for循环
        // 空间复杂度：O(N),用了一个栈
        return func2(s);

    }

    /**
     * 思路二：动态规划
     *
     * @param s 原始字符串
     * @return 最大有效括号数
     */
    private int func2(String s) {
        // 1. 找重复性。
        // 2. 定义状态数组：dp[i]表示以i结尾的字符串的最大有效括号数量
        // 3. 如果当前下标表示的字符是')',并且他的前一个是左括号。那么dp[i] = dp[i - 2] + 2
        //    反之，如果他的前一个不是左括号，而是右括号。在这个基础上
        //   如果s[i - dp[i - 1] - 1]代表的字符是左括号。如****(()())这种情况。
        //   dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2。其中dp[i - 1]指的是()()这两个括号
        //   的长度，可以看到的(()())最外面的两个括号就是 2， 而***代表的就是dp[i-dp[i-1]-2]

        // 获取字符串长度
        int length = s.length();
        // 申请dp数组
        int[] dp = new int[length];
        // 定义最大值
        int max = 0;

        // 循环dp
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = dp[Math.max(i - 2, 0)] + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + dp[Math.max(i - dp[i - 1] - 2, 0)] + 2;
                }
                max = Math.max(dp[i], max);
            }
        }

        return max;
    }

    /**
     * 思路一：栈
     *
     * @param s 原字符串
     * @return 最大有效括号数
     */
    private int func1(String s) {
        // 用栈去模拟
        // 将字符串转化为字符数组
        char[] sA = s.toCharArray();
        // 定义返回结果
        int res = 0;
        // 获取字符串长度
        int length = s.length();

        // 申请一个栈
        Deque<Integer> stack = new LinkedList<>();
        // 防止第一个放进去的有括号的情况
        stack.push(-1);

        // 遍历字符串
        for (int i = 0; i < length; i++) {
            // 如果遇到左括号，将它的下标入栈即可
            if (sA[i] == '(') {
                stack.push(i);
            } else {
                // 如果遇到右括号
                // 1. 先出栈一个元素
                stack.pop();
                // 2. 如果此时栈为空，证明出掉的是我们最开始push进去的-1，那么我们将当前右括号的下标入栈
                // 3. 如果栈不为空，证明当前右括号被匹配了。那么我们计算比较一下以前的最大值以及当前右括号下标和栈顶的差值，得出最大值即可
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    res = Math.max(res, i - stack.peek());
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
