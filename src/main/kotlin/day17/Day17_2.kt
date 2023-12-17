package day17

import solve
import utils.Direction
import utils.Point
import utils.toGrid

fun main() = solve { lines ->
    val grid = lines.toGrid { it.digitToInt() }
    val target = Point(grid.keys.maxOf { it.x }, grid.keys.maxOf { it.y })
    data class State(val point: Point, val from: Direction, val line: Int)
    val unvisited = mutableListOf(
        State(Point(0, 0), Direction.EAST, 0) to 0,
    )
    val visited = mutableMapOf<State, Int>()
    while (unvisited.isNotEmpty()) {
        val (current, currentLoss) = unvisited.minBy { it.second }
        if (current.point == target && current.line >= 4) {
            return@solve currentLoss
        }
        unvisited -= current to currentLoss
        visited += current to currentLoss
        Direction.values()
            .asSequence()
            .map { it to current.point.move(it) }
            .filter { (direction, neighbor) -> neighbor in grid }
            .filter { (direction, neighbor) -> direction != current.from.reverse() }
            .filter { (direction, neighbor) -> direction != current.from || current.line < 10 }
            .filter { (direction, neighbor) -> direction == current.from || current.line >= 4 }
            .forEach { (direction, neighbor) ->
                val line = if (direction == current.from) current.line + 1 else 1
                val newLoss = currentLoss + grid.getValue(neighbor)
                val new = State(neighbor, direction, line)
                val existingVisited = visited[new]
                if (existingVisited == null || existingVisited > newLoss) {
                    val existingBetter = unvisited.firstOrNull { (state, loss) ->
                        new == state && loss <= newLoss
                    }
                    if (existingBetter == null) {
                        unvisited += new to newLoss
                    }
                }
            }
    }
}
