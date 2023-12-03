package day03

import solve

fun main() = solve { lines ->
    data class Number(val value: Int, val x: Int, val y: Int)
    val numbers = mutableListOf<Number>()
    lines.forEachIndexed { y, line ->
        var x = 0
        while (x <= line.lastIndex) {
            if (line[x].isDigit()) {
                val value = line.drop(x).takeWhile { it.isDigit() }.toInt()
                Number(value, x, y).let { numbers += it; x += it.value.toString().length }
            } else x++
        }
    }
    numbers.filter { (value, x, y) ->
        lines.withIndex()
            .filter { it.index in (y - 1)..(y + 1) }
            .flatMap { it.value.drop(maxOf(x - 1, 0)).take(value.toString().length + 2).toList() }
            .any { it != '.' && !it.isDigit() }
    }.sumOf { it.value }
}
