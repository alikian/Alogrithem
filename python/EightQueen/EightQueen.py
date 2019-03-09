from array import *
import DrawBoard
import turtle
import time

turtle.speed(-1)

counter = 0

board = [[0 for i in range(8)] for j in range(8)]
checking_display = False


def init():
    global board
    print("init", board[1][1])


def isSafe(row, col):
    global board, counter

    if checking_display:
        DrawBoard.draw_queen(row, col, False)

    # counter += 1
    # sleep = .3
    # if counter > 50:
    #     sleep = 0
    # time.sleep(sleep)

    for colIndex in range(0, col):
        if board[row][colIndex]:
            if checking_display:
                DrawBoard.draw_queen(row, col, True)
            return False

    rowIndex = max(row - col, 0)
    colIndex = max(col - row, 0)
    while rowIndex < row and colIndex < col:
        if board[rowIndex][colIndex] == 1:
            if checking_display:
                DrawBoard.draw_queen(row, col, True)
            return False
        rowIndex += 1
        colIndex += 1

    rowIndex = min(row + col, 7)
    colIndex = max(col - (7 - row), 0)
    while rowIndex > 0 and colIndex < col:
        if board[rowIndex][colIndex] == 1:
            if checking_display:
                DrawBoard.draw_queen(row, col, True)
            return False
        rowIndex -= 1
        colIndex += 1

    if checking_display:
        DrawBoard.draw_queen(row, col, True)
    return True


def printBoard():
    for row in range(8):
        for col in range(8):
            print(" ", board[row][col], end=" ")
        print()


def solveBoard(col):
    global board

    for row in range(8):
        if isSafe(row, col):
            board[row][col] = 1
            DrawBoard.draw_queen(row, col, False)
            if col == 7:
                return True
            if not solveBoard(col + 1):
                DrawBoard.draw_queen(row, col, True)
                board[row][col] = 0
            else:
                return True

    return False


DrawBoard.draw_board()

init()
board[1][0] = 1
DrawBoard.draw_queen(1, 0, False)

solveBoard(1)

printBoard()

turtle.done()
