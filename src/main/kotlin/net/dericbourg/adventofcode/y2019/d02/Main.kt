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

    println(result[0])
}
