package cn.edu.szu.mytestproject.tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Author : hengyilin
 * Date   : 16-9-30
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class BinaryTree2 {
    class TreeNode{
        public int data;
        public TreeNode lChild;
        public TreeNode rChild;

        public TreeNode(int data) {
            this.data = data;
            this.lChild = null;
            this.rChild = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public TreeNode getlChild() {
            return lChild;
        }

        public void setlChild(TreeNode lChild) {
            this.lChild = lChild;
        }

        public TreeNode getrChild() {
            return rChild;
        }

        public void setrChild(TreeNode rChild) {
            this.rChild = rChild;
        }
    }

    public TreeNode root;

    public BinaryTree2() {
    }

    public BinaryTree2(TreeNode root) {
        this.root = root;
    }

    /*
    创建二叉树
     */
    public void createBinaryTreeByArray(int[] datas) {
        if (datas != null) {
            for (int data : datas) {
                insertDataToBinaryTree(data);
            }
        }
    }

    public void insertDataToBinaryTree(int data) {
        if (root == null) {
            root = new TreeNode(data);
        } else {
            TreeNode currentNode = root; // 当前节点
            TreeNode parentNode = null;// 父节点
            while (true) {
                parentNode = currentNode;
                if (currentNode.data > data) {
                    // 在左子树插入元素
                    currentNode = currentNode.getlChild();
                    // 该创建节点的条件就是当前节点是空节点
                    if (currentNode == null) {
                        parentNode.lChild = new TreeNode(data);
                        break;
                    }
                } else if (currentNode.data < data){
                    // 在右子树插入元素
                    currentNode = currentNode.getrChild();
                    // 该创建节点的条件就是当前节点是空节点
                    if (currentNode == null) {
                        parentNode.rChild = new TreeNode(data);
                        break;
                    }
                }
            }
        }
    }

    /*
    以下为非递归遍历二叉树
     */
    public void preOrderBinaryTreeByStack(TreeNode node, ArrayList<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = node;
        //  全部访问完啦就会出现currentNode == null && stack.size() == 0
        while (currentNode != null || stack.size() > 0) {
            while (currentNode != null) {
                list.add(currentNode.getData());
                stack.push(currentNode);
                currentNode = currentNode.getlChild();
            }
            currentNode = stack.pop();
            currentNode = currentNode.getrChild();
        }
    }

    public void inOrderBinaryTreeByStack(TreeNode node, ArrayList<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = node;
        while (currentNode != null || stack.size() > 0) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getlChild();
            }
            if (stack.size() > 0) {
                currentNode = stack.pop();
                list.add(currentNode.getData());
                currentNode = currentNode.getrChild();
            }
        }
    }

    public void postOrderBinaryTreeByStack(TreeNode node, ArrayList<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = node;
        TreeNode visitedNode = null;
        while (currentNode != null) {
             // 左子树进栈(左子树不为空则左子树进栈)
            while (currentNode.getlChild() != null) {
                stack.push(currentNode);
                currentNode = currentNode.getlChild();
            }
            // 没有右子树或者右子树已经输出
            while (currentNode != null && (currentNode.getrChild() == null
                                            || currentNode.getrChild() == visitedNode)) {
                list.add(currentNode.getData());
                visitedNode = currentNode;
                if (stack.empty()) {
                    return ;
                }
                // 在栈弹出元素之前判断栈是不是空是很有必要的因为如果栈空啦就没有元素可以弹出了自然也就不用弹啦
                currentNode = stack.pop();
            }
            // 没有左子树但是有右子树则当前节点进栈通知开始遍历当期那节点的右子树，在遍历右子树时也是重新左子树的过程
            stack.push(currentNode); // 当前节点进栈
            currentNode = currentNode.getrChild(); // 当前节点变为当前节点的右节点

        }
    }
    /*
    以下为递归遍历二叉树
     */
    public void preOrderBinaryTree(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            list.add(node.data);
            preOrderBinaryTree(node.lChild, list);
            preOrderBinaryTree(node.rChild, list);
        }
    }

    public void inOrderBinaryTree(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            inOrderBinaryTree(node.getlChild(), list);
            list.add(node.getData());
            inOrderBinaryTree(node.getrChild(), list);
        }
    }

    public void postOrderBinaryTree(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            postOrderBinaryTree(node.getlChild(), list);
            postOrderBinaryTree(node.getrChild(), list);
            list.add(node.getData());
        }
    }

    public TreeNode serachNodeByValue(int data) {
        TreeNode currentNode = root;
        //  只要当前的节点的数据不与要查找的值相那么就要不断在左右子树中查找
        while (currentNode.getData() != data) {
            if (currentNode.getData() > data) {
                // 在左子树查找
                currentNode = currentNode.getlChild();
            }
            if (currentNode.getData() < data) {
                // 在右子树查找
                currentNode = currentNode.getrChild();
            }
        }
        return currentNode;
    }

    public TreeNode getParentByValue(int data) {
        TreeNode parentNode = null;
        TreeNode currentNode = root;
        // 只要当前节点的值不等于要查找的值那就不断在左右子树中查找
        while (currentNode.getData() != data) {
            parentNode = currentNode;
            if (currentNode.getData() < data) {
                currentNode = currentNode.getrChild();// 在右子树查找
            }
            else {
                currentNode = currentNode.getlChild(); // 在左子树查找
            }
        }
        return parentNode;
    }
}
