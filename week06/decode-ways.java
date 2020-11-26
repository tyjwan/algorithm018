package Algorithm.leetcode.leetcode.editor.cn;
//一条包含字母 A-Z 的消息通过以下方式进行了编码：
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 题目数据保证答案肯定是一个 32 位的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：s = "1"
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：s = "2"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 564 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/decode-ways/
    public int numDecodings(String s) {
        // 1. 找重复性。当前字符串的解码方式分为几种情况。第一种当前s[i]为0， 如果s[i - 1]为1，或者2，证明最后两位可以拼成一个字母。那么以当前字符结尾的字符串的编码方式就
        // 是dp[i - 2].否则就是0。第二种如果当前s[i]不为0，那么至少dp[i] = dp[i - 1]，也就是最后一位自己单独组。然后如果倒数第二位和倒数第一位组合的数字在[11, 26]之间，
        // 证明最后两位也可以组成一组成为一个字母，这种情况下dp[i]要再加一个dp[i - 2]
        // 2. 定义状态数组：dp[i] ，其中dp[i]表示以s[i]结尾的字符串有多少种编码方式
        // 3. DP方程：if s[i] = 0 && s[i-1] = 1 || 2, dp[i] = dp[i - 2].
        // if s[i] != 0 dp[i] = dp[i - 1], if 11 <= s[i - 1]s[i] <= 26, dp[i] += dp[i - 2]
        // 实际上上面的思路还可以合并，具体在代码里体现

        // 边界条件
        if (s.charAt(0) == '0') {
            return 0;
        }

        // 将字符串转化为字符数组
        char[] sArray = s.toCharArray();
        // 得到字符串的长度
        int length = s.length();

        // 申请dp数组
        int[] dp = new int[length];
        // 初始化dp数组
        dp[0] = 1;

        // 循环dp
        for (int i = 1; i < sArray.length; i++) {
            // 如果最后一个字符不是0，铁定的可以单独解码出一个字母，这种情况dp[i]至少也是dp[i - 1]
            if (sArray[i] != '0') {
                dp[i] = dp[i - 1];
            }

            // 算出最后两个数的数值
            int num = 10 * (sArray[i - 1] - '0') + (sArray[i] - '0');

            // 如果最后两个数在10 - 26之间，那么证明最后两个数是可以合并在一起解码一个字母的，那么dp[i]还要加上dp[i - 2]。
            // 这里其实就把最后一个字符是0的情况包含进去了。即便你是0，也只有10,20是符合要求的，而10,20都在10-26区间
            if (num >= 10 && num <= 26) {
                // 这里如果i是1下标会越界，所以我们做一个特殊判断
                if (i == 1) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
        }

        return dp[length - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
