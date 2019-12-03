package net.dericbourg.adventofcode.y2019.d03

import kotlin.math.absoluteValue

class ManhattanDistance {
    fun computeIntersectionDistances(points: List<List<Point>>): List<Int> {
        return intersectionPoints(points).map { distancesFromOrigin(it) }
    }

    private fun intersectionPoints(pointLists: List<List<Point>>): Iterable<Point> {
        return if (pointLists.size < 2) {
            emptyList()
        } else {
            val head = pointLists[0]
            val tail = pointLists.subList(1, pointLists.size)
            tail.fold(head, { intersection: Iterable<Point>, list: List<Point> -> intersection.intersect(list) })
        }
    }

    private fun distancesFromOrigin(point: Point): Int {
        return point.x.absoluteValue + point.y.absoluteValue
    }
}