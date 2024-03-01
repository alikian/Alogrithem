package com.que;

import java.util.LinkedList;

public class QueRemoval {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5};
        arr = findPositions(arr, 5);
        arr = findPositions(arr, 5);
        arr = findPositions(arr, 5);
        arr = findPositions(arr, 5);
        arr = findPositions(arr, 5);
    }

    static int[] findPositions(int[] arr, int x) {
        int[] result = new int[arr.length - 1];
        LinkedList<Integer> que = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            que.add(arr[i]);
        }

        LinkedList<Integer> reminder = new LinkedList<>();
        int maxItem = 0;
        for (int i = 0; i < Math.min(x,arr.length); i++) {
            int item = que.remove();
            maxItem = Math.max(maxItem, item);
            reminder.add(item);
        }
        int reminderSize = reminder.size();
        boolean itemRemoved = false;
        for (int i = 0; i < reminderSize; i++) {
            int item = reminder.remove();
            if (item != maxItem || itemRemoved) {
                reminder.add(Math.max(item - 1, 0));
            }else {
                itemRemoved = true;
            }
        }

        while (!reminder.isEmpty()) {
            que.add(reminder.remove());
        }
        int queSize = que.size();
        for (int i = 0; i < queSize; i++) {
            result[i] = que.remove();
            System.out.print(result[i] + " ");
        }
        System.out.println();
        return result;
    }
}
