package net.dericbourg.adventofcode.y2019.d03

import kotlin.math.absoluteValue

data class IndexedPoint(val indices: Iterable<Int>, val point: Point)
data class Distances(val manhattan: Int, val totalSteps: Int)

class DistanceComputer {
    fun computeIntersectionDistances(points: List<List<Point>>): List<Distances> {
        return intersectionPoints(points).map { distances(it) }
    }

    private fun intersectionPoints(pointLists: List<List<Point>>): Iterable<IndexedPoint> {
        return if (pointLists.size < 2) {
            emptyList()
        } else {
            val head = pointLists[0]
            val tail = pointLists.subList(1, pointLists.size)
            val intersections =
                tail.fold(head, { intersection: Iterable<Point>, list: List<Point> -> intersection.intersect(list) })
            intersections.map { point ->
                val indices = pointLists.map { it.indexOf(point) + 1 }
                IndexedPoint(indices, point)
            }
        }
    }

    private fun distances(point: IndexedPoint): Distances {
        return Distances(
            point.point.x.absoluteValue + point.point.y.absoluteValue,
            point.indices.sum()
        )
    }
}