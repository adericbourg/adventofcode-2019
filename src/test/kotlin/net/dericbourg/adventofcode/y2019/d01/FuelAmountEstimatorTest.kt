package net.dericbourg.adventofcode.y2019.d01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FuelAmountEstimatorTest {

    @Test
    fun `fuel for mass 0 should be 0`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateFuelForMass(0)

        assertThat(estimate).isEqualTo(0)
    }

    @Test
    fun `fuel for mass 12 should be 2`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateFuelForMass(12)

        assertThat(estimate).isEqualTo(2)
    }

    @Test
    fun `fuel for mass 14 should be 2`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateFuelForMass(14)

        assertThat(estimate).isEqualTo(2)
    }

    @Test
    fun `fuel for mass 1969 should be 966`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateFuelForMass(1969)

        assertThat(estimate).isEqualTo(966)
    }

    @Test
    fun `fuel for mass 100756 should be 50346`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateFuelForMass(100756)

        assertThat(estimate).isEqualTo(50346)
    }

    @Test
    fun `estimateAll should sum estimations`() {
        val estimator = FuelAmountEstimator()

        val estimate = estimator.estimateAll(listOf(0, 12, 14, 1969, 100756))

        assertThat(estimate).isEqualTo(0 + 2 + 2 + 966 + 50346)
    }
}