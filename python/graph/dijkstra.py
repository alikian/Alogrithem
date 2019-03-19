import tkinter
import time
import threading
import math


class Vertex:
    def __init__(self, name, x, y):
        self.name = name
        self.x = x
        self.y = y
        self.edges = {}
        self.visited = False

    def connect(self, name, distance):
        self.edges[name] = distance


class Graph:
    rad = 10

    def __init__(self, length, width):
        self.width = width
        self.length = length
        self.window = tkinter.Tk()
        self.canvas = tkinter.Canvas(self.window, width=self.width, height=self.length)
        self.canvas.pack()
        self.vertexes = {}

    def add_vertex(self, name, x, y):
        vertex = Vertex(name, x, y)
        self.vertexes[name]=vertex
        self.draw_vertex(vertex)

    def draw_vertex(self, vertex):
        color = "green"
        if vertex.visited:
            color = "red"
        self.circle(vertex.x, vertex.y, Graph.rad, color)
        self.canvas.create_text(vertex.x, vertex.y, font="Purisa", text=vertex.name)

    def circle(self, x, y, r, color):
        return self.canvas.create_oval(x - r, y - r, x + r, y + r, fill=color)

    def connect_vertexes(self, name_a, name_b):

        distance = math.sqrt((vertex_a.x - vertex_b.x) ** 2 + (vertex_a.y - vertex_b.y) ** 2)
        vertex_a.connect(vertex_b.name, distance)
        vertex_b.coneect(vertex_a.name, distance)
        self.canvas.create_line(vertex_a.x,vertex_a.y,vertex_b.x,vertex_b.y)

    def start(self):
        self.window.mainloop()


g = Graph(500, 800)

g.add_vertex("a", 100, 50)
g.add_vertex("b", 100, 300)
g.add_vertex("c", 200, 100)
g.add_vertex("d", 300, 120)
g.add_vertex("e", 400, 140)
g.add_vertex("f", 700, 150)
g.add_vertex("h", 600, 400)
g.add_vertex("i", 500, 420)

g.add_vertex("z", 750, 440)

g.connect_vertexes("a","b")

g.start()
