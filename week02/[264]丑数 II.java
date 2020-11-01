package Algorithm.leetcode.leetcode.editor.cn;
//编写一个程序，找出第 n 个丑数。
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划 
// 👍 417 👎 0


import java.util.HashSet;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class 丑数 {
    // 题目链接：https://leetcode-cn.com/problems/ugly-number-ii/
    public static Ugly ugly = new Ugly();
    public int nthUglyNumber(int n) {
        return ugly.nums[n - 1];
    }
}

class Ugly {
    // 用于存储结果的数组
    public int[] nums = new int[1690];

    public Ugly() {
        // 用hashSet存储已经计算过的丑数，避免重复计算带来的消耗
        HashSet<Long> hashSet = new HashSet<>();
        // 申请一个基于优先队列的小顶堆
        PriorityQueue<Long> queue = new PriorityQueue<>();
        // 定义一个因子数组，用于之后将堆中元素弹出然后*因子数组中的每个元素，目的是为了找之后的丑数
        int[] element = new int[] {2, 3, 5};
        // 定义两个变量用作存储新旧丑数
        long oldNum = 1, newNum = 0;
        // 将第一个丑数加入到hashset以及优先队列中
        hashSet.add(oldNum);
        queue.offer(oldNum);

        for (int i = 0; i < 1690; i++) {
            // 当前堆中poll出来的一定是当前最小的丑数，也是第i大的丑数（因为是小顶堆）
            oldNum = queue.poll();
            // 将第i大的丑数赋值给nums[i]
            nums[i] = (int) oldNum;

            for (int j = 0; j < element.length; j++) {
                // 计算新的丑数
                newNum = oldNum * element[j];
                // 如果之前没有计算过
                if (!hashSet.contains(newNum)) {
                    // 则将其加入优先队列以及哈希表
                    hashSet.add(newNum);
                    queue.offer(newNum);
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
