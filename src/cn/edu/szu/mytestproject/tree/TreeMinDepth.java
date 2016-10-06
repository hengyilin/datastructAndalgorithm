package cn.edu.szu.mytestproject.tree;

import java.util.ArrayList;

/**
 * Author : hengyilin
 * Date   : 16-9-30
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 二叉树的最小深度测试
 */
public class TreeMinDepth {
    class TreeNode {
        int data;
        TreeNode lChild;
        TreeNode rChild;

        public TreeNode(int data) {
            this.data = data;
            this.lChild = null;
            this.rChild = null;
        }
    }
    private TreeNode root;

    public TreeMinDepth(TreeNode root) {
        this.root = root;
    }

    public TreeMinDepth() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void createBinaryTree(int[] datas) {
        if (datas != null) {
            for (int data : datas) {
                insertElementToTree(data);
            }
        }
    }

    public void insertElementToTree(int data) {
        if (root == null) {
            root = new TreeNode(data);
            System.out.println("插入的数据是：" + data );
        } else {
            TreeNode current = root;
            TreeNode parent = null;
            while (true) {
                parent = current;
                if (current.data > data) {
                    current = current.lChild;
                    if (current == null) {
                        parent.lChild = new TreeNode(data);
                        System.out.println("插入的数据是：" + data);
                        break;
                    }
                }
                else if (current.data < data) {
                    current = current.rChild;
                    if (current == null) {
                        parent.rChild = new TreeNode(data);
                        System.out.println("插入的数据是：" + data);
                        break;
                    }
                }
                else {
                    break;
                }
            }
        }
    }

    public void inOrderTreeWalk(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            inOrderTreeWalk(node.lChild, list);
            list.add(node.data);
            inOrderTreeWalk(node.rChild, list);
        }
    }

    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.data);
            preOrder(node.lChild);
            preOrder(node.rChild);
        }
    }

    public void inOrder(TreeNode node) {
        if (node != null) {
            inOrder(node.lChild);
            System.out.print(node.data);
            inOrder(node.rChild);
        }
    }

    public int getMinDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.lChild == null && node.rChild == null) {
            System.out.println("叶子节点");
            return 1;
        }
        if (node.lChild == null) {
            System.out.println("开始计算右子树深度");
            return getMinDepth(node.rChild) + 1;
        }
        if (node.rChild == null) {
            System.out.println("开始计算左子树深度");
            return getMinDepth(node.lChild) + 1;
        }
        //  前面所有的条件都是用来判断递归什么时候跳出循环的只有下面一句是真正在处理业务
        return Math.min(getMinDepth(node.lChild), getMinDepth(node.rChild)) + 1;
    }

    public static void main(String[] args) {
        int[] datas = {5,8,5,1,2,3,5,9,6,4,7};
        TreeMinDepth treeMinDepth = new TreeMinDepth();
        treeMinDepth.createBinaryTree(datas);
        treeMinDepth.preOrder(treeMinDepth.getRoot());
        System.out.println();
        treeMinDepth.inOrder(treeMinDepth.getRoot());
        ArrayList<Integer> list = new ArrayList<>();
        treeMinDepth.inOrderTreeWalk(treeMinDepth.getRoot(),list);
        System.out.println(list);
        System.out.println("二叉树的最小深度为：");
        int minDepth = treeMinDepth.getMinDepth(treeMinDepth.getRoot());
        System.out.println(minDepth);

    }
}
