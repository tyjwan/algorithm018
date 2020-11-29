package Algorithm.leetcode.leetcode.editor.cn;
//给定一个正整数 n，返回长度为 n 的所有可被视为可奖励的出勤记录的数量。 答案可能非常大，你只需返回结果mod 109 + 7的值。
//
// 学生出勤记录是只包含以下三个字符的字符串： 
//
// 
// 'A' : Absent，缺勤 
// 'L' : Late，迟到 
// 'P' : Present，到场 
// 
//
// 如果记录不包含多于一个'A'（缺勤）或超过两个连续的'L'（迟到），则该记录被视为可奖励的。 
//
// 示例 1: 
//
// 
//输入: n = 2
//输出: 8 
//解释：
//有8个长度为2的记录将被视为可奖励：
//"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//只有"AA"不会被视为可奖励，因为缺勤次数超过一次。 
//
// 注意：n 的值不会超过100000。 
// Related Topics 动态规划 
// 👍 111 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int count,M=1000000007;
    // 题目链接：https://leetcode-cn.com/problems/student-attendance-record-ii/
    public int checkRecord(int n) {
        count = 0;
        gen("", n);
        return count;
    }

    public void gen(String s, int n) {
        if (n == 0 && checkRecord(s)) {
            count=(count+1)%M;
        } else if (n > 0) {
            gen(s + "A", n - 1);
            gen(s + "P", n - 1);
            gen(s + "L", n - 1);
        }
    }
    public boolean checkRecord(String s) {
        int count = 0;
        for (int i = 0; i < s.length() && count < 2; i++) {
            if (s.charAt(i) == 'A') {
                count++;
            }
        }
        return s.length() > 0 && count < 2 && s.indexOf("LLL") < 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
