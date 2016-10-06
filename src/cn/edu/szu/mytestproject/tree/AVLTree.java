package cn.edu.szu.mytestproject.tree;

import java.util.ArrayList;

/**
 * Author : hengyilin
 * Date   : 16-10-2
 * Version: 1.0
 * Email  : hylin601@126.com
 * Github : www.github.com/hengyilin
 */
public class AVLTree<T extends Comparable<? super T>> {
    private static class AVLTreeNode<T> {
        T data;
        AVLTreeNode<T> lChild;
        AVLTreeNode<T> rChild;
        int height;

        public AVLTreeNode(T data) {
            this.data = data;
            this.height = 0;
            this.lChild = null;
            this.rChild = null;
        }
    }

    private AVLTreeNode<T> root;

    public AVLTreeNode<T> getRoot() {
        return root;
    }

    public AVLTree() {
        this.root = null;
    }

    public void insertNode(T data) {
        // 只有第一次进来时会执行该代码块（就是当根节点为空时执行）
        if (root == null) {
            root = new AVLTreeNode<T>(data);
//            root.height = Math.max(getHeight(root.lChild), getHeight(root.rChild)) + 1;
//            System.out.println("当前的高度是：" + root.height);
        } else {
            AVLTreeNode currentNode = root;
            AVLTreeNode previousNode = null;
            // 只要根节点不为空就要执行下面的方法
            while (true) {
                previousNode = currentNode;
                int compareResult = data.compareTo((T) currentNode.data); // 比较两个数那个大
                if (compareResult < 0) {
                    // 插入左子树
                    currentNode = currentNode.lChild;
                    if (currentNode == null) {
                        previousNode.lChild = new AVLTreeNode(data);
                        if ((getHeight(root.lChild) - getHeight(root.rChild)) == 2) {
                            // 打破平衡要旋转
                            if ((data.compareTo((T) previousNode.data)) < 0) {
                                // LL型 左左型 右旋操作
                                root = singleRotateWithLeftChild(root);
                            } else {
                                // LR型 左右型
                                root = doubleRotateWithLeftChild(root);
                            }
                        }
//                        root.height = Math.max(getHeight(root.lChild), getHeight(root.rChild)) + 1;
//                        System.out.println("当前的高度是：" + root.height);
                        break;
                    }
                } else if (compareResult > 0) {
                    // 插入右子树
                    currentNode = currentNode.rChild;
                    if (currentNode == null) {
                        previousNode.rChild = new AVLTreeNode(data);
                        if ((getHeight(root.lChild) - getHeight(root.rChild)) == 2) {
                            // 打破平衡
                            if ((data.compareTo((T) previousNode.data)) > 0) {
                                // 右右型
                                root = singleRotateWithRightChild(root);
                            } else {
                                // 右左型
                                root = doubleRotateWithRightChild(root);
                            }
                        }
//                        root.height = Math.max(getHeight(root.lChild), getHeight(root.rChild)) + 1;
//                        System.out.println("当前的高度是：" + root.height);
                        break;
                    }
                } else {
                    // 两个元素大小相等什么都不做
                }
            }
        }
    }

    /**
     * 双右旋
     *
     * @param root
     * @return
     */
    private AVLTreeNode<T> doubleRotateWithRightChild(AVLTreeNode<T> root) {
        root.rChild = singleRotateWithLeftChild(root.rChild);
        return singleRotateWithRightChild(root);
    }

    /**
     * 根节点的右节点向左旋转作为新的根节点
     * 带右子树的单选转  适合与插入根节点右子树的右节点而打破平衡的情况
     * 思路：
     * 1、获取根节点的右节点并保存
     * 2、把原来根节点的右引用变为原来根节点右节点的左子树节点
     * 3、把原来根节点的右节点的左引用变为原来的根节点
     * 4、修改高度值 向左边旋转则右边的高度没有发生变化
     *
     * @param root
     * @return
     */
    private AVLTreeNode<T> singleRotateWithRightChild(AVLTreeNode<T> root) {
        AVLTreeNode tempNode = root.rChild;
        root.lChild = tempNode.lChild;
        tempNode.lChild = root;
        root.height = Math.max(getHeight(root.lChild), getHeight(root.rChild)) + 1;
        tempNode.height = Math.max(getHeight(tempNode.rChild), root.height) + 1;
        return tempNode;
    }

