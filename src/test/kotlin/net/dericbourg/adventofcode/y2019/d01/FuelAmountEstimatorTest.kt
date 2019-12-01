package net.dericbourg.adventofcode.y2019.d01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FuelAmountEstimatorTest {

    @Test
    fun `fuel for mass 12 should be 2`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateForMass(12)

        assertThat(estimate).isEqualTo(2)
    }

    @Test
    fun `fuel for mass 14 should be 2`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateForMass(14)

        assertThat(estimate).isEqualTo(2)
    }

    @Test
    fun `fuel for mass 1969 should be 654`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateForMass(1969)

        assertThat(estimate).isEqualTo(654)
    }

    @Test
    fun `fuel for mass 100756 should be 33583`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateForMass(100756)

        assertThat(estimate).isEqualTo(33583)
    }

    @Test
    fun `estimateAll should sum estimations`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateAll(listOf(12, 14, 1969, 100756))

        assertThat(estimate).isEqualTo(2 + 2 + 654 + 33583)
    }
}