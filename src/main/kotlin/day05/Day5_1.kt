package day05

import solve

fun main() = solve { lines ->
    val seeds = lines.first().substringAfter(" ").split(" ").map { it.toLong() }
    val maps = lines.drop(2).joinToString("\n").split("\n\n").map { section ->
        section.lines().drop(1).associate {
            it.split(" ").map { it.toLong() }.let { (dest, source, length) ->
                source..(source + length) to dest..(dest + length)
            }
        }
    }
    seeds.minOf { seed ->
        maps.fold(seed) { aac, map ->
            map.entries.firstOrNull { aac in it.key }?.let { (source, dest) -> dest.first + (aac - source.first) } ?: aac
        }
    }
}
