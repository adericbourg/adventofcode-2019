package net.dericbourg.adventofcode.y2019.d06

data class Orbit(val centralMass: String, val orbitingObject: String) {
    companion object {

        fun parseOrbits(input: String): List<Orbit> {
            return input.lines()
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .map(::parseSingleOrbit)
        }

        private fun parseSingleOrbit(input: String): Orbit {
            val split = input.split(")")
            return Orbit(split[0], split[1])
        }
    }
}
