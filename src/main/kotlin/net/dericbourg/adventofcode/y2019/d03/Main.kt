package net.dericbourg.adventofcode.y2019.d03

object Main

fun main() {
    val inputContent = Main::class.java.getResource("/d03.txt").readText()

    val mapper = InstructionMapper()
    val pathComputer = PathComputer()
    val distance = DistanceComputer()

    val instructions = mapper.map(inputContent)
    val paths = pathComputer.compute(instructions)
    val distances = distance.computeIntersectionDistances(paths)

    println("Part 1: ${distances.map { it.manhattan }.min()}")
    println("Part 2: ${distances.map { it.totalSteps }.min()}")
}
