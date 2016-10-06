package cn.edu.szu.mytestproject.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 二叉树层序遍历
 */
public class BinaryTreeLevelTraverse {
    class TreeNode{
        public int data;
        public TreeNode lChild;
        public TreeNode rChild;

        public TreeNode(int data) {
            this.data = data;
            this.lChild = null;
            this.rChild = null;;
        }
    }

    /**
     * 二叉树层序遍历
     * @param root 二叉树的根节点
     * @return 列表里面的元素是一个列表里面的列表代表每一层的元素
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>(); // 底层为链表的列表结构
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root); // 入对列
        TreeNode last = root;
        TreeNode nextLast = null;
        List<Integer> currentLevel = new LinkedList<>();
        if (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 出对列
            currentLevel.add(node.data);
            if (node.lChild != null) {
                queue.offer(node.lChild);
                nextLast = node.lChild;
            }
            if (node.rChild != null) {
                queue.offer(node.rChild);
                nextLast = node.rChild;
            }
            if (node == last) {
                last = nextLast;
                result.add(new LinkedList<>(currentLevel));
                currentLevel.clear();
            }
        }
        return result;
    }
}
