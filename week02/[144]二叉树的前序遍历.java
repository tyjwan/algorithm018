package Algorithm.leetcode.leetcode.editor.cn;//给定一个二叉树，返回它的 前序 遍历。
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
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 
// 👍 367 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class 二叉树的前序遍历 {
    List<Integer> result = new ArrayList<Integer>();

    public List<Integer> preorderTraversal(TreeNode root) {
        // 递归
         if (root == null) {
             return res;
         }

         res.add(root.val);
         preorderTraversal(root.left);
         preorderTraversal(root.right);

         return res;


         // 迭代
         if (root == null) {
             return res;
         }

         Stack<TreeNode> s = new Stack<>();
         s.push(root);

         while (!s.isEmpty()) {
             TreeNode node = s.pop();
             res.add(node.val);
             if (node.right != null) {
                 s.push(node.right);
             }
             if (node.left != null) {
                 s.push(node.left);
             }
         }

         return res;

        // 迭代二
        if (root == null) {
            return res;
        }

        Stack<TreeNode> s = new Stack<>();
        TreeNode node = root;

        while (node != null) {
            res.add(node.val);
            if (node.right != null) {
                s.push(node.right);
            }

            node = node.left;
            if (node == null && !s.isEmpty()) {
                node = s.pop();
            }
        }

        return res;


        // 迭代三
        if (root == null) {
            return res;
        }

        TreeNode p = root;

        while (p != null || !s.isEmpty()) {
            if (p != null) {
                result.add(p.val);
                s.push(p);
                p = p.left;
            } else {
                p = s.pop().right;
            }
        }

        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