    /**
     * 带左子树的双旋 适合于插入根节点左子树的右子树打破平衡的情况
     * 思路：
     * 1、先左子树先带左子树的右节点左旋
     * 2、再左子树带左节点右旋
     * 3、更新高度
     *
     * @param root
     * @return
     */
    private AVLTreeNode<T> doubleRotateWithLeftChild(AVLTreeNode<T> root) {
        root.lChild = singleRotateWithRightChild(root.lChild);
        return singleRotateWithLeftChild(root);
    }

    /**
     * 根节点的左子树向右旋转作为新的根节点
     * 带左子树单选转 适合于插入根节点左子树的左子树而导致的打破平衡情况
     * 思路：
     * 根节点左子树的根节点作为新的根节点
     * 根节点左节点的右节点作为原来根节点的左子树
     * 原来的根节点作为新根节点的右子树根节点
     * 修改指针即可
     *
     * @param node 根节点
     * @return
     */
    private AVLTreeNode<T> singleRotateWithLeftChild(AVLTreeNode<T> node) {
        AVLTreeNode tempNode = node.lChild; // 根节点左子树的根节点[先保留根节点的左节点]
        node.lChild = tempNode.rChild; // 修改原来根节点的左节点为原来根节点左节点的右节点
        tempNode.rChild = node; // 修改原来根节点的左节点的右节点为原来根节点也就是把原来根节点的左节点作为新的根节点
        node.height = Math.max(getHeight(node.lChild), getHeight(node.rChild)) + 1; // 修改高度
        tempNode.height = Math.max(getHeight(tempNode.lChild), node.height) + 1;

        return tempNode;
    }

    /**
     * 获取节点的高度
     *
     * @param node
     * @return
     */
    public int getHeight(AVLTreeNode node) {
        return node == null ? -1 : node.height;
    }

    public void inOrderPrintTree(AVLTreeNode root, ArrayList<T> list) {
        if (root != null) {
            list.add((T) root.data);
            inOrderPrintTree(root.lChild, list);
            inOrderPrintTree(root.rChild, list);
        }
    }

    public void preOrderPrintTree(AVLTreeNode root, ArrayList<T> list) {
        if (root != null) {
            inOrderPrintTree(root.lChild, list);
            list.add((T) root.data);
            inOrderPrintTree(root.rChild, list);
        }
    }

    public boolean containElement(AVLTreeNode root, T data) {
        while (root != null) {
            int comparaResult = data.compareTo((T) root.data);
            if (comparaResult < 0) {
                root = root.lChild;
            } else if (comparaResult > 0) {
                root = root.rChild;
            } else {
                return true;
            }
        }
        return false;
    }

    public AVLTreeNode findMax() {
        if (root == null) {
            return null;
        }
        AVLTreeNode temp = root;
        while (temp.rChild != null) {
            temp = temp.rChild;
        }
        return temp;
    }

    public AVLTreeNode findMin() {
        if (root == null) {
            return root;
        }
        AVLTreeNode temp = root;
        while (temp.lChild != null) {
            temp = temp.lChild;
        }
        return temp;
    }

    public boolean isEmpty(AVLTreeNode root) {
        return root == null ? true : false;
    }

    ////////////////////////////////////////////////////
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] array = {4, 2, 1, 6, 8, 7, 3, 9, 10};
        System.out.println("Checking... (no more output means success)");
        for (int i : array) {
            tree.insertNode(i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("开始中序遍历");
        tree.inOrderPrintTree(tree.getRoot(), list);
        System.out.println(list);
        System.out.println("开始前序遍历");
        list.clear();
        tree.preOrderPrintTree(tree.root, list);
        System.out.println(list);
        AVLTreeNode maxNode = tree.findMax();
        if (maxNode != null) {
            System.out.println("最大值：" + maxNode.data);
        }
        AVLTreeNode minNode = tree.findMin();
        if (minNode != null) {
            System.out.println("最小值：" + minNode.data);
        }
        System.out.println("是不是包含10？" + tree.containElement(tree.root, 10));
        System.out.println("树的高度是：" + tree.getHeight(tree.root));

    }
}
