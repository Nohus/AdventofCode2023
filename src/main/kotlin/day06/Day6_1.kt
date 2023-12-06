package day06

import solve

fun main() = solve { lines ->
    lines.map { it.split(" ").mapNotNull { it.toIntOrNull() } }.let { (t, d) -> t.zip(d) }
        .map { (time, distance) -> (0..time).map { (time - it) * it }.count { it > distance } }
        .reduce { acc, i -> acc * i }
}
