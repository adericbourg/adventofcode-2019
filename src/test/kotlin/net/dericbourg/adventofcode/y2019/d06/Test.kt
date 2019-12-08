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
        val count = OrbitGraph.countOrbits(orbits)

        assertThat(count).isEqualTo(42)
    }
}