package day04

import solve

fun main() = solve { lines ->
    val cardsWithScore = lines.mapIndexed { index, line ->
        val winning = line.substringAfter(": ").substringBefore("|").chunked(3).map { it.trim().toInt() }
        (index + 1) to line.substringAfter("| ").chunked(3).map { it.trim().toInt() }.count { it in winning }
    }
    var total = 0
    val winningCards = cardsWithScore.map { it.first }.toMutableList()
    while (winningCards.isNotEmpty()) {
        val index = winningCards.first()
        val countOfCard = winningCards.count { it == index }
        winningCards.removeAll { it == index }
        val copies = cardsWithScore.drop(index).take(cardsWithScore.first { it.first == index }.second).map { it.first }
        winningCards += List(countOfCard) { copies }.flatten()
        total += countOfCard
    }
    total
}
