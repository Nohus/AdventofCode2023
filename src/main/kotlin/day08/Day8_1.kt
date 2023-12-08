package day08

import solve

fun main() = solve { lines ->
    val steps = lines.first()
    val map = lines.drop(2).associate { line ->
        val (from, left, right) = """([A-Z]{3}) = \(([A-Z]{3}), ([A-Z]{3})\)""".toRegex().matchEntire(line)!!.groupValues.drop(1)
        from to listOf(left, right)
    }
    var current = "AAA"
    var count = 0
    while (current != "ZZZ") {
        steps.forEach { current = if (it == 'R') map[current]!![1] else map[current]!![0] }
        count += steps.length
    }
    count
}
