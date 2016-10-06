package cn.edu.szu.mytestproject.tree;

import java.util.ArrayList;

/**
 * Author : hengyilin
 * Date   : 16-10-2
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class AVLTree2 {
    private class TreeNode {
        int data;  // 数据项的值
        TreeNode lChild; // 左孩子
        TreeNode rChild; // 右孩子

        public TreeNode(int data) {
            this.data = data;// 数据项的值
            this.lChild = null; // 新建节点时左孩子为空
            this.rChild = null; // 新建节点是右孩子为空
        }
    }

    private TreeNode root;

    public AVLTree2() {
        this.root = null; // 新建一颗二叉树时维护一个根节点并且让根节点为空直至插入数据
    }

    public void insertNode(int data) {
        if (root == null) {
            root = new TreeNode(data);
            System.out.println("新建树，此时高度为：" + getHight(root));
        } else {
            TreeNode currentNode = root;
            TreeNode previousNode = null;
            while (true) {
                previousNode = currentNode;
                if (currentNode.data > data) {
                    // 插入左子树
                    currentNode = currentNode.lChild;
                    if (currentNode == null) {
                        // 当前节点为空节点新建节点
                        previousNode.lChild = new TreeNode(data);
                        System.out.println("插入节点此时高度为：" + getHight(root));
                        break;
                    }
                } else if (currentNode.data < data) {
                    // 插入右子树
                    currentNode = currentNode.rChild;
                    if (currentNode == null) {
                        previousNode.rChild = new TreeNode(data);
                        System.out.println("插入节点此时高度为：" + getHight(root));
                        break;
                    }
                } else {
                    // 相等什么都不做
                    break;
                }
            }

            if (Math.abs(getHight(root.lChild) - getHight(root.rChild)) == 2) {
                System.out.println("打破平衡要旋转");
                if (getHight(root.lChild) > getHight(root.rChild)) {
                    // 插入左子树导致平衡被打破
                    if (data < root.lChild.data) {
                        // 插入的是左子树的左节点
                        root = singleRotateWithLeftChild(root);
                    } else {
                        // 插入的是左子树的右节点
                        root = doubleRotateWithLeftChild(root);
                    }
                } else {
                    // 插入右子树导致平衡被打破
                    if (data > root.rChild.data) {
                        // 插入的是右子树的右节点
                        root = singleRotateWithRightChild(root);
                    } else {
                        // 插入的是右子树的左节点
                        root = doubleRotateWithRightChild(root);
                    }
                }
            }
        }
    }

    private TreeNode singleRotateWithLeftChild(TreeNode root) {
        System.out.println("LL型");
        //向右旋
        TreeNode tempNode = root.lChild;
        if (tempNode.rChild != null) {
            root.lChild = tempNode.rChild;
        } else {
            root.lChild = null;
        }
        tempNode.rChild = root;
        return tempNode;
    }

    private TreeNode doubleRotateWithLeftChild(TreeNode root) {
        System.out.println("LR型");
        root.lChild = singleRotateWithRightChild(root.lChild); // 左子树向右旋
        return singleRotateWithLeftChild(root); // 整颗树向左旋
    }

    private TreeNode singleRotateWithRightChild(TreeNode root) {
        System.out.println("RR型");
        // 向左旋
        TreeNode tempNode = null;
        tempNode = root.rChild;
        if (tempNode.lChild != null) {
            root.rChild = tempNode.lChild;
        } else {
            root.rChild = null;
        }
        tempNode.lChild = root;

        return tempNode;
    }

    private TreeNode doubleRotateWithRightChild(TreeNode root) {
        System.out.println("RL型");
        root.rChild = singleRotateWithLeftChild(root.rChild); // 右子树向左旋
        return singleRotateWithRightChild(root); // 整颗树向右旋
    }

    public int getHight(TreeNode node) {
        int lHight = 0;
        int rHight = 0;
        if (node != null) {
            lHight = getHight(node.lChild); // 遍历左子树的高度
            rHight = getHight(node.rChild); // 遍历右子树的高度
            return Math.max(lHight, rHight) + 1;
        }
        return 0;
    }

    public void preOrder(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            list.add(node.data);
            preOrder(node.lChild, list);
            preOrder(node.rChild, list);
        }
    }

    public void inOrder(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            inOrder(node.lChild, list);
            list.add(node.data);
            inOrder(node.rChild, list);
        }
    }

    public void postOrder(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            postOrder(node.lChild, list);
            postOrder(node.rChild, list);
            list.add(node.data);
        }
    }

    public static void main(String[] args) {
        int[] datas = {4, 8, 5, 2, 3, 9, 6, 7, 1};
        AVLTree2 tree2 = new AVLTree2();
        final int NUMS = 200;
        final int GAP = 17;
        System.out.println("Checking... (no more output means success)");
        for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
            tree2.insertNode(i);
        }
//        for (int data : datas) {
//            tree2.insertNode(data);
//        }
        ArrayList<Integer> list = new ArrayList<>();
        tree2.preOrder(tree2.root, list);
        System.out.println(list);
        list.clear();
        tree2.inOrder(tree2.root, list);
        System.out.println(list);
        list.clear();
        tree2.postOrder(tree2.root, list);
        System.out.println(list);
        list.clear();
    }
}
