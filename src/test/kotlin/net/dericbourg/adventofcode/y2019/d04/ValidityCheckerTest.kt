package net.dericbourg.adventofcode.y2019.d04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ValidityCheckerTest {

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

    @Test
    fun `112233 should be valid`() {
        val checker = ValidityChecker()
        assertThat(checker.isValid(112233)).isTrue()
    }

    @Test
    fun `123444 should not be valid`() {
        val checker = ValidityChecker()
        assertThat(checker.isValid(123444)).isFalse()
    }

    @Test
    fun `111122 should be valid`() {
        val checker = ValidityChecker()
        assertThat(checker.isValid(111122)).isTrue()
    }
}
