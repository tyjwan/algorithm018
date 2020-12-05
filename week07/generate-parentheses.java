package Algorithm.leetcode.leetcode.editor.cn;
//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1325 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class 括号生成 {
    // 题目链接：https://leetcode-cn.com/problems/generate-parentheses/

    public List<String> result = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        helper2(0, 0, n, "");
        return result;
    }

    public void helper2(int left, int right, int n, String s) {
        if (left == right) {
            result.add(s);
            return;
        }

        if (left < n) {
            helper2(left + 1, right, n, s + "(");
        }

        if (right < left) {
            helper2(left, right + 1, n, s + ")");
        }
    }




    public void helper(int left, int right, int n, String str) {
        if (left == n && right == n) {
            result.add(str);
            return;
        }

        if (left < n) {
            helper(left + 1, right, n, str + "(");
        }

        if (right < left) {
            helper(left, right + 1, n, str + ")");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
