package Algorithm.leetcode.leetcode.editor.cn;
//给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
// 
//
// 若这两个字符串没有公共子序列，则返回 0。 
//
// 
//
// 示例 1: 
//
// 输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace"，它的长度为 3。
// 
//
// 示例 2: 
//
// 输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc"，它的长度为 3。
// 
//
// 示例 3: 
//
// 输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= text1.length <= 1000 
// 1 <= text2.length <= 1000 
// 输入的字符串只含有小写英文字符。 
// 
// Related Topics 动态规划 
// 👍 284 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class longest-common-subsequence {
    // 题目链接：https://leetcode-cn.com/problems/longest-common-subsequence/
    public int longestCommonSubsequence(String text1, String text2) {
        return func2(text1, text2);
    }

    /**
     * 思路二：动态规划简单版本
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return 最长公共子序列的长度
     */
    private int func2(String text1, String text2) {
        // 将两个字符串转化为数组，便于后面的遍历
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        // 得到两个字符串的长度
        int length1 = text1.length() + 1;
        int length2 = text2.length() + 1;

        // 定义动态规划状态集
        int[][] dp = new int[length1][length2];

        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                // 1.
                // 如果当前两个字符串目前为止最后一个字符相等，那么我只需要知道他们两个的子序列的公共长度，再加1即可
                // 比如 ****A 和 %%%%%A,A和A相等了，那么只需要看****和%%%%的最长公共子序列是多少然后加上1即可
                // 2.
                // 否则，加入现在是****A，%%%%B，就要看****A和%%%%的公共子序列长度与****和%%%%B的最大值是多少
                if (t1[i - 1] == t2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[length1 - 1][length2 - 1];
    }

    /**
     * 思路一：动态规划复杂版本
     * @param text1 第一个字符串
     * @param text2 第二个字符串
     * @return 最长公共子序列的长度
     */
    private int func1(String text1, String text2) {
        // 将两个字符串转化为数组，便于后面的遍历
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();

        // 得到两个字符串的长度
        int length1 = text1.length();
        int length2 = text2.length();

        // 用一个二维数组来存储结果
        int[][] res = new int[length1][length2];

        // 初始化res的第一行
        for (int i = 0; i < length2; i++) {
            if (i > 0 && res[0][i - 1] == 1) {
                res[0][i] = 1;
                continue;
            }

            if (t2[i] == t1[0]) {
                res[0][i] = 1;
            }
        }

        // 初始化res的第一列
        for (int i = 0; i < length1; i++) {
            if (i > 0 && res[i - 1][0] == 1) {
                res[i][0] = 1;
                continue;
            }

            if (t1[i] == t2[0]) {
                res[i][0] = 1;
            }
        }

        // 开始完善结果集
        for (int i = 1; i < length1; i++) {
            for (int j = 1; j < length2; j++) {
                // 1.
                // 如果当前两个字符串目前为止最后一个字符相等，那么我只需要知道他们两个的子序列的公共长度，再加1即可
                // 比如 ****A 和 %%%%%A,A和A相等了，那么只需要看****和%%%%的最长公共子序列是多少然后加上1即可
                // 2.
                // 否则，加入现在是****A，%%%%B，就要看****A和%%%%的公共子序列长度与****和%%%%B的最大值是多少
                if (t1[i] == t2[j]) {
                    res[i][j] = res[i - 1][j - 1] + 1;
                } else {
                    res[i][j] = Math.max(res[i][j - 1], res[i - 1][j]);
                }
            }
        }

        return res[length1 - 1][length2 - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
