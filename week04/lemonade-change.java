package Algorithm.leetcode.leetcode.editor.cn;
//在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
//
// 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。 
//
// 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。 
//
// 注意，一开始你手头没有任何零钱。 
//
// 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。 
//
// 示例 1： 
//
// 输入：[5,5,5,10,20]
//输出：true
//解释：
//前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
//第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
//第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
//由于所有客户都得到了正确的找零，所以我们输出 true。
// 
//
// 示例 2： 
//
// 输入：[5,5,10]
//输出：true
// 
//
// 示例 3： 
//
// 输入：[10,10]
//输出：false
// 
//
// 示例 4： 
//
// 输入：[5,5,10,10,20]
//输出：false
//解释：
//前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
//对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
//对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
//由于不是每位顾客都得到了正确的找零，所以答案是 false。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= bills.length <= 10000 
// bills[i] 不是 5 就是 10 或是 20 
// 
// Related Topics 贪心算法 
// 👍 135 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class lemonade-change {
    // 题目链接：https://leetcode-cn.com/problems/lemonade-change/description/
    public boolean lemonadeChange(int[] bills) {
        /**
         * 方法一：贪心算法
         *
         * 时间复杂度：O(N), 只有一层for循环。
         * 空间复杂度：O(1), 没有用到额外空间。
         */

        /**
         * 为什么这个题目而言是可以用贪心的？
         * 当您收到5或10的账单时，您只有一个选项：要么不返回（当收到5）要么返回5（当收到10）的账单。
         * 但是当你收到20的账单时，你有两个选择：退还一个5元账单和一个10元账单，或者只返回3个 5元账单。
         * 正确的想法是前者更好，但为什么呢？ 答案是10元账单只有在你收到20元账单时才能在退还时使用，
         * 但是当你收到20张账单和10张账单时，你都可以退还5元账单。所以说， 在某种程度上， 5元账单比15元账单更“珍贵” 。
         */

        // 用five记录当前我们拥有的5元的数量，ten记录我们当前拥有的10元的数量
        int five = 0, ten = 0;

        // 遍历即可
        for (int bill : bills) {
            // 如果当前客户给我们的是5元面值的钱，那么我们不用找零，将拥有的5元的钱的数量加一即可
            // 如果当前客户给我们的是10元面值的钱，那么我们要找他5元，所以如果我们拥有的5元的钱的数量为0的，结果就为false。
            // 如果当前客户给我们的是20元面值的钱，那么我们有两种方案，第一，找给他一张10元，一张5元。第二，找给他三张5元。如果都没有直接返回false
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                if (five != 0 && ten != 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }

        // 遍历完之后还没有问题，返回true即可
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)