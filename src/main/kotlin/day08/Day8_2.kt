package day08

import solve
import utils.leastCommonMultiple

fun main() = solve { lines ->
    val steps = lines.first()
    val map = lines.drop(2).associate { line ->
        val (from, left, right) = """([A-Z]{3}) = \(([A-Z]{3}), ([A-Z]{3})\)""".toRegex().matchEntire(line)!!.groupValues.drop(1)
        from to listOf(left, right)
    }
    val counts = map.keys.filter { it.endsWith("A") }.map { startingPoint ->
        var current = startingPoint
        var count = 0L
        while (!current.endsWith("Z")) {
            steps.forEach { current = if (it == 'R') map[current]!![1] else map[current]!![0] }
            count += steps.length
        }
        count
    }
    counts.reduce { acc, i -> leastCommonMultiple(acc, i) }
}
