package net.dericbourg.adventofcode.y2019.d06

object Main

fun main() {
    val inputContent = Main::class.java.getResource("/d06.txt").readText()

    val orbits = Orbit.parseOrbits(inputContent)
    val count = OrbitGraph.countOrbits(orbits)
    println(count)
}
