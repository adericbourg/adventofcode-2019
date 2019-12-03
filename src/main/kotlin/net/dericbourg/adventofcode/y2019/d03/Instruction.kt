package net.dericbourg.adventofcode.y2019.d03

data class Instruction(val direction: Direction, val distance: Int)

enum class Direction {
    Up,
    Down,
    Left,
    Right
}