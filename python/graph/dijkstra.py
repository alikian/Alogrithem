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
        self.visiting = False
        self.dist = sys.maxsize
        self.parent = None
        self.is_in_path = False
        self.dist_text = None
        self.checking= False

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
        tkinter.Button(self.window, text="Solve", command=self.solve).pack()

    def add_vertex(self, name, x, y):
        vertex = Vertex(name, x, y)
        self.vertices[name] = vertex
        self.draw_vertex(vertex)

    def solve(self):
        thread = ThreadedTask(self)
        thread.start()

    def draw_vertex(self, vertex):
        color = "green"
        if vertex.visited:
            color = "red"
        if vertex.visiting:
            color = "orange"
        if vertex.checking:
            color = "yellow"
        if vertex.is_in_path:
            color = "blue"
        self.circle(vertex.x, vertex.y, Graph.rad, color)
        self.canvas.create_text(vertex.x, vertex.y, font="Purisa", text=vertex.name)
        if vertex.dist != sys.maxsize:
            if vertex.dist_text is not None:
                self.canvas.delete(vertex.dist_text)
            vertex.dist_text = self.canvas.create_text(vertex.x + Graph.rad, vertex.y - Graph.rad * 2, font="Purisa",
                                                       text=str(vertex.dist))

    def circle(self, x, y, r, color):
        return self.canvas.create_oval(x - r, y - r, x + r, y + r, fill=color)

    def connect_vertices(self, name_a, name_b):
        vertex_a = self.vertices[name_a]
        vertex_b = self.vertices[name_b]
        distance = int(math.sqrt((vertex_a.x - vertex_b.x) ** 2 + (vertex_a.y - vertex_b.y) ** 2))
        vertex_a.connect(vertex_b.name, distance)
        vertex_b.connect(vertex_a.name, distance)
        self.canvas.create_line(vertex_a.x, vertex_a.y, vertex_b.x, vertex_b.y)
        # self.canvas.create_text((vertex_a.x + vertex_b.x) / 2, (vertex_a.y + vertex_b.y) / 2, font="Purisa",
        #                         text=str(distance))
        self.draw_vertex(vertex_a)
        self.draw_vertex(vertex_b)

    def start(self):
        self.window.mainloop()

    def min_distance(self):

        min_name = ""
        min_dist = sys.maxsize

        for name, vertex in self.vertices.items():
            if not vertex.visited:
                if vertex.dist < min_dist:
                    min_name = name
                    min_dist = vertex.dist

        return self.vertices[min_name]

    def dijkstra(self, name_src, name_dest):
        src_vertex = self.vertices[name_src]
        src_vertex.dist = 0

        for count in range(0, len(self.vertices) - 1):
            vertex = self.min_distance()
            vertex.visited = True
            vertex.visiting = True
            time.sleep(.4)
            self.draw_vertex(vertex)

            for name, dist in vertex.edges.items():
                n_vertex = self.vertices[name]
                if not n_vertex.visited and n_vertex.dist > dist + vertex.dist:
                    n_vertex.checking = True

                    n_vertex.dist = dist + vertex.dist
                    n_vertex.parent = vertex

                    self.draw_vertex(n_vertex)
                    time.sleep(1)
                    n_vertex.checking = False
                    self.draw_vertex(n_vertex)

            vertex.visiting = False
            self.draw_vertex(vertex)


        vertex = self.vertices[name_dest]
        while vertex.name != name_src:
            vertex.is_in_path = True
            self.draw_vertex(vertex)
            vertex = vertex.parent

        vertex.is_in_path = True
        self.draw_vertex(vertex)



class ThreadedTask(threading.Thread):
    def __init__(self, graph):
        threading.Thread.__init__(self)
        self.graph = graph

    def run(self):
        self.graph.dijkstra("a", "z")


g = Graph(500, 800)

g.add_vertex("a", 120, 50)
g.add_vertex("b", 100, 300)
g.add_vertex("c", 200, 100)
g.add_vertex("d", 300, 50)
g.add_vertex("e", 400, 40)
g.add_vertex("f", 700, 100)
g.add_vertex("h", 600, 400)
g.add_vertex("i", 500, 420)
g.add_vertex("j", 450, 320)
g.add_vertex("k", 550, 220)
g.add_vertex("l", 220, 220)
g.add_vertex("m", 320, 100)

g.add_vertex("z", 750, 440)

g.connect_vertices("a", "b")
g.connect_vertices("a", "d")
g.connect_vertices("c", "b")
g.connect_vertices("a", "c")
g.connect_vertices("c", "d")
g.connect_vertices("b", "i")
g.connect_vertices("i", "h")
g.connect_vertices("c", "j")
g.connect_vertices("c", "k")
g.connect_vertices("b", "j")
g.connect_vertices("j", "k")
g.connect_vertices("j", "h")
g.connect_vertices("k", "h")
g.connect_vertices("k", "f")
g.connect_vertices("k", "z")
g.connect_vertices("e", "k")
g.connect_vertices("d", "e")
g.connect_vertices("e", "f")
g.connect_vertices("f", "z")
g.connect_vertices("h", "z")
g.connect_vertices("e", "j")
g.connect_vertices("a", "l")
g.connect_vertices("l", "j")
g.connect_vertices("a", "m")
g.connect_vertices("m", "j")


g.start()
