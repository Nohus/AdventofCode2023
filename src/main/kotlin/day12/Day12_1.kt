package day12

import solve
import utils.Point
import kotlin.math.max
import kotlin.math.min

fun main() = solve { lines ->
    lines.sumOf { line ->
        val (symbols, groupSizesText) = line.split(" ")
        val groupSizes = groupSizesText.split(",").map { it.toInt() }
        val incomplete = mutableListOf(symbols)
        val complete = mutableListOf<String>()
        while (incomplete.isNotEmpty()) {
            val current = incomplete.removeFirst()
            listOf(current.replaceFirst('?', '.'), current.replaceFirst('?', '#')).forEach { option ->
                if ('?' in option) incomplete += option
                else if (option.split(".").filter { it.isNotEmpty() }.map { it.length } == groupSizes) complete += option
            }
        }
        complete.size
    }
}
