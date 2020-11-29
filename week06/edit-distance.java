package Algorithm.leetcode.leetcode.editor.cn;
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 
// 👍 1271 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/edit-distance/
    public int minDistance(String word1, String word2) {
        // 时间复杂度：O(MN),两层for循环
        // 空间复杂度：O(MN),即为两个字符串长度的乘积
        return func1(word1, word2);
    }

    /**
     * 思路一：动态规划
     *
     * @param word1 字符串1
     * @param word2 字符串2
     * @return 最小编辑距离
     */
    private int func1(String word1, String word2) {
        // 1. 找重复性。其实重复性很好找。可以借鉴最长公共子序列这个题目的思路(1143)。如果当前下标指向的Word1，Word2所在字符相等。
        //  那么前一个的编辑距离就是当前的编辑距离。否则，按替换，插入，删除三种操作来看
        // 2. 定义状态数组：dp[i][j],表示字符串1从[0,i)转化成字符串[0,j)所需要的的编辑距离
        // 3. DP方程：当word1[i] == word2[j]。当前的dp[i][j] = dp[i - 1][j - 1];
        //           当word1[i] != word2[j]。可以考虑三种情况。增加，删除，替换。
        //           如果将word1增加一个word2当前的word2[j]字符，那么他们最后一个字符就相等了，那么dp[i][j] = dp[i][j - 1] + 1
        //           如果将word1最后一个字符删除，那么dp[i][j] = dp[i - 1][j] + 1
        //           如果将word1最后一个字符替换成word2最后一个字符，dp[i][j] = dp[i - 1][j - 1] + 1

        // 得到两个字符串的长度
        int len1 = word1.length();
        int len2 = word2.length();

        // 申请dp数组，多开一行是为了保存边界条件，防止不必要的麻烦，动态规划中很常见
        int[][] dp = new int[len1 + 1][len2 + 1];

        // 初始化dp数组，考虑到空字符串的情况，其实空字符串与另一个字符串的编辑距离就是另一个字符串的长度
        // 当word2的长度为0时，将word1的字符全部删除即可
        for (int i = 1; i <= len1; i++) {
            // 有多少个字符就删多少个字符
            dp[i][0] = i;
        }

        // 当word1的长度为0时，插入所有的word2字符即可
        for (int i = 1; i <= len2; i++) {
            // 有多少个字符就插入多少个字符
            dp[0][i] = i;
        }

        // 将字符串转化为字符数组
        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();

        // 循环进行DP
        for (int i = 1; i <= c1.length; i++) {
            for (int j = 1; j <= c2.length; j++) {
                // 如果当前i和j所标识的两个字符串的最后一个相等，那么编辑距离就是前一个的距离即可
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }

        return dp[len1][len2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
