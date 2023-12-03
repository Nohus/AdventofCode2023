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
    fun Int.around() = (this - 1)..(this + 1)
    var total = 0
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char == '*') numbers
                .filter { it.y in y.around() && listOf(it.x, it.x + it.value.toString().lastIndex).any { it in x.around()} }
                .takeIf { it.size == 2 }?.let { total += it[0].value * it[1].value }
        }
    }
    total
}
