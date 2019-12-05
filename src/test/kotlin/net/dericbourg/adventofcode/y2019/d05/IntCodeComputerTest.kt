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
}