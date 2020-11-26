package Algorithm.leetcode.leetcode.editor.cn;
//给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务
//都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。 
//
// 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。 
//
// 你需要计算完成所有任务所需要的最短时间。 
//
// 
//
// 示例 ： 
//
// 输入：tasks = ["A","A","A","B","B","B"], n = 2
//输出：8
//解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
//     在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。 
//
// 
//
// 提示： 
//
// 
// 任务的总个数为 [1, 10000]。 
// n 的取值范围为 [0, 100]。 
// 
// Related Topics 贪心算法 队列 数组 
// 👍 429 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 题目链接：https://leetcode-cn.com/problems/task-scheduler/
    public int leastInterval(char[] tasks, int n) {
        // 时间复杂度：O(time),因为每个任务都安排了时间，所以最终的答案就是时间复杂度
        // 空间复杂度：O(N), 申请了一个优先队列
        return func1(tasks, n);
    }

    /**
     * 思路一：优先队列
     *
     * @param tasks 任务数组
     * @param n 冷却时间
     * @return 执行时间
     */
    private int func1(char[] tasks, int n) {
        // 直观分析，应该让数量多的任务先执行，我们用一个优先队列来模拟

        // 因为任务用字母表示，而因为字母只有26个，所以申请一个26大小的数组即可，将字母映射为数字
        int[] map = new int[26];
        // 遍历tasks，初始化map数组
        for (char c : tasks) {
            map[c - 'A']++;
        }

        // 申请一个优先队列, 并且是大根堆，因为之前分析了，数量多的任务应该先执行
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());

        // 遍历map数组，将映射为数字的任务加入到优先队列中
        for (int i : map) {
            if (i > 0) {
                queue.offer(i);
            }
        }

        // 初始化完成任务所需要的的时间
        int time = 0;

        // 当队列非空的时候，证明任务没做完，循环去做任务
        while (! queue.isEmpty()) {
            // 定义一个变量，用于控制冷却时间
            int i = 0;
            // 定义一个列表用以记录每个任务还剩下的次数。每次拿出一个数字，我们都将其减一。这样用来代表每个任务还剩下的次数
            List<Integer> taskTimes = new ArrayList<>();
            // 当i小于冷却时间时，继续进入以下循环
            while (i <= n) {
                // 如果队列不为空
                if (!queue.isEmpty()) {
                    // 如果队头元素大于1
                    if (queue.peek() > 1) {
                        // 将其减一再加入临时的一个任务列表里，代表这个任务数量被减一了
                        taskTimes.add(queue.poll() - 1);
                    } else {
                        queue.poll();
                    }
                }

                // 执行任务的时间加一
                ++time;
                // 当前的冷却时间也加一
                ++i;

                // 如果队列为空，并且临时任务列表也为空了，那么代表所有的任务都做完了。
                if (queue.isEmpty() && taskTimes.isEmpty()) {
                    break;
                }
            }

            // 一轮冷却时间过了，要重新新一轮的任务，将刚刚保存到临时任务列表的值再次加入到优先队列中
            for (int value : taskTimes) {
                queue.offer(value);
            }
        }

        return time;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
