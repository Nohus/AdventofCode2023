package day02

import solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        line.substringAfter(": ").split("; ").map { set ->
            set.split(", ").map { cubes ->
                val n = cubes.substringBefore(" ").toInt()
                val color = cubes.substringAfter(" ")
                if (color == "red" && n > 12 || color == "green" && n > 13 || color == "blue" && n > 14) return@sumOf 0
            }
        }
        line.substringAfter(" ").substringBefore(":").toInt()
    }
}
