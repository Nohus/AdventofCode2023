package day02

import solve
import kotlin.math.max

fun main() = solve { lines ->
    lines.sumOf { line ->
        val max = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
        line.substringAfter(": ").split("; ").forEach { set ->
            set.split(", ").map { cubes ->
                val color = cubes.substringAfter(" ")
                max[color] = max(max[color]!!, cubes.substringBefore(" ").toInt())
            }
        }
        max["red"]!! * max["green"]!! * max["blue"]!!
    }
}
