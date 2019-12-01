package net.dericbourg.adventofcode.y2019.d01

class FuelAmountEstimator {

    fun estimateAll(masses: Iterable<Long>): Long {
        return masses
            .map { m -> estimateForMass(m) }
            .sum()
    }

    fun estimateForMass(mass: Long): Long {
        return (mass / 3) - 2
    }
}
