package day09

import solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        val sequences = mutableListOf(line.split(" ").map { it.toInt() })
        while (sequences.last().any { it != 0 }) sequences += sequences.last().windowed(2).map { (a, b) -> b - a }
        sequences.map { it.first() }.reversed().reduce { acc, i -> i - acc }
    }
}
