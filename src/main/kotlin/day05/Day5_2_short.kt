package day05

import solve

fun main() = solve { lines ->
    val seeds = lines.first().substringAfter(" ").split(" ").map { it.toLong() }.chunked(2).map { it.first()..<it.first() + it.last() }
    val maps = lines.drop(2).joinToString("\n").split("\n\n").map { section ->
        section.lines().drop(1).associate {
            it.split(" ").map { it.toLong() }.let { (dest, source, length) ->
                source..(source + length) to dest..(dest + length)
            }
        }
    }
    seeds.flatMap { seedsRange ->
        maps.fold(listOf(seedsRange)) { aac, map ->
            aac.flatMap {
                map.entries.mapNotNull { (source, dest) ->
                    (maxOf(source.first, it.first) to minOf(source.last, it.last)).let { (start, end) ->
                        if (start <= end) (dest.first - source.first).let { (start + it)..(end + it) } else null
                    }
                }
            }
        }
    }.minOf { it.first }
}
