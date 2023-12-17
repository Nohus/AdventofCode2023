package day16

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import solveSuspending
import utils.Direction
import utils.Direction.*
import utils.Point
import utils.toGrid

fun main() = solveSuspending { lines ->
    val grid = lines.toGrid()
    val maxX = grid.keys.maxOf { it.x }
    val maxY = grid.keys.maxOf { it.y }
    data class Beam(val position: Point, val direction: Direction)
    val starts = mutableListOf<Beam>()
    for (x in 0..maxX) starts += listOf(Beam(Point(x, -1), SOUTH), Beam(Point(x, maxY + 1), NORTH))
    for (y in 0..maxY) starts += listOf(Beam(Point(-1, y), EAST), Beam(Point(maxX + 1, y), WEST))
    starts.map { start ->
        async {
            val beams = mutableListOf(start)
            val beamsHistory = mutableSetOf<Beam>()
            while (beams.isNotEmpty()) {
                val beam = beams.removeFirst()
                val next = beam.position.move(beam.direction)
                if (next !in grid || beam.copy(position = next) in beamsHistory) continue else beamsHistory += beam.copy(position = next)
                beams += when (grid[next]) {
                    '.' -> listOf(beam.direction)
                    '/' -> listOf(mapOf(NORTH to EAST, EAST to NORTH, SOUTH to WEST, WEST to SOUTH)[beam.direction]!!)
                    '\\' -> listOf(mapOf(NORTH to WEST, EAST to SOUTH, SOUTH to EAST, WEST to NORTH)[beam.direction]!!)
                    '-' -> mapOf(NORTH to listOf(WEST, EAST), EAST to listOf(beam.direction), SOUTH to listOf(WEST, EAST), WEST to listOf(beam.direction))[beam.direction]!!
                    '|' -> mapOf(NORTH to listOf(beam.direction), EAST to listOf(NORTH, SOUTH), SOUTH to listOf(beam.direction), WEST to listOf(NORTH, SOUTH))[beam.direction]!!
                    else -> throw RuntimeException()
                }.map { Beam(next, it) }
            }
            beamsHistory.map { it.position }.toSet().size
        }
    }.awaitAll().max()
}
