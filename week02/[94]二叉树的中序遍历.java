package Algorithm.leetcode.leetcode.editor.cn;
//给定一个二叉树，返回它的中序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表 
// 👍 716 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class 二叉树的中序遍历 {
    // 题目URL： https://leetcode-cn.com/problems/binary-tree-inorder-traversal/submissions/
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        // 方法一： 递归
        // 判空
        if (root == null) {
            return res;
        }

        // 递归遍历左子树
        inorderTraversal(root.left);
        // 访问根节点
        res.add(root.val);
        // 递归遍历右子树
        inorderTraversal(root.right);
        // 返回结果
        return res;



        // 方法二： 迭代
        // 判空
        if (root == null) {
            return res;
        }

        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;

        // node不为空代表上一个出栈元素的右孩子不为空，s非空代表栈非空。如果这两个都空，证明我们已经遍历完整棵树了
        while (node != null || !s.isEmpty()) {
            // 往左边找到最左边的节点
            while (node != null) {
                // 因为栈是先进后出结构，所以每次找到都将其入栈，这样就能实现后面的左节点会先出栈
                s.push(node);
                // 将当前指针指向它的左节点循环迭代
                node = node.left;
            }

            // 这时候已经找到了最左边的节点，也就是栈顶元素，我们将其出栈
            node = s.pop();
            // 再将这个元素加入到res列表里面
            res.add(node.val);
            // 再访问当前节点的右节点(由左，根，右访问顺序决定)
            node = node.right;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
