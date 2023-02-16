package com.sort;

import java.util.Arrays;

public class MergeSort {
    int[] merge(int arr[], int leftIndex, int middleIndex, int rightIndex) {
        int leftSize = middleIndex - leftIndex + 1;
        int rightSize = rightIndex - middleIndex;

        int left[] = new int[leftSize];
        int right[] = new int[rightSize];

        /*Copy data to temp arrays*/
        for (int i = 0; i < leftSize; ++i)
            left[i] = arr[leftIndex + i];
        for (int j = 0; j < rightSize; ++j)
            right[j] = arr[middleIndex + 1 + j];

        int iRes = leftIndex;
        int iLeft = 0;
        int iRight = 0;
        while (iLeft<leftSize && iRight<rightSize) {
            if (left[iLeft] < right[iRight]) {
                arr[iRes++] = left[iLeft++];
            } else {
                arr[iRes++] = right[iRight++];
            }
        }

        while (iLeft < leftSize) {
            arr[iRes++] = left[iLeft++];
        }

        /* Copy remaining elements of R[] if any */
        while (iRight < rightSize) {
            arr[iRes++] = right[iRight++];
        }

        return arr;
    }

    public void print(int[] a) {
        System.out.println();
        for (int r : a
        ) {
            System.out.print(" " + r);
        }
    }

    void sort(int[] a, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;

            sort(a, leftIndex, mid);
            sort(a, mid + 1, rightIndex);
            merge(a, leftIndex, mid, rightIndex);
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
//        int[] a = {2, 3, 5};
//        int[] b = {1, 5, 7, 10, 12};
//
//        int res[] = mergeSort.merge(a, b);
//        mergeSort.print(res);

        int[] rand = {2, 4, 1, 5, 2, 6};
        mergeSort.print(rand);
        mergeSort.sort(rand,0,rand.length-1);
        mergeSort.print(rand);
    }
}
