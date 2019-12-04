package net.dericbourg.adventofcode.y2019.d03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Test {

    @Test
    fun `distance for R75,D30,R83,U83,L12,D49,R71,U7,L72 | U62,R66,U55,R34,D71,R55,D58,R83 should be 159`() {
        val input = """R75,D30,R83,U83,L12,D49,R71,U7,L72
                       U62,R66,U55,R34,D71,R55,D58,R83"""
        val mapper = InstructionMapper()
        val pathComputer = PathComputer()
        val distanceComputer = DistanceComputer()

        val minDistance = distanceComputer.computeIntersectionDistances(
            pathComputer.compute(
                mapper.map(input)
            )
        ).map { it.manhattan }.min()

        assertThat(minDistance).isEqualTo(159)
    }

    @Test
    fun `distance for R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51 | U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 should be 135`() {
        val input = """R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
                       U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"""
        val mapper = InstructionMapper()
        val pathComputer = PathComputer()
        val distanceComputer = DistanceComputer()

        val minDistance = distanceComputer.computeIntersectionDistances(
            pathComputer.compute(
                mapper.map(input)
            )
        ).map { it.manhattan }.min()

        assertThat(minDistance).isEqualTo(135)
    }

    @Test
    fun `steps for R75,D30,R83,U83,L12,D49,R71,U7,L72 | U62,R66,U55,R34,D71,R55,D58,R83 should be 610`() {
        val input = """R75,D30,R83,U83,L12,D49,R71,U7,L72
                       U62,R66,U55,R34,D71,R55,D58,R83"""
        val mapper = InstructionMapper()
        val pathComputer = PathComputer()
        val distanceComputer = DistanceComputer()

        val minDistance = distanceComputer.computeIntersectionDistances(
            pathComputer.compute(
                mapper.map(input)
            )
        ).map { it.totalSteps }.min()

        assertThat(minDistance).isEqualTo(610)
    }

    @Test
    fun `steps for R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51 | U98,R91,D20,R16,D67,R40,U7,R15,U6,R7 should be 410`() {
        val input = """R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
                       U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"""
        val mapper = InstructionMapper()
        val pathComputer = PathComputer()
        val distanceComputer = DistanceComputer()

        val minDistance = distanceComputer.computeIntersectionDistances(
            pathComputer.compute(
                mapper.map(input)
            )
        ).map { it.totalSteps }.min()

        assertThat(minDistance).isEqualTo(410)
    }
}