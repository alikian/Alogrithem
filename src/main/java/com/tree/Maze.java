package com.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.tree.Maze.Cell.Type;


public class Maze {
    int rowCount;
    int colCount;

    Cell entry;
    Cell exist;

    Queue<Cell> nextToVisit = new LinkedList<>();


    static class Cell {
        enum Type {
            Entry, Wall, Exit, Path, Road
        }

        int row;
        int col;

        Cell(int row, int col, Type type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }

        Cell previous;

        boolean visited;
        Type type;

        @Override
        public String toString() {
            return "Row (" + row + "," + col + ")";
        }
    }

    Cell[][] cells;

    public Maze(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        cells = new Cell[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                cells[row][col] = new Cell(row, col, Type.Road);
            }
        }
    }

    public void print(int delay) throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        for (int row = 0; row < rowCount; row++) {
            System.out.println();
            for (int col = 0; col < colCount; col++) {
                Cell cell = cells[row][col];
                switch (cell.type) {
                    case Exit:
                        System.out.print("X");
                        break;
                    case Entry:
                        System.out.print("E");
                        break;
                    case Wall:
                        System.out.print("#");
                        break;
                    case Path:
                        System.out.print(".");
                        break;
                    case Road:
                        if (cell.visited) {
                            System.out.print("v");
                        } else {
                            System.out.print(" ");
                        }
                        break;
                }
            }
        }
        TimeUnit.MILLISECONDS.sleep(delay);
        System.out.println();
        System.out.println();


    }


    public Maze setEntry(int row, int col) {
        cells[row][col].type = Type.Entry;
        this.entry = cells[row][col];
        return this;
    }

    public void setExit(int row, int col) {
        cells[row][col].type = Type.Exit;
        this.exist = cells[row][col];
    }

    public void addWall(int row, int col) {
        cells[row][col].type = Type.Wall;
    }

    public List<Cell> solve() throws InterruptedException {
        nextToVisit.add(entry);
        while (!nextToVisit.isEmpty()) {
            print(50);
            Cell cur = nextToVisit.remove();
            if (cur.visited) {
                continue;
            }
            if (cur.type == Type.Wall) {
                cur.visited = true;
                continue;
            }
            if (cur.type == Type.Exit) {
                System.out.println("Found Path ");
                return backtrace(cur);
            }
            if (cur.row > 0) {
                addNext(cur, cur.row - 1, cur.col);
            }
            if (cur.col > 0) {
                addNext(cur, cur.row, cur.col - 1);
            }
            if (cur.row < rowCount - 1) {
                addNext(cur, cur.row + 1, cur.col);
            }
            if (cur.col < colCount - 1) {
                addNext(cur, cur.row, cur.col + 1);
            }
            cur.visited = true;
        }
        System.out.println("Not found");
        return Collections.emptyList();
    }

    private void addNext(Cell cur, int row, int col) {
        Cell next = cells[row][col];
        if (next.visited) {
            return;
        }
        next.previous = cur;
        nextToVisit.add(next);
    }

    private List<Cell> backtrace(Cell cell) throws InterruptedException {
        List<Cell> cellList = new ArrayList<>();
        Cell cur = cell;
        while (cur != null) {
            if (cur.type == Type.Road) {
                cur.type = Type.Path;
            }
            cellList.add(cur);
            cur = cur.previous;
            print(100);
        }
        return cellList;
    }

    public static void main(String[] args) throws InterruptedException {
        Maze maze = new Maze(10, 15);
        maze.setEntry(0, 1);
        maze.setExit(9, 14);

        maze.addWall(0, 2);

        maze.addWall(0, 3);
        maze.addWall(1, 3);
        maze.addWall(2, 3);
        maze.addWall(3, 3);
        maze.addWall(4, 3);
        maze.addWall(5, 3);

//        maze.addWall(6, 0);
        maze.addWall(6, 1);
        maze.addWall(6, 2);
        maze.addWall(6, 3);
        maze.addWall(6, 4);
        maze.addWall(6, 8);
        maze.addWall(6, 9);


        maze.addWall(8, 4);
        maze.addWall(8, 5);
        maze.addWall(8, 6);
        maze.addWall(8, 7);
        maze.print(10);

        List<Cell> path = maze.solve();

        maze.print(0);
    }
}
