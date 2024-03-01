package com.arrays;

public class ArrayUtil {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 6, 2};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(countiunesSubArrays(arr, i) + " ");
        }
    }

    static int countiunesSubArrays(int[] arr, int index) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j == index || i == index) {
                    boolean isMax = true;
                    for (int k = i; k <= j; k++) {
                        if (arr[k] > arr[index]) {
                            isMax = false;
                            break;
                        }
                    }
                    if (isMax) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
