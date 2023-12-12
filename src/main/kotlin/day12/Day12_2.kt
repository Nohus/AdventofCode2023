package day12

import solve

fun main() = solve { lines ->
    lines.sumOf { line ->
        val symbols = List(5) { line.substringBefore(" ") }.joinToString("?")
        val amounts = List(5) { line.substringAfter(" ").split(",").map { it.toInt() } }.flatten()
        getArrangements(mutableMapOf(), symbols, amounts, 0, 0, 0)
    }
}

fun getArrangements(cache: MutableMap<Triple<Int, Int, Int>, Long>, text: String, groups: List<Int>, index: Int, groupIndex: Int, partialCount: Int): Long {
    val key = Triple(index, groupIndex, partialCount)
    cache[key]?.let { return it }
    var arrangements = 0L
    if (index > text.lastIndex || text[index] != '#') {
        if (partialCount == 0) {
            if (index > text.lastIndex) return if (groupIndex == groups.size) 1 else 0
            arrangements += getArrangements(cache, text, groups, index + 1, groupIndex, 0)
        } else if (partialCount == groups.getOrNull(groupIndex)) {
            arrangements += getArrangements(cache, text, groups, index + 1, groupIndex + 1, 0)
        }
    }
    if (index <= text.lastIndex && text[index] != '.') {
        arrangements += getArrangements(cache, text, groups, index + 1, groupIndex, partialCount + 1)
    }
    return arrangements.also { cache[key] = it }
}
