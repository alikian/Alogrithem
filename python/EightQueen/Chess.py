import tkinter
import time
import threading


class Chess:
    def __init__(self, x_ref, y_ref, width_param):
        self.window = tkinter.Tk()
        self.width = width_param
        self.canvas = tkinter.Canvas(self.window, width=self.width * 10, height=self.width * 10)
        self.xRef = x_ref
        self.yRef = y_ref
        self.window.title("Chess")
        self.canvas.pack()
        self.board = [[0 for i in range(8)] for j in range(8)]
        self.counter = 0
        self.board[1][0] = 1
        tkinter.Button(self.window, text="Solve", command=self.solve).pack()

    def solve(self):
        thread= ThreadedTask(self)
        thread.start()

    def isSafe(self, row, col):
        for colIndex in range(0, col):
            if self.board[row][colIndex]:
                return False

        rowIndex = max(row - col, 0)
        colIndex = max(col - row, 0)
        while rowIndex < row and colIndex < col:
            if self.board[rowIndex][colIndex] == 1:
                return False
            rowIndex += 1
            colIndex += 1

        rowIndex = min(row + col, 7)
        colIndex = max(col - (7 - row), 0)
        while rowIndex > 0 and colIndex < col:
            if self.board[rowIndex][colIndex] == 1:
                return False
            rowIndex -= 1
            colIndex += 1

        return True

    def solve_board(self, col):
        time.sleep(.2)
        for row in range(8):
            if self.isSafe(row, col):
                self.board[row][col] = 1
                queen = self.draw_queen(row, col)
                if col == 7:
                    return True
                if not self.solve_board(col + 1):
                    self.canvas.delete(queen)
                    self.board[row][col] = 0
                else:
                    return True

        return False

    def draw_board(self):
        for row in range(9):
            self.canvas.create_line(row * self.width + self.xRef, 0 + self.yRef, row * self.width + self.xRef,
                                    self.width * 8 + self.yRef)
            self.canvas.create_line(0 + self.xRef, row * self.width + self.yRef, self.width * 8 + self.xRef,
                                    row * self.width + self.yRef)

        for row in range(4):
            for col in range(4):
                x = col * width * 2 + self.xRef
                y = row * width * 2 + self.yRef
                self.canvas.create_rectangle(x, y, x + self.width, y + width, fill="black")
                self.canvas.create_rectangle(x + self.width, y + self.width, x + 2 * self.width, y + 2 * self.width,
                                             fill="black")

    def circle(self, x, y, r, color):
        return self.canvas.create_oval(x - r, y - r, x + r, y + r, fill=color)

    def draw_queen(self, row, col):
        index = col * 8 + row + col
        rad = width / 3
        x = col * width + width / 2
        y = row * width + width / 2
        color = "black"

        if index % 2 == 0:
            color = "white"

        return self.circle(x + self.xRef, y + self.yRef, rad, color)

    def start(self):
        self.window.mainloop()

class ThreadedTask(threading.Thread):
    def __init__(self, chess):
        threading.Thread.__init__(self)
        self.chess = chess
    def run(self):
        self.chess.solve_board(0)

width = 70
chess = Chess(width, width, width)
chess.draw_board()
chess.start()
