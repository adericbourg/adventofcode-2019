package net.dericbourg.adventofcode.y2019.d05

object Main

fun main() {
    val inputContent = Main::class.java.getResource("/d05.txt").readText()
    val instructions = inputContent
        .split(",")
        .map { it.trim() }
        .filter { it.isNotBlank() }
        .map { it.toInt() }

    val intCodComputer = IntCodeComputer()
    intCodComputer.compute(instructions)
}
