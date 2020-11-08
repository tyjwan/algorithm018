package Algorithm.leetcode.leetcode.editor.cn;
//根据一棵树的前序遍历与中序遍历构造二叉树。
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 745 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class construct-binary-tree-from-preorder-and-inorder-traversal {
    // 题目链接: https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/

    Map<Integer, Integer> findRoot = new HashMap<Integer, Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /**
         * 方法一： 递归
         *
         * 时间复杂度：O(N), N就是树的节点个数
         * 空间复杂度：O(N), 除去返回的答案需要的 O(n) 空间之外，我们还需要使用 O(n) 的空间存储哈希映射，以及 O(h)（其中 h 是树的高度）的空间表示递归时栈空间。这里 h<n，所以总空间复杂度为O(n)。
         */

        int n = inorder.length;

        // 构造hash映射，帮助我们快速定位中序遍历的根节点，因为前序遍历不需要我们定位，每一颗子树的第一个节点就是根节点
        for (int i = 0; i < n; i++) {
            findRoot.put(inorder[i], i);
        }

        // 递归调用
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);


        /**
         * 方法二：迭代
         *
         * 时间复杂度：
         * 空间复杂度：
         */
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
        // 1. 递归出口

        if (preorderLeft > preorderRight) {
            return null;
        }

        // 2. 处理当前层逻辑

        // 拿到根节点，前序遍历第一个元素就是根节点
        int rootValue = preorder[preorderLeft];
        // 找出根节点在中序遍历中的位置，这样我们就知道了左子树的节点数目和右子树的节点数目
        int rootInorderIndex = findRoot.get(rootValue);
        // 计算出左子树的节点个数
        int leftNodeNum = rootInorderIndex - inorderLeft;
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootValue);

        // 3. 递归调用

        // 递归的得到左子树
        // 对于前序序列而言，左子树就是当前的left边界加一，到left边界加左子树的数目；
        // 对于中序序列而言，左子树就是当前的left边界到中序序列对应的根节点的位置的前一个节点
        root.left = myBuildTree(preorder, inorder, preorderLeft + 1, preorderLeft + leftNodeNum, inorderLeft, rootInorderIndex - 1);

        // 递归的得到右子树
        // 对于前序序列而言，右子树的起点就是除掉根节点以及左子树的之后的下标， 终点就是当前层的preorderRight
        // 对于中序序列而言，右子树的起点就是根节点所在下标加一，终点就是当前层的inorderRight
        root.right = myBuildTree(preorder, inorder, preorderLeft + leftNodeNum + 1, preorderRight, rootInorderIndex + 1, inorderRight);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
