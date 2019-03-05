package com.backtrack;

import java.math.*;

public class EightQueens {
    int board[][] = new int[8][8];
    int moveCount = 0;
    int colMoveCount[] = new int[8];

    boolean debug = false;

    public static void main(String args[]) {
        EightQueens eightQueens = new EightQueens();

        eightQueens.solveBoard(0);
        eightQueens.printBoard();
        eightQueens.printBoardInfo();
    }

    private void printBoardInfo() {
        System.out.println("Move Count: " + moveCount);
        System.out.println("Col Move Count: ");
        for (int col = 0; col < 8; col++) {
            System.out.print(" " + colMoveCount[col]);
        }
    }


    boolean solveBoard(int col) {
        if (debug)
            System.out.println("solving: col:" + col);

        for (int row = 0; row < 8; row++) {
            if (isSafe(row, col)) {
                if (debug)
                    System.out.println("Found Safe: col,row: " + col + "," + row);
                board[row][col] = 1;
                if (col == 7) {
                    return true;
                }
                if (!solveBoard(col + 1)) {
                    board[row][col] = 0;
                    if (debug)
                        System.out.println("can't solve: col:" + col);
                } else {
                    if (debug)
                        System.out.println("solved: col:" + col);
                    return true;
                }

            }
        }
        if (debug)
            System.out.println("can't solveBoard: col:" + col);
        return false;
    }

    boolean isSafe(int row, int col) {
        moveCount++;
        colMoveCount[col]++;

        // Check row 
        for (int colIndex = 0; colIndex < col; colIndex++) {
            if (board[row][colIndex] == 1) {
                return false;
            }
        }

        // Check diagnoly downward
        for (int rowIndex = Math.max(row - col, 0), colIndex = Math.max(col - row, 0); rowIndex < row && colIndex < col; rowIndex++, colIndex++) {
            if (board[rowIndex][colIndex] == 1) {
                return false;
            }
        }
        
        // Check diagnoly upward
        for (int rowIndex = Math.min(row + col, 7), colIndex = Math.max(col - (7 - row), 0); rowIndex > 0 && colIndex < col; rowIndex--, colIndex++) {
            if (board[rowIndex][colIndex] == 1) {
                return false;
            }
        }
        return true;
    }

    void printBoard() {
        System.out.println();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print(board[row][col] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
