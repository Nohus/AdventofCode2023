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
    val unexplored = mutableListOf(start to 0)
    val explored = mutableMapOf(start to 0)
    while (unexplored.isNotEmpty()) {
        val (current, distance) = unexplored.removeFirst()
        explored[current] = distance
        pipes[grid[current]]!!.forEach { direction ->
            val point = current.move(direction)
            if (point !in explored.keys && point in grid.keys && direction.reverse() in pipes[grid[point]]!!) {
                unexplored += point to (distance + 1)
            }
        }
    }
    explored.values.max()
}
