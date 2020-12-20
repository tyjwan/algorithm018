package Algorithm.leetcode.leetcode.editor.cn;
//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 3020 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        // 枚举回文中心,超时，自己的版本
//        return func1(s);

        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        String res = s.substring(0, 1);
        // 中心位置枚举到 len - 2 即可
        for (int i = 0; i < len - 1; i++) {
            String oddStr = centerSpread(s, i, i);
            String evenStr = centerSpread(s, i, i + 1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > maxLen) {
                maxLen = maxLenStr.length();
                res = maxLenStr;
            }
        }
        return res;

    }

    private String centerSpread(String s, int left, int right) {
        // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
        // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
        int len = s.length();
        int i = left;
        int j = right;
        while (i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        // 这里要小心，跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，因此不能取 i，不能取 j
        return s.substring(i + 1, j);
    }

    private String func1(String s) {
        int mid = 0;
        char[] sArray = s.toCharArray();
        int len = s.length();
        String res = "";

        while (mid < len) {
            String temp = "";
            int left = mid - 1, right = mid + 1;
            while (left >= 0 && right < len) {
                if (sArray[left] == sArray[right]) {
                    temp = s.substring(left, right);
                    left--;
                    right++;
                } else {
                    break;
                }
            }
            res = temp.length() > res.length() ? temp : res;

            left = mid;
            right = mid + 1;
            while (left >= 0 && right < len) {
                if (sArray[left] == sArray[right]) {
                    temp = s.substring(left, right);
                    left--;
                    right++;
                } else {
                    break;
                }
            }

            res = temp.length() > res.length() ? temp : res;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
