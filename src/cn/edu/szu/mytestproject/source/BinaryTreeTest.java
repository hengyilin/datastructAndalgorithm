package cn.edu.szu.mytestproject.source;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class BinaryTreeTest {
    private class TreeNode {
        public int value;
        public TreeNode lChild;
        public TreeNode rChild;

        public TreeNode(int value) {
            this.value = value;
            this.lChild = null;
            this.rChild = null;
        }
    }
    private TreeNode root;
    public BinaryTreeTest(){
        root = null;
    }

    private void createBibaryTree(TreeNode node,int val) {

        if (val == -1) {
            return ;
        } else {
            if (root == null) {
                root = new TreeNode(val);
            }
            else {
                if (node.lChild == null) {
                    node.lChild = new TreeNode(val);
                } else {
                    createBibaryTree(node.lChild,val);
                }
                if (node.rChild == null) {
                    node.rChild = new TreeNode(val);
                } else{
                    createBibaryTree(node.rChild,val);
                }
            }
        }
    }

    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.value);
            preOrder(node.lChild);
            preOrder(node.rChild);
        }
    }

    public static void main(String[] args) {
        BinaryTreeTest binaryTreeTest = new BinaryTreeTest();
        Scanner sc = new Scanner(System.in);
        int val = sc.nextInt();
        while(val != -2)
        {
            binaryTreeTest.createBibaryTree(binaryTreeTest.root,val);
            val = sc.nextInt();
        }
        binaryTreeTest.preOrder(binaryTreeTest.root);
    }
}
