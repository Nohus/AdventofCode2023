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
            aac.flatMap { getOutputRanges(map, it) }
        }
    }.minOf { it.first }
}

fun getOutputRanges(map: Map<LongRange, LongRange>, input: LongRange): List<LongRange> {
    val mappedInputRanges = mutableListOf<LongRange>()
    val outputRanges = map.entries.mapNotNull { (source, dest) ->
        val start = maxOf(source.first, input.first)
        val end = minOf(source.last, input.last)
        if (start <= end) {
            mappedInputRanges += start..end
            (dest.first - source.first).let { (start + it)..(end + it) }
        } else null
    }
    val cuts = listOf(input.first) + mappedInputRanges.flatMap { listOf(it.first, it.last) } + listOf(input.last)
    val unmappedInputRanges = cuts.chunked(2).mapNotNull { (first, second) ->
        if (second > first) if (second == cuts.last()) first..second else first..<second else null
    }
    return outputRanges + unmappedInputRanges
}
