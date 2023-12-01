package day01

import solve

fun main() = solve { lines ->
    val digits = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    lines.sumOf { line ->
        line.indices.mapNotNull { i ->
            digits.indexOfFirst { line.drop(i).startsWith(it) }.takeIf { it > -1 } ?: line[i].digitToIntOrNull()
        }.let { it.first() * 10 + it.last() }
    }
}
