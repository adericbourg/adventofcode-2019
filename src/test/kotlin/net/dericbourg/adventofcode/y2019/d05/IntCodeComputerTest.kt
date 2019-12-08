package net.dericbourg.adventofcode.y2019.d05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IntCodeComputerTest {
    @Test
    fun example1() {
        val computer = IntCodeComputer()
        val result = computer.compute(listOf(1002, 4, 3, 4, 33)) // 33 * 3 = 99 => pos 4
        assertThat(result[4]).isEqualTo(99)


    }

    @Test
    fun example2() {
        val computer = IntCodeComputer()
        val result = computer.compute(listOf(1101, 100, -1, 4, 0)) // 100 + -1 = 99 > pos 4
        assertThat(result[4]).isEqualTo(99)
    }

    @Test
    fun example3_check_output_manually() {
        val computer = IntCodeComputer()
        computer.compute(
            listOf(
                3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
                1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
                999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
            )
        )
    }
}