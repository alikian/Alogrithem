package com.recursive;

import java.util.Arrays;

import java.util.Arrays;

public class ArrayPermutations {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4}; // Example array
        System.out.println("All permutations of the array:");
        permute(array, 0);
    }

    // Method to generate and print all permutations of the array
    public static void permute(int[] array, int start) {
        if (start >= array.length) {
            // Print the current permutation
            System.out.println(Arrays.toString(array));
            return;
        }

        for (int i = start; i < array.length; i++) {
            // Swap the current element with itself and with every element after it
            swap(array, start, i);
            // Recurse on the remaining elements
            permute(array, start + 1);
            // Backtrack: swap the elements back to undo the previous swap
            swap(array, start, i);
        }
    }

    // Helper method to swap two elements in the array
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
