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
         // 方法一： 递归
         // 判空
         if (root == null) {
             return res;
         }

         // 将根节点加入结果集中
         res.add(root.val);
         // 递归遍历根节点的左子节点
         preorderTraversal(root.left);
         // 递归遍历根节点的右子节点
         preorderTraversal(root.right);

         return res;


         // 方法二: 迭代
         // 判空
         if (root == null) {
             return res;
         }

         Stack<TreeNode> s = new Stack<>();
         s.push(root);

         // 当栈不为空时
         while (!s.isEmpty()) {
             TreeNode node = s.pop();
             // 当前出栈的元素加入到结果集中
             res.add(node.val);
             // 我们为了模拟根左右的遍历顺序，先要将右子节点加入栈中，左子节点后加入。这样下次出栈左节点就会先出栈，才符合我们的需求
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

        // 这边做了一个很巧妙的改变，也就是说如果左子节点不为空的话。
        // 我这边将它push进栈，下一次循环立马就要出栈，中间这个操作可不可以省
        // 掉呢，答案是可以的，我们将while循环的结束条件改为node不为空，
        // 第一次是根节点肯定不为空，后面就是直接将右子节点push，然后将node.left赋值
        // 给node，如果node为空，再从栈中pop一个出来。可以想下，当node.left和栈都为空的时候
        // 实际上所有的节点我们都遍历到了
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
