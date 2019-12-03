package net.dericbourg.adventofcode.y2019.d03

data class Point(val x: Int, val y: Int)

class PathComputer {
    fun compute(instructions: List<List<Instruction>>): List<List<Point>> {
        return instructions
            .map { computePoints(it) }
            .filter { it.isNotEmpty() }
    }

    private fun computePoints(instructions: List<Instruction>): List<Point> {
        data class Accumulator(val current: Point, val points: List<Point>)
        return instructions.fold(
            Accumulator(Point(0, 0), mutableListOf()),
            { acc: Accumulator, instruction: Instruction ->
                val moveFunction = when (instruction.direction) {
                    Direction.Up -> { point: Point -> Point(point.x, point.y + 1) }
                    Direction.Down -> { point: Point -> Point(point.x, point.y - 1) }
                    Direction.Right -> { point: Point -> Point(point.x + 1, point.y) }
                    Direction.Left -> { point: Point -> Point(point.x - 1, point.y) }
                }
                var current = acc.current
                val points = mutableListOf<Point>()
                for (i in 1..instruction.distance) {
                    current = moveFunction(current)
                    points.add(current)
                }
                val result = mutableListOf<Point>()
                result.addAll(acc.points)
                result.addAll(points)
                Accumulator(current, result)
            }).points
    }
}
