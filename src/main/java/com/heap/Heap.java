package com.heap;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Heap<T extends Comparable<T>> {

    public Heap(Class<T> clazz, int capacity) {
        items = (T[]) Array.newInstance(clazz, capacity);
    }

    private int capacity = 10;
    private int size = 0;
    T[] items;

    private T getLeftChild(int parentIndex) {
        return items[getLeftChildIndex(parentIndex)];
    }

    private T getRightChild(int parentIndex) {
        return items[getRightChildIndex(parentIndex)];
    }

    private T getParent(int parentIndex) {
        return items[getParentIndex(parentIndex)];
    }

    private boolean hasLeftChild(int parentIndex) {
        return getLeftChildIndex(parentIndex) < size;
    }

    private boolean hasRightChild(int parentIndex) {
        return getRightChildIndex(parentIndex) < size;
    }

    private boolean hasParent(int parentIndex) {
        return getParentIndex(parentIndex) >= 0;
    }


    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private void swap(int indexOne, int indexTwo) {
        T temp = items[indexOne];
        items[indexOne] = items[indexTwo];
        items[indexTwo] = temp;

//        System.out.println("  Swapping "+items[indexOne]+" with "+items[indexTwo]);

    }

    private void ensureCapacity() {
        if (size == capacity) {
            items = Arrays.copyOf(items, capacity * 2);
            capacity *= 2;
        }
    }

    public T peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return items[0];
    }

    public T poll() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        T item = items[0];
        items[0] = items[size - 1];
        size--;
        heapifyDown();
        return item;
    }

    public void add(T item) {
//        System.out.println("Adding "+item);
        ensureCapacity();
        items[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getSmallerChildIndex(index);
            if (items[index].compareTo(items[smallerChildIndex]) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    private int getSmallerChildIndex(int parentIndex) {
        int smallerChildIndex = getLeftChildIndex(parentIndex);
        if (hasRightChild(parentIndex) && getRightChild(parentIndex).compareTo(getLeftChild(parentIndex)) < 0) {
            smallerChildIndex = getRightChildIndex(parentIndex);
        }
        return smallerChildIndex;
    }

    private void heapifyUp() {
        int index = size - 1;
        while (hasParent(index) && getParent(index).compareTo(items[index]) > 0) {
            swap(index, getParentIndex(index));
            index = getParentIndex(index);
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println("  -- "+items[i]);
        }
    }
}
