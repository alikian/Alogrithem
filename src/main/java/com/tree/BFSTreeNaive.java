package com.tree;

public class BFSTreeNaive<T> extends BinaryTree<T>{

    public BFSTreeNaive(Node<T> root) {
        super(root);
    }

    void printLevel(Node<T> node, int level) {
        if (node == null) {
            return;
        }
        if (level == 1) {
            System.out.print(node.data + " ");
        }
        if (level > 1) {
            printLevel(node.left, level - 1);
            printLevel(node.right, level - 1);
        }
    }

    void printAllLevels() {
        System.out.println();
        int height = getHeight(root);
        for (int i = 1; i <= height; i++) {
            printLevel(root, i);
        }
    }
}
