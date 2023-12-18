package day18

import solve
import utils.Direction
import utils.Direction.*
import utils.Point
import utils.PointL
import utils.printArea
import utils.toGrid
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main() = solve { lines ->
    var current = PointL.ORIGIN
    val points = mutableListOf(current)
    var area = 0L
    var perimeter = 0L
    lines.forEach { line ->
        val (directionText, distanceText) = line.split(" ")
        val direction = when (directionText) {
            "U" -> NORTH
            "D" -> SOUTH
            "L" -> WEST
            "R" -> EAST
            else -> throw RuntimeException()
        }
        val distance = distanceText.toInt()
        perimeter += distance
        current = current.move(direction, distance.toLong())
        area += points.last().x * current.y - current.x * points.last().y
        points += current
    }
    area / 2 - perimeter / 2 + 1 + perimeter
}
