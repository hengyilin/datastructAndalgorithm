package cn.edu.szu.mytestproject.source;

import java.util.Scanner;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * Description : 二叉树的最短路径树
 */
public class MinPathTree {
    /**
     * 树的数据结构
     */
    public static class TreeNode {
        int value; // 二叉树的节点的值
        TreeNode lChild; // 左孩子
        TreeNode rChild; // 右孩子

        TreeNode() {
        }

        TreeNode(int value) {
            this.value = value;
            this.lChild = null;
            this.rChild = null;
        }
    }

    private static TreeNode root = new TreeNode(); // 头节点的引用

    public static void main(String[] args) {
        System.out.println("开始输入二叉树");
        createBinaryTree(root); // 创建一颗二叉树
        System.out.println("二叉树输入完毕");
        System.out.println(root);
        inOrderTraverse(root); // 中序遍历二叉树
    }

    /**
     * 构造一颗二叉树
     * 逻辑就是：
     * 1、输入一个节点的值
     * 2、判断该值能不能用于构造一个子树
     * 3、如果能用于构造子树那么就让该值作为一个节点同时构造其左子树
     * 4、如果不能用于构造子树那么就返回构造其右子树
     *
     * @param treeNode
     */
    private static void createBinaryTree( TreeNode treeNode) {
        Scanner sc = new Scanner(System.in);
        int nodeValue = sc.nextInt();
        if (nodeValue == -1) {
            treeNode = null;
            System.out.println("到叶子节点啊");
            return ;
        } else {
            if (treeNode == null) {
                treeNode = new TreeNode(nodeValue);
            } else {
                treeNode.value = nodeValue;
            }
        }
    }

    private static void inOrderTraverse(TreeNode node) {
        System.out.println("开始中序遍历");
        if (node != null) {
            inOrderTraverse(node.lChild);
            System.out.println(node.value);
            inOrderTraverse(node.rChild);
        }
    }
}
