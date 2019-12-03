package net.dericbourg.adventofcode.y2019.d03

class InstructionMapper {

    fun map(input: String): List<List<Instruction>> {
        return input
            .lines()
            .map { line ->
                line
                    .split(",")
                    .map { it.trim() }
                    .filter { it.isNotBlank() }
                    .map { mapInstruction(it) }
            }
    }

    private fun mapInstruction(s: String): Instruction {
        val direction = when (s[0]) {
            'U' -> Direction.Up
            'D' -> Direction.Down
            'L' -> Direction.Left
            'R' -> Direction.Right
            else -> throw RuntimeException("Unknown direction code: ${s[0]}")
        }
        val distance = s.subSequence(1, s.length).toString().toInt()
        return Instruction(direction, distance)
    }
}

