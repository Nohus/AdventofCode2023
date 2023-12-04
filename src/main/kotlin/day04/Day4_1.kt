package day04

import solve
import kotlin.math.pow

fun main() = solve { lines ->
    lines.sumOf { line ->
        val winning = line.substringAfter(": ").substringBefore("|").chunked(3).map { it.trim().toInt() }
        val count = line.substringAfter("| ").chunked(3).map { it.trim().toInt() }.count { it in winning }
        2.0.pow(count - 1).toInt()
    }
}
