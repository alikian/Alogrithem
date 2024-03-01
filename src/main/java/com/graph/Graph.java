package com.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Graph {
    Map<Integer, Node> nodeLookup = new HashMap<>();

    static class Node {
        private int id;
        List<Node> adjacent = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    public void addEdge(int source, int destination) {
        Node s = getNode(source);
        Node d = getNode(destination);
        s.adjacent.add(d);
    }

    public Node getNode(int id) {
        return nodeLookup.get(id);
    }

    public void addNode(int id) {
        Node node = new Node(id);
        nodeLookup.put(id, node);
    }

    public boolean hasPathDFS(int source, int destination) {
        System.out.println("DFS source: " + source + "  destination: " + destination);
        Node s = getNode(source);
        Node d = getNode(destination);
        Set<Integer> visited = new HashSet<>();
        return hasPathDFS(s, d, visited);
    }

    public boolean hasPathBFS(int source, int destination) {
        System.out.println("BFS source: " + source + "  destination: " + destination);
        Node s = getNode(source);
        Node d = getNode(destination);
        return hasPathBFS(s, d);
    }

    private boolean hasPathBFS(Node source, Node destination) {
        Queue<Node> nextToVisit = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        nextToVisit.add(source);
        while (!nextToVisit.isEmpty()) {
            Node node = nextToVisit.remove();
            System.out.print(node.id+" ");
            if (node == destination) {
                return true;
            }
            if (visited.contains(node.id)) {
                continue;
            }
            visited.add(node.id);
            nextToVisit.addAll(node.adjacent);
        }
        return false;
    }

    private boolean hasPathDFS(Node source, Node destination, Set<Integer> visited) {
        if (visited.contains(source.id)) {
            return false;
        }
        System.out.print("" + source.id+" ");
        visited.add(source.id);
        if (source == destination) {
            return true;
        }
        for (Node child : source.adjacent) {
            if (hasPathDFS(child, destination, visited)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);
        g.addNode(5);

        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(0, 5);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 1);
        g.addEdge(3, 2);
        g.addEdge(3, 4);

        System.out.println("-> "+g.hasPathDFS(0, 2));

        System.out.println("-> "+g.hasPathBFS(0, 2));
    }
}
