package net.dericbourg.adventofcode.y2019.d04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ValidityCheckerTest {

    @Test
    fun `111111 should be valid (double 11, never decreases)`() {
        val checker = ValidityChecker()
        assertThat(checker.isValid(111111)).isTrue()
    }

    @Test
    fun `223450 should not be valid (decreasing pair of digits 50)`() {
        val checker = ValidityChecker()
        assertThat(checker.isValid(223450)).isFalse()
    }

    @Test
    fun `123789 should be valid (no double)`() {
        val checker = ValidityChecker()
        assertThat(checker.isValid(123789)).isFalse()
    }
}
