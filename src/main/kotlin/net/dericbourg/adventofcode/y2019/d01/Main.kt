package net.dericbourg.adventofcode.y2019.d01

object Main

fun main() {
    val inputFile = Main::class.java.getResource("/d01.txt").readText()
    val masses = inputFile
        .lines()
        .filter { s -> s.isNotBlank() }
        .map { l -> l.toLong() }

    val estimator = FuelAmountEstimator()
    val estimate = estimator.estimateAll(masses)

    println(estimate)
}
