package day07

import solve

fun main() = solve { lines ->
    data class Hand(val cards: List<Int>, val groups: List<Int>, val bid: Int)
    val values = listOf('T', 'Q', 'K', 'A')
    lines
        .map { it.split(" ") }.map { (text, bid) ->
            val cards = text.map { card -> values.indexOf(card).let { if (it > -1) it + 10 else card.digitToIntOrNull() ?: 1 } }
            val groups = (2..13)
                .map { swap -> cards.map { if (it == 1) swap else it }.groupBy { it }.map { it.value.size }.sortedByDescending { it } }
                .sortedWith(compareBy({ it[0] }, { it.getOrNull(1) }))
                .last()
            Hand(cards, groups, bid.toInt())
        }
        .sortedWith(compareBy({ it.groups[0] }, { it.groups.getOrNull(1) }, { it.cards[0] }, { it.cards[1] }, { it.cards[2] }, { it.cards[3] }, { it.cards[4] }))
        .mapIndexed { index, hand -> (index + 1) * hand.bid }
        .sum()
}
