package day15

import solve

fun main() = solve { lines ->
    data class Lens(val label: String, val focal: Int)
    val boxes = MutableList(256) { mutableListOf<Lens>() }
    lines.first().split(",").forEach { step ->
        val label = step.substringBefore("=").substringBefore("-")
        val hash = label.fold(0) { acc, char -> ((acc + char.code) * 17) % 256 }
        if ('=' in step) {
            val lens = Lens(label, step.substringAfter("=").toInt())
            val index = boxes[hash].indexOfFirst { it.label == label }
            if (index > -1) boxes[hash][index] = lens else boxes[hash] += lens
        } else {
            boxes[hash].removeIf { it.label == label }
        }
    }
    boxes.flatMapIndexed { boxIndex, box ->
        box.mapIndexed { lensIndex, lens -> (1 + boxIndex) * (lensIndex + 1) * lens.focal }
    }.sum()
}
