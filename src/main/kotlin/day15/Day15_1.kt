package day15

import solve

fun main() = solve { lines ->
    lines.first().split(",").map { text ->
        text.fold(0) { acc, char -> ((acc + char.code) * 17) % 256 }
    }.sum()
}
