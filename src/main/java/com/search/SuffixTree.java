package com.search;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class SuffixTree {
    class Node {
        String value;
        Map<String, Node> children;
        List<Integer> indexes;

        Node(String value) {
            this.value = value;
            children = new Hashtable<>();
            indexes= new ArrayList<>();
        }

        Node getFirstChild() {
            for (Node child : children.values())
                return child;
            return null;
        }

        void compress() {
            if (children.size() == 0) {
                return;
            }
            if (children.size() == 1) {
                Node child = getFirstChild();
                child.compress();

                value = value + child.value;
                children = child.children;
            }

            for(Node child: children.values()){
                child.compress();
            }
        }

        void print(int level) {
            System.out.println();
            for (int i = 0; i < level; i++) {
                System.out.print("   ");
            }
            System.out.print(value +indexes+ ": ");
            for (Node child : children.values()) {
                System.out.print(child.value + " ");
                if (child.children.size() > 0) {
                    child.print(level + 1);
                }
            }
        }

        void insert(String str,int index, String originalSuffix, int level) {
            System.out.println(str + " org:" + originalSuffix + " level:" + level);

            indexes.add(index);

            // it is repeated and length is greater than maxLen
            // then store the substring
            if (indexes.size() > 1 && maxLen < level) {
                maxLen = level;
                maxStr = originalSuffix.substring(0, level);
                System.out.println("Max: "+maxStr);
            }

            if (str.isEmpty())
                return;

            Node child;
            String firstChar = str.substring(0, 1);

            if (children.get(firstChar) == null) {
                child = new Node(firstChar);
                children.put(firstChar, child);
            } else {
                child = children.get(firstChar);
            }

            child.insert(str.substring(1),index, originalSuffix, level + 1);


        }
    }

    int maxLen = 0;
    String maxStr = "";
    Node root = new Node("@");

    String findLSR(String str) {

        //insert all substring in suffix tree
        for (int i = 0; i < str.length(); i++) {
            String s = str.substring(i);
            root.insert(s,i, s, 0);
        }

        System.out.println("LSR:" + maxStr);
        return maxStr;
    }

    void print() {
        root.print(0);
    }

    void compress() {
        root.compress();
    }

    public static void main(String args[]) {
        SuffixTree suffixTree = new SuffixTree();
        suffixTree.findLSR("Banana$");
        suffixTree.print();
        suffixTree.compress();
        suffixTree.print();
    }

}
