package net.dericbourg.adventofcode.y2019.d02

object Main

fun main() {
    val inputFile = Main::class.java.getResource("/d02.txt").readText()
    val instructions = inputFile
        .split(",")
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .map { it.toInt() }

    val computer = IntCodeComputer()
    val result = computer.compute(instructions)

    println("Part 1: ${result[0]}")

    (1..99).forEach { noun ->
        (1..99).forEach { verb ->
            val balanced = computer.reset(instructions, noun, verb)
            val maybeResult = computer.computeOperations(balanced)
            if (maybeResult[0] == 19690720) {
                println("Part 2: ${100 * noun + verb}")
            }
        }
    }
}
