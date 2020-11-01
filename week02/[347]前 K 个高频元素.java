package Algorithm.leetcode.leetcode.editor.cn;
//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//
// 
//
// 示例 1: 
//
// 输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。 
// 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。 
// 你可以按任意顺序返回答案。 
// 
// Related Topics 堆 哈希表 
// 👍 556 👎 0


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class 前k个高频元素 {
    // 题目地址： https://leetcode-cn.com/problems/top-k-frequent-elements/solution/
    public int[] topKFrequent(int[] nums, int k) {
        // 先用hashmap存储nums里面每个元素出现的频率。
        Map<Integer, Integer> hashMap = new HashMap<>();

        // 此循环走完之后，每个元素的次数都被统计完了
        for (int i : nums) {
            hashMap.put(i, hashMap.getOrDefault(i, 0) + 1);
        }

        // 申请一个基于堆实现的优先队列（默认小顶堆）
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });

        // 遍历hashmap
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            // 得到map中每一对键值对中对应的数字和出现次数
            int num = entry.getKey();
            int time = entry.getValue();
            // 如果优先队列满了
            if (queue.size() == k) {
                // 则判断堆顶元素的次数是否小于当前元素的次数
                if (queue.peek()[1] < time) {
                    // 将小的元素从堆中删除
                    queue.poll();
                    // 再加入新的元素
                    queue.offer(new int[] {num, time});
                }
            } else {
                // 优先队列没有满，那直接往堆中加即可
                queue.offer(new int[] {num, time});
            }
        }
        // 至此，优先队列中剩余的值就是前k个高频元素，因为是小顶堆，堆顶元素是最小的，比他更小的都在比较重被淘汰了，剩余的自然就是前k个高频元素了

        // 结果集
        int[] res = new int[k];

        // 依次遍历将优先队列中的数组中的高频元素拿出来
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll()[0];
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
