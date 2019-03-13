package com.graph.bfs;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


class PostOffice{
    int rowCount =10;
    int colCount =7;
    int map[][]=new int[rowCount][colCount];
    Queue<Node> queue = new ArrayBlockingQueue(1000);

    PostOffice(){
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                map[row][col] =-1;
            }
        }

        addNode(1,1,0);
        addNode(3,1,0);
        addNode(1,4,0);
        addNode(4,6,0);
    }

    class Node{
        int row;
        int col;
        Node(int row,int col){
            this.row = row;
            this.col = col;
        }
    }

    void print(){
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                System.out.print(" "+map[row][col]);
            }
            System.out.println();
        }
    }
    void addNode(int row, int col,int value){
        if(row<0 || col<0 || row>=rowCount || col>=colCount || map[row][col]>=0){
            return;
        }
        map[row][col] = value;
        queue.add(new Node(row, col));
    }

    void findDistance(){
        while(queue.peek()!=null){
            Node node = queue.poll();
            int distance = map[node.row][node.col];
            addNode(node.row, node.col+1, distance+1);
            addNode(node.row, node.col-1, distance+1);
            addNode(node.row+1, node.col, distance+1);
            addNode(node.row-1, node.col, distance+1);
        }
    }

public static void main(String args[]){
        PostOffice postOffice= new PostOffice();

        postOffice.findDistance();
        postOffice.print();

    }
}
