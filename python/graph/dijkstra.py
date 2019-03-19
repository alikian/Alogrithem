import tkinter
import time
import threading
import math
import sys


class Vertex:
    def __init__(self, name, x, y):
        self.name = name
        self.x = x
        self.y = y
        self.edges = {}
        self.visited = False
        self.dist = sys.maxsize

    def connect(self, name, dist):
        self.edges[name] = dist


class Graph:
    rad = 10

    def __init__(self, length, width):
        self.width = width
        self.length = length
        self.window = tkinter.Tk()
        self.canvas = tkinter.Canvas(self.window, width=self.width, height=self.length)
        self.canvas.pack()
        self.vertices = {}

    def add_vertex(self, name, x, y):
        vertex = Vertex(name, x, y)
        self.vertices[name] = vertex
        self.draw_vertex(vertex)

    def draw_vertex(self, vertex):
        color = "green"
        if vertex.visited:
            color = "red"
        self.circle(vertex.x, vertex.y, Graph.rad, color)
        self.canvas.create_text(vertex.x, vertex.y, font="Purisa", text=vertex.name)
        if vertex.dist != sys.maxsize:
            self.canvas.create_text(vertex.x + Graph.rad, vertex.y - Graph.rad*2, font="Purisa", text=str(vertex.dist))

    def circle(self, x, y, r, color):
        return self.canvas.create_oval(x - r, y - r, x + r, y + r, fill=color)

    def connect_vertices(self, name_a, name_b):
        vertex_a = self.vertices[name_a]
        vertex_b = self.vertices[name_b]
        distance = int(math.sqrt((vertex_a.x - vertex_b.x) ** 2 + (vertex_a.y - vertex_b.y) ** 2))
        vertex_a.connect(vertex_b.name, distance)
        vertex_b.connect(vertex_a.name, distance)
        self.canvas.create_line(vertex_a.x, vertex_a.y, vertex_b.x, vertex_b.y)
        self.canvas.create_text((vertex_a.x+vertex_b.x)/2, (vertex_a.y+vertex_b.y)/2, font="Purisa", text=str(distance))
        self.draw_vertex(vertex_a)
        self.draw_vertex(vertex_b)

    def start(self):
        self.window.mainloop()


    def min_distance(self, vertex):

        min_name = ""
        min_dist = sys.maxsize

        for name, dist in vertex.edges.items():
            if not self.vertices[name].visited:
                if self.vertices[name].dist > dist + vertex.dist:
                    self.vertices[name].dist = dist + vertex.dist
                if self.vertices[name].dist < min_dist:
                    min_name = name
                    min_dist = self.vertices[name].dist

        vertex.next = min_name

        return self.vertices[min_name]

    def dijkstra(self, name_src, name_dest):
        src_vertex = self.vertices[name_src]
        src_vertex.dist = 0
        checking_vertex = src_vertex

        while checking_vertex.name != name_dest:
            vertex = self.min_distance(checking_vertex)
            print("vertex: " + checking_vertex.name)
            checking_vertex.visited = True
            self.draw_vertex(checking_vertex)
            checking_vertex = vertex

        checking_vertex.visited = True
        print("vertex: " + checking_vertex.name)
        self.draw_vertex(checking_vertex)



g = Graph(500, 800)

g.add_vertex("a", 120, 50)
g.add_vertex("b", 100, 300)
g.add_vertex("c", 200, 100)
g.add_vertex("d", 300, 120)
g.add_vertex("e", 400, 140)
g.add_vertex("f", 700, 100)
g.add_vertex("h", 600, 400)
g.add_vertex("i", 500, 420)
g.add_vertex("j", 250, 250)
g.add_vertex("k", 550, 220)

g.add_vertex("z", 750, 440)

g.connect_vertices("a", "b")
g.connect_vertices("a", "c")
g.connect_vertices("c", "d")
g.connect_vertices("b", "i")
g.connect_vertices("i", "h")
g.connect_vertices("c", "j")
g.connect_vertices("b", "j")
g.connect_vertices("j", "k")
g.connect_vertices("k", "h")
g.connect_vertices("k", "f")
g.connect_vertices("k", "z")
g.connect_vertices("e", "k")
g.connect_vertices("d", "e")
g.connect_vertices("e", "f")
g.connect_vertices("f", "z")
g.connect_vertices("h", "z")

g.dijkstra("a", "z")

g.start()
