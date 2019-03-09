import turtle

turtle.speed(-1)

width = 90


def draw_squre(x, y):
    turtle.goto(x, y)
    turtle.begin_fill()
    turtle.down()  # "Pen" down?
    for i in range(4):  # For each edge of the shape
        turtle.forward(width)  # Move forward 40 units
        turtle.left(90)  # Turn ready for the next edge
    turtle.up()  # Pen up
    turtle.end_fill()  # End fill.


def draw_board():
    for row in range(9):
        turtle.up()  # "Pen" down?
        turtle.goto(row * width, 0)
        turtle.down()  # "Pen" down?
        turtle.goto(row * width, width * 8)
        turtle.up()

        turtle.up()  # "Pen" down?
        turtle.goto(0, row * width)
        turtle.down()  # "Pen" down?
        turtle.goto(width * 8, row * width)
        turtle.up()

    for col in range(4):
        for row in range(4):
            draw_squre(col * width * 2, row * width * 2)
            draw_squre(col * width * 2 + width, row * width * 2 + width)


def draw_queen(row, col, erase):
    index = col * 8 + row+col

    turtle.pencolor('black')
    turtle.pensize(4)

    if erase:
        if index % 2 == 0:
            turtle.pencolor('black')

        if index % 2 == 1:
            turtle.pencolor('white')
    else:
        if index % 2 == 0:
            turtle.pencolor('white')

        if index % 2 == 1:
            turtle.pencolor('black')

    turtle.up()
    rad = width / 3
    x = col * width + width / 2
    y = row * width + width / 2 -rad

    turtle.goto(x, y)
    turtle.down()
    turtle.circle(rad)
    turtle.color('black')
