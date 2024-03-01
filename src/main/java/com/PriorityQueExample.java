package com;

import java.util.PriorityQueue;

public class PriorityQueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQue = new PriorityQueue<>();
        priorityQue.add(1);
        priorityQue.add(3);
        priorityQue.add(5);
        System.out.println(priorityQue);

        System.out.println("Add 2");
        priorityQue.add(2);
        priorityQue.remove();
        System.out.println(priorityQue);

        System.out.println("Add 4");
        priorityQue.add(4);
        priorityQue.remove();

        System.out.println(priorityQue);

    }

    int[] findMaxProduct(int[] arr) {

        // Write your code here
        int []res=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            if(i<2){
                res[i]=-1;
                continue;
            }

            PriorityQueue que=new PriorityQueue();
            for(int j=0;j<=i;j++){
                que.add(arr[j]);
            }

            while(que.size()>3){
                que.remove();
            }
            int mul=1;
            while(!que.isEmpty()){
//                mul = mul * que.remove();
            }
            res[i]=mul;
        }
        return res;
    }
}
