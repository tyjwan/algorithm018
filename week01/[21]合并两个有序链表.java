package Algorithm.leetcode.leetcode.editor.cn;
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 
//
// 示例： 
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// 
// Related Topics 链表 
// 👍 1174 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }合并两个有序链表
 */
class 合并两个有序链表 {
    // 时间复杂度， n 或者 m， 空间复杂度1. 反正时间复杂度就是n和m中比较小的那一个。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 迭代
        ListNode soldier = new ListNode(0);
        ListNode p = soldier;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        p.next = l1 == null ? l2 : l1;

        return soldier.next;

        // 递归
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2, l2.next);
            return l2;
        }
    }
}
// leetcode submit region end(Prohibit modification and deletion)
