package day01

import solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        line.filter { it.isDigit() }.let { "${it.first()}${it.last()}" }.toInt()
    }
}
