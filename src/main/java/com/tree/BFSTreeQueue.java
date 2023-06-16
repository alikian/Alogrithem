package com.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BFSTreeQueue<T> extends BinaryTree<T> {
    public BFSTreeQueue(Node<T> root) {
        super(root);
    }

    public void printLevel() {
        System.out.println();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            System.out.print(node.data+" ");

            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }

        }
    }
}
