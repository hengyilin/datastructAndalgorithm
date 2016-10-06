package cn.edu.szu.mytestproject.tree;

import java.util.ArrayList;

/**
 * Author : hengyilin
 * Date   : 16-9-29
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 * 二叉树的创建和遍历
 * 这里是在操作二叉排序树
 */
public class BinaryTree {
    public class TreeNode {
        int leftOrRight; // 0代表root 1 代表lchild 2代表rchild
        int value;
        TreeNode lChild;
        TreeNode rChild;

        public TreeNode(int value) {
            this.value = value;
            this.lChild = null;
            this.rChild = null;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TreeNode) {
                TreeNode node = (TreeNode) obj;
                return node.value == this.value;
            }
            return false;
        }

        @Override
        public String toString() {
            return "TreeNode [leftOrRight="
                    + leftOrRight
                    + ", value="
                    + value
                    + (lChild != null ? ", leftChildNode="
                    + lChild : "")
                    + (rChild != null ? ", rightChildNode="
                    + rChild : "") + "]";
        }
    }

    private TreeNode root;
    private int size;

    public BinaryTree() {
    }

    public BinaryTree(int value) {
        this.root = new TreeNode(value);
        this.size = 1;
    }

    public TreeNode getRoot() {
        return root;
    }

    public int getMax() {
        TreeNode node = root;
        while (node.rChild != null) {
            node = node.rChild;
        }
        return node.value;
    }

    public int getMin() {
        TreeNode node = root;
        while (node.lChild != null) {
            node = node.lChild;
        }
        return node.value;
    }

    public void preOrderTreeWalk(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            list.add(node.value);
            preOrderTreeWalk(node.lChild, list);
            preOrderTreeWalk(node.rChild, list);
        }
    }

    public void inOrderTreeWalk(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            inOrderTreeWalk(node.lChild, list);
            list.add(node.value);
            inOrderTreeWalk(node.rChild, list);
        }
    }

    public void postOrder(TreeNode node, ArrayList<Integer> list) {
        if (node != null) {
            postOrder(node.lChild, list);
            postOrder(node.rChild, list);
            list.add(node.value);
        }
    }

    /**
     * 查找指定节点的引用
     * @param value 指定的节点的数据
     * @return 返回指定节点数据的节点的引用
     */
    public TreeNode search(int value) {
        TreeNode node = root;
        while (node.value != value) {
            if (node.value < value) {
                node = node.rChild;
            } else {
                node = node.lChild;
            }
        }
        return node;
    }

    /**
     * 找到指定节点数据的父节点
     * @param value 指定的节点时数据
     * @return 指定节点的父节点的引用
     */
    public TreeNode findParent(int value) {
        TreeNode node = root;
        TreeNode parent = null;
        while (node.value != value) {
            parent = node;
            if (node.value < value) {
                node = node.rChild;
            } else {
                node = node.lChild;
            }
            if (node == null) {
                parent = null;
            }
        }
        return parent;
    }

    /**
     * 由给定的数据创建一颗排序二叉树
     * 排序二叉树的定义：空树或者时按组以下条件的二叉树：左右子树均是排序二叉树左子树比根节点要小，右子树比根节点要大
     * @param values 要用来创建的二叉树的数值
     */
    public void createBinaryTree(int[] values) {
        if (values != null) {
            for (int value : values) {
                insert(value);
            }
        }
    }

    /**
     * 在指定的位置插入节点
     * @param value 要插入的节点的数据
     */
    private void insert(int value) {
        if (root == null) {
            // 新建第一个节点时才会走这里
            root = new TreeNode(value);
            root.leftOrRight = 0;

        } else {
            TreeNode currNode = root;
            TreeNode preNode = null;
            while (true) {
                //插入的过程只有找到空节点之后才会插入因此要不断地遍历
                preNode = currNode; // 在改变当前指针前要记录当前节点为当前节点的孩子节点的父节点
                if (currNode.value > value) {
                    // 插入左子树
                    currNode = currNode.lChild;
                    if (currNode == null) {
                        preNode.lChild = new TreeNode(value); //新建节点之后新结点的左右节点为null
                        preNode.lChild.leftOrRight = 1;
                        break;
                    }
                }
                else {
                    // 插入右子树
                    currNode = currNode.rChild;
                    if (currNode == null) {
                        preNode.rChild = new TreeNode(value);
                        preNode.rChild.leftOrRight = 2;
                        break;
                    }
                }
            }
        }
        ++size;
    }

    public boolean deleteNode(int value) {
        boolean flag = false;
        TreeNode node = search(value);
        TreeNode preNode = findParent(value);
        if (node != null) {
            if (node.equals(root)) {
                root = null;
                flag = true;
            }
            //删掉节点要分成以下几种情况：
            //情况1：要删除的就节点的左右子树都为空那么直接删除该节点即可
            //在java中直接另一个应用为null就可以不用理会内存的回收，java有垃圾回收机制
            if (node.lChild == null && node.rChild == null) {
                if (node.leftOrRight == 1) { // 判断该节点时父节点的左孩子还是右孩子
                    node = null; // 删除节点
                    preNode.rChild = null; // 修改父节点的左孩子
                } else if (node.leftOrRight == 2) {
                    node = null;
                    preNode.lChild = null;
                }
            }
            //情况2：要删除的节点的左右节点均不为空这种情况最复杂！
            /*
            要保证删除后二叉树的中序遍历相对位置不变
            思路：
                    当前节点的左孩子直接作为当前节点的父节点的左孩子，
                    当前节点的右孩子作为当前节点的左孩子的最右边的节点的右孩子

             */
            else if (node.lChild != null && node.rChild != null) {
                TreeNode successor = findSuccessor(node); // 查找当前节点的最右边的孩子节点
                // 如果当前节点时其父节点的左孩子
                if (node.leftOrRight == 1) {
                    preNode.lChild = successor;
                    preNode.lChild.leftOrRight = 1;
                }
                // 如果当前节点时其父节点的右孩子
                else if (node.leftOrRight == 2) {
                    preNode.rChild = successor;
                    preNode.rChild.leftOrRight = 2;
                }
                successor.lChild = node.lChild;
            }
            // 情况3：左右孩子只有一个为空
            else {
                // 当前节点的右孩子为空
                if (node.lChild != null) {
                    // 当前孩子为父节点的左孩子
                    if (node.leftOrRight == 1) {
                        preNode.lChild = node.lChild;
                    }
                    // 当前节点为父节点的右孩子
                    else if (node.leftOrRight == 2) {
                        preNode.rChild = node.lChild;
                    }
                }
                // 当前节点的左孩子为空
                else if (node.rChild != null) {
                    // 当前孩子为父节点的左孩子
                    if (node.leftOrRight == 2) {
                        preNode.rChild = node.rChild;
                    }
                    // 当前节点为父节点的右孩子
                    else if (node.leftOrRight == -1) {
                        preNode.lChild = node.rChild;
                    }
                }
                node = null; // 删除当前节点
            }
            flag = true;
            --size;
        }
        return flag;
    }

    /**
     * 找到当前节点的最右边的孩子节点
     * @param node 当前要删除的节点
     * @return 当前要删除的节点的最右边的孩子节点
     */
    public TreeNode findSuccessor(TreeNode node) {
        TreeNode parent = node;
        TreeNode successor = node; // 临时变量
        TreeNode currNode = node.rChild; // 当前变量的临时变量
        // 用循环找到最右边的节点
        while (currNode != null) {
            parent = successor;
            successor = currNode;
            currNode = currNode.lChild;
        }
        // 如果当前节点最右边的孩子节点不是当前节点的直接右孩子
        if (!successor.equals(node.rChild)) {

            parent.lChild = successor.lChild;
            successor.rChild = node.rChild;
        }
        return successor;
    }

    /**
     * 获取二叉树的大小
     * @return 二叉树的总节点树
     */
    public int getSize(){
        return this.size;
    }

    /**
     * 判断二叉树是不是空树
     * @return true 表示空二叉树 false 表示非空二叉树
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
}
