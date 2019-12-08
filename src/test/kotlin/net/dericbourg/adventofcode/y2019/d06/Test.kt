package net.dericbourg.adventofcode.y2019.d06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Test {
    @Test
    fun example() {
        val input = """ COM)B
                        B)C
                        C)D
                        D)E
                        E)F
                        B)G
                        G)H
                        D)I
                        E)J
                        J)K
                        K)L"""

        val orbits = Orbit.parseOrbits(input)
        val graph = OrbitGraph.buildGraph(orbits)
        val count = graph.countOrbits()

        assertThat(count).isEqualTo(42)
    }

    @Test
    fun part2Example() {
        val input = """ COM)B
                        B)C
                        C)D
                        D)E
                        E)F
                        B)G
                        G)H
                        D)I
                        E)J
                        J)K
                        K)L
                        K)YOU
                        I)SAN"""

        val orbits = Orbit.parseOrbits(input)
        val graph = OrbitGraph.buildGraph(orbits)
        val path = graph.shortestPath("YOU", "SAN")

        assertThat(path.map { it.name }).isEqualTo(listOf("K", "J", "E", "D", "I"))
    }
}