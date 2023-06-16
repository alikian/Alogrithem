package com.tree;

import com.tree.DFSTree.TraverseOrder;

public class Example {
    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);

        BFSTreeNaive<Integer> treeNaive = new BFSTreeNaive<>(root);
        treeNaive.printAllLevels();

        BFSTreeQueue<Integer> treeQueue = new BFSTreeQueue<>(root);
        treeQueue.printLevel();

        DFSTree<Integer> dfsTree = new DFSTree<>(root);
        dfsTree.traverse(TraverseOrder.PreOrder);
        dfsTree.traverse(TraverseOrder.InOrder);
        dfsTree.traverse(TraverseOrder.PostOrder);

    }
}
