package day11

import solve
import utils.Point
import kotlin.math.max
import kotlin.math.min

fun main() = solve { lines ->
    val grid = mutableMapOf<Point, Char>()
    val galaxies = mutableListOf<Point>()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            grid[Point(x, y)] = c
            if (c == '#') galaxies += Point(x, y)
        }
    }
    val expandedX = (0..(lines[0].length)).filter { x -> (0..(lines.size)).none { y -> Point(x, y) in galaxies } }
    val expandedY = (0..(lines.size)).filter { y -> (0..(lines[0].length)).none { x -> Point(x, y) in galaxies } }
    galaxies
        .flatMapIndexed { index, from -> galaxies.drop(index + 1).map { to -> from to to } }
        .sumOf { (from, to) ->
            val expX = (min(from.x, to.x)..max(from.x, to.x)).count { it in expandedX }
            val expY = (min(from.y, to.y)..max(from.y, to.y)).count { it in expandedY }
            from.manhattan(to) + (expX + expY) * 1
        }
}
