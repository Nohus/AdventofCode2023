package day06

import solve

fun main() = solve { lines ->
    val (time, distance) = lines.map { it.substringAfter(" ").replace(" ", "").toLong() }
    (0..time).map { (time - it) * it }.count { it > distance }
}
