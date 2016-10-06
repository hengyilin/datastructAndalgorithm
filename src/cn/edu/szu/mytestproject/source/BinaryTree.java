package cn.edu.szu.mytestproject.source;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class BinaryTree {
    private Node root;
    private class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    public BinaryTree(){
        root = null;
    }

    private void buildBinaryTree(Node node, int data) {
        if (root == null) {
            root = new Node(data);
        }
        else {
            if (data < node.data) {
                if (node.left == null) {
                    node.left = new Node(data);
                } else {
                    buildBinaryTree(node.left, data);
                }
            } else {
                if (node.right == null) {
                    node.right = new Node(data);
                } else {
                    buildBinaryTree(node.right, data);
                }
            }
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
        //System.out.println();
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data);
            inOrder(node.right);
        }
        //System.out.println();
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data);
        }
        //System.out.println();
    }

    public static void main(String[] args) {
        int[] a = {2, 4, 12, 45, 21, 6, 11};
        BinaryTree binaryTree = new BinaryTree();
        for (int i = 0; i < a.length; i++) {
            binaryTree.buildBinaryTree(binaryTree.root, a[i]);
        }
        binaryTree.preOrder(binaryTree.root);
        System.out.println();
        binaryTree.inOrder(binaryTree.root);
        System.out.println();
        binaryTree.postOrder(binaryTree.root);
        System.out.println();
    }
}
