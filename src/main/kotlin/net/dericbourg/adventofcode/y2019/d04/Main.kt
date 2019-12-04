package net.dericbourg.adventofcode.y2019.d04

object Main

fun main() {
    val inputContent = Main::class.java.getResource("/d04.txt").readText()

    val bounds = inputContent.split("-")
    val passwordRange = (bounds[0].toInt()..bounds[1].toInt())

    val validityChecker = ValidityChecker()
    val counter = BruteForcePasswordCounter(validityChecker)

    val passwordCount = counter.count(passwordRange)
    println("Part 1: $passwordCount")
}
