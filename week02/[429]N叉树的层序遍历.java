package Algorithm.leetcode.leetcode.editor.cn;
//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 110 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
// 题目地址
class N叉树的层序遍历 {
    // 题目地址：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/

    // 结果集
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> levelOrder(Node root) {
        // 判空
        if (root == null) {
            return res;
        }

        // 用队列进行层序遍历
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        // 如果队列非空，证明还有节点没有遍历完
        while (!queue.isEmpty()) {
            // 用于存储每一层的结果集
            List<Integer> temp = new ArrayList<>();
            // 当前层次的节点个数
            int size = queue.size();
            // 当当前层次还没有遍历完，则继续遍历
            while (size != 0) {
                // 出队
                Node node = queue.poll();
                // 加入当前层的结果集
                temp.add(node.val);
                // 当前层的size - 1
                size--;

                // 将当前节点的所有儿子节点加入队列中（这也就是下一层的节点了）
                queue.addAll(node.children);
            }
            // 将刚刚遍历的那一层的结果集加入到最终的结果集
            res.add(temp);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
