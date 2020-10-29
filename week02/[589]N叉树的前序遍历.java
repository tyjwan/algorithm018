package Algorithm.leetcode.leetcode.editor.cn;//给定一个 N 叉树，返回其节点值的前序遍历。
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 103 👎 0


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

import java.util.*;

class N叉树的前序遍历 {
    List<Integer> result = new ArrayList<>();
    public List<Integer> preorder(Node root) {
        // 递归解法（深度优先dfs）
        if (root == null) {
            return result;
        }


        result.add(root.val);

        List<Node> ln = root.children;
        for (int i = 0; i < ln.size(); i++) {
            result = preorder(ln.get(i));
        }

        return result;

        // 迭代解法（广度优先bfs)
        if (root == null) {
            return result;
        }

        Stack<Node> s = new Stack<Node>();
        s.push(root);

        while (!s.isEmpty()) {
            root = s.pop();
            result.add(root.val);

            for (int i = root.children.size() - 1; i >= 0; i--) {
                s.push(root.children.get(i));
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
