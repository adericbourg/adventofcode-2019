package net.dericbourg.adventofcode.y2019.d04

class BruteForcePasswordCounter(private val checker: ValidityChecker) {

    fun count(range: IntRange): Int {
        return range.filter { checker.isValid(it) }.size
    }
}