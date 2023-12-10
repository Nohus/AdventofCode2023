package day10

import solve
import utils.Direction.EAST
import utils.Direction.NORTH
import utils.Direction.SOUTH
import utils.Direction.WEST
import utils.Point

fun main() = solve { lines ->
    val grid = mutableMapOf<Point, Char>()
    lines.forEachIndexed { y, row ->
        row.forEachIndexed { x, c ->
            grid[Point(x, y)] = c
        }
    }
    val pipes = mutableMapOf(
        'S' to listOf(NORTH, EAST, SOUTH, WEST),
        '|' to listOf(SOUTH, NORTH),
        '-' to listOf(WEST, EAST),
        'L' to listOf(NORTH, EAST),
        'J' to listOf(NORTH, WEST),
        '7' to listOf(SOUTH, WEST),
        'F' to listOf(SOUTH, EAST)
    )
    val start = grid.entries.first { it.value == 'S' }.key
    val unexplored = mutableListOf(start)
    val explored = mutableSetOf<Point>()
    while (unexplored.isNotEmpty()) {
        val current = unexplored.removeFirst()
        explored += current
        pipes[grid[current]]!!.forEach { direction ->
            val point = current.move(direction)
            if (point !in explored) {
                val pipe = grid[point]
                if (pipe != null && direction.reverse() in pipes[pipe]!!) unexplored += point
            }
        }
    }

    val expandedGrid = mutableMapOf<Point, Char>()
    grid.forEach { (point, char) ->
        val expandedPoint = Point(point.x * 3, point.y * 3)
        expandedGrid[expandedPoint] = if (char != '.' && point in explored) '#' else '.'
        expandedPoint.getAdjacent().forEach { expandedGrid[it] = '.' }
        if (point in explored) pipes[char]!!.forEach { expandedGrid[expandedPoint.move(it)] = '#' }
    }

    val toFlood = mutableListOf(Point.ORIGIN)
    while (toFlood.isNotEmpty()) {
        val current = toFlood.removeFirst()
        expandedGrid[current] = '='
        toFlood += current.getAdjacentSides().filter { expandedGrid[it] == '.' && it !in toFlood }
    }

    grid.keys.count { expandedGrid[Point(it.x * 3, it.y * 3)] == '.' }
}
