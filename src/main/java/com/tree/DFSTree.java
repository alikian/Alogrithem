package com.tree;

public class DFSTree<T> extends BinaryTree<T> {
    enum TraverseOrder {
        InOrder, PreOrder, PostOrder
    }

    public DFSTree(Node<T> root) {
        super(root);
    }

    public void traverse(TraverseOrder traverseOrder) {
        System.out.print("\n" + traverseOrder + " Traverse: ");
        traverseInOrder(root, traverseOrder);
    }

    public void traverseInOrder(Node<T> node, TraverseOrder traverseOrder) {
        if (node == null) {
            return;
        }
        if (traverseOrder == TraverseOrder.InOrder) {
            traverseInOrder(node.left, traverseOrder);
            System.out.print(node.data + " ");
            traverseInOrder(node.right, traverseOrder);
        }
        if (traverseOrder == TraverseOrder.PreOrder) {
            System.out.print(node.data + " ");
            traverseInOrder(node.left, traverseOrder);
            traverseInOrder(node.right, traverseOrder);
        }
        if (traverseOrder == TraverseOrder.PostOrder) {
            traverseInOrder(node.left, traverseOrder);
            traverseInOrder(node.right, traverseOrder);
            System.out.print(node.data + " ");
        }
    }
}
