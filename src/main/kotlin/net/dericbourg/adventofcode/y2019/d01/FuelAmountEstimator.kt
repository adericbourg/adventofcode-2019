package net.dericbourg.adventofcode.y2019.d01

import java.lang.Long.max

class FuelAmountEstimator {

    fun estimateAll(masses: Iterable<Long>): Long {
        return masses
            .map { m -> estimateFuelForMass(m) }
            .sum()
    }

    fun estimateFuelForMass(mass: Long): Long {
        tailrec fun inner(acc: Long, delta: Long): Long {
            val massIncrease = computeFuelForMass(delta)
            return if (massIncrease == 0L) acc + delta
            else inner(acc + delta, massIncrease)
        }

        val fuelForInitialMass = computeFuelForMass(mass)
        return inner(0, fuelForInitialMass)
    }

    private fun computeFuelForMass(mass: Long): Long {
        return max(0L, (mass / 3) - 2)
    }
}
