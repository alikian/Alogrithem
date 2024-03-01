package com.tree;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
class TriNode{
    char c;
    int worldCount;
    Map<Character,TriNode> children;
    public TriNode(char c){
        this.c = c;
        children=new HashMap<>();
    }
    void insert(String s){

        if(s.length()==0){
            return;
        }
//        worldCount ++;
        char first=s.charAt(0);
        TriNode child=children.get(first);
        if(child==null){
            child = new TriNode(first);
            children.put(first, child);
        }
        child.worldCount++;
        if(s.length()>1){
            child.insert(s.substring(1));
        }
    }
    TriNode find(String s){
        if(s==null || s.isEmpty()){
            return null;
        }
        char first=s.charAt(0);
        if(first!=c){
            return null;
        }
        if(s.length()==1){
            return this;
        }
        char seconde=s.charAt(1);
        TriNode child=children.get(seconde);
        if(child==null){
            return null;
        }
        return child.find(s.substring(1));

    }
    void print(){
        Set<Character> keySet=children.keySet();
        if(keySet!=null){
            for(Character c:keySet){
                System.out.print("key: "+c);
            }
        }
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        TriNode root = new TriNode(' ');
        root.insert("h");
        root.insert("hh");
        root.insert("hhh");
        TriNode triNode1=root.find(" h");
        TriNode triNode2=root.find(" hh");
        TriNode triNode3=root.find(" hhh");
        System.out.println(triNode1.worldCount);
        System.out.println(triNode2.worldCount);
        System.out.println(triNode3.worldCount);
    }
}
