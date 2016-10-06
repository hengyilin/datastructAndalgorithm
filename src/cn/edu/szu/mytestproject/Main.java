package cn.edu.szu.mytestproject;

import cn.edu.szu.mytestproject.tree.BinaryTree;
import cn.edu.szu.mytestproject.tree.BinaryTree2;

import java.util.ArrayList;

/**
 * Author : hengyilin
 * Date   : 16-9-30
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class Main {
    public static void main(String[] args) {
        BinaryTree2 binaryTree2 = new BinaryTree2();
        BinaryTree binaryTree = new BinaryTree();

        int[] datas = {4,2,3,8,6,5,9,1};
        binaryTree2.createBinaryTreeByArray(datas);
        ArrayList<Integer> list = new ArrayList<>();

        binaryTree.createBinaryTree(datas);
        //binaryTree.preOrderTreeWalk(binaryTree.getRoot(), list);
//        binaryTree2.preOrderBinaryTree(binaryTree2.root, list);
//        binaryTree2.inOrderBinaryTree(binaryTree2.root, list);
        binaryTree2.postOrderBinaryTreeByStack(binaryTree2.root, list);
        System.out.println(list);


    }
}
