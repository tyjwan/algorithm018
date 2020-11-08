package Algorithm.leetcode.leetcode.editor.cn;
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。” 
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
// 
//
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
// 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
// 
//
// 
//
// 说明: 
//
// 
// 所有节点的值都是唯一的。 
// p、q 为不同节点且均存在于给定的二叉树中。 
// 
// Related Topics 树 
// 👍 815 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// lowest-common-ancestor-of-a-binary-tree
class lowest-common-ancestor-of-a-binary-tree {
    // 题目链接： https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/

    Map<Integer, TreeNode> map = new HashMap<>();
    Set<Integer> set = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//         方法一：递归

        /**
         * 时间复杂的： 每个树节点都被访问且仅被访问N次（N为树的节点个数）所以时间复杂度是 O(n)
         * 空间复杂度: 由于进行了递归，递归的最大深度本来是树的深度，但是如果树极端情况下退化成链表，所以空间复杂度是O(n)
         */

//         如果root为空返回很容易理解，root 等于 q, p中的一个，那么root就一定是最近的公共祖先
        if (root == null || root == p || root == q) {
            return root;
        }

        // 到下层左边去进行寻找
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 到下层右边去寻找
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左边没有找到并且右边也没有找到，证明就是没有，返回空
        if (left == null && right == null) {
            return null;
        }

        // 如果左边没有找到，那证明两个节点都在右边。
        if (left == null) {
            return right;
        }

        // 如果右边没有找到，那证明两个节点都在左边
        if (right == null) {
            return left;
        }

        // 最后一种情况就是左边也不是null, 右边也不是null，则最近公共祖先是root
        return root;



        // 方法二: 将父节点存储下来，最终判断两个人有没有第一次访问过同一个节点，就找到了他们的最近公共父节点了。

        /**
         * 时间复杂度：O(N)，其中 N 是二叉树的节点数。二叉树的所有节点有且只会被访问一次，从 p 和 q 节点往上跳经过的祖先节点个数不会超过 NN，因此总的时间复杂度为 O(N)O(N)。
         * O(N) ，其中 N 是二叉树的节点数。递归调用的栈深度取决于二叉树的高度，二叉树最坏情况下为一条链，此时高度为 NN，因此空间复杂度为 O(N)O(N)，哈希表存储每个节点的父节点也需要 O(N)O(N) 的空间复杂度，因此最后总的空间复杂度为 O(N)O(N)
         */

        dfs(root);

        // 先从p节点开始从下往上遍历，每遍历一个都加入到set集合中，然后获取其父节点直到为空
        while (p != null) {
            set.add(p.val);
            p = map.get(p.val);
        }

        // 再从q节点从下往上遍历
        while (q != null) {
            // 如果集合中包含这个节点，证明刚刚p遍历过了，这就是他们的最近公共父节点
            if (set.contains(q.val)) {
                return q;
            }
            // 否则继续往上遍历
            q = map.get(q.val);
        }

        // 循环结束还没找到那就是不存在了
        return null;
    }

    public void dfs(TreeNode root) {
        if (root.left != null) {
            map.put(root.left.val, root);
            dfs(root.left);
        }

        if (root.right != null) {
            map.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
