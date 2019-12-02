package net.dericbourg.adventofcode.y2019.d02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IntCodeComputerTest {

    @Test
    fun `restoreGravity should replace position 1 with the value 12 and replace position 2 with the value 2`() {
        val input = listOf(0, 1, 2, 3)
        val computer = IntCodeComputer()

        val result = computer.restoreGravity(input)

        assertThat(result).isEqualTo(listOf(0, 12, 2, 3))
    }

    @Test
    fun `result for 1,0,0,0,99 should be 2,0,0,0,99`() {
        val computer = IntCodeComputer()

        val result = computer.computeOperations(listOf(1, 0, 0, 0, 99))

        assertThat(result).isEqualTo(listOf(2, 0, 0, 0, 99))
    }

    @Test
    fun `result for 2,3,0,3,99 should be 2,3,0,6,99`() {
        val computer = IntCodeComputer()

        val result = computer.computeOperations(listOf(2, 3, 0, 3, 99))

        assertThat(result).isEqualTo(listOf(2, 3, 0, 6, 99))
    }

    @Test
    fun `result for 2,4,4,5,99,0 should be 2,4,4,5,99,9801`() {
        val computer = IntCodeComputer()

        val result = computer.computeOperations(listOf(2, 4, 4, 5, 99, 0))

        assertThat(result).isEqualTo(listOf(2, 4, 4, 5, 99, 9801))
    }

    @Test
    fun `result for 1,1,1,4,99,5,6,0,99 should be 30,1,1,4,2,5,6,0,99`() {
        val computer = IntCodeComputer()

        val result = computer.computeOperations(listOf(2, 4, 4, 5, 99, 0))

        assertThat(result).isEqualTo(listOf(2, 4, 4, 5, 99, 9801))
    }
}