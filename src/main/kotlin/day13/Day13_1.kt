package day13

import solveRaw
import utils.Point

fun main() = solveRaw { lines ->
    lines.split("\n\n").map { block ->
        val map = mutableMapOf<Point, Char>()
        block.lines().forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                map[Point(x, y)] = c
            }
        }
        val maxX = map.maxOf { it.key.x }
        val maxY = map.maxOf { it.key.y }
        reflectionLine@ for (reflectionX in 0 until maxX) {
            for (x in 0..maxX) {
                for (y in 0..maxY) {
                    val reflectedPoint = Point(x - (((x - reflectionX) * 2) - 1), y)
                    if (map[reflectedPoint] != null && map[Point(x, y)] != map[reflectedPoint]) continue@reflectionLine
                }
            }
            return@map reflectionX + 1
        }
        reflectionLine@ for (reflectionY in 0 until maxY) {
            for (x in 0..maxX) {
                for (y in 0..maxY) {
                    val reflectedPoint = Point(x, y - (((y - reflectionY) * 2) - 1))
                    if (map[reflectedPoint] != null && map[Point(x, y)] != map[reflectedPoint]) continue@reflectionLine
                }
            }
            return@map (reflectionY + 1) * 100
        }
        throw RuntimeException()
    }.sum()
}
