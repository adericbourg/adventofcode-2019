package net.dericbourg.adventofcode.y2019.d05

import net.dericbourg.adventofcode.y2019.d05.IntCodeComputer.Operation.Companion.bindOperation

class IntCodeComputer {

    fun compute(intCodeProgram: List<Int>): List<Int> {
        val computationBuffer = intCodeProgram.toMutableList()

        var loopIndex = 0
        while (loopIndex < computationBuffer.size) {
            val opCode = computationBuffer[loopIndex]
            when (val operation = bindOperation(opCode)) {
                is Operation.Stop -> return computationBuffer.toList()
                else -> {
                    loopIndex = operation.apply(computationBuffer, loopIndex)
                }
            }
        }

        return emptyList()
    }

    sealed class Operation(val modes: Array<Mode>) {
        companion object {
            fun bindOperation(instruction: Int): Operation {
                val paddedInstruction = instruction.toString().padStart(5, '0')
                val modes = bindModes(paddedInstruction)
                return when (val opCode = paddedInstruction.substring(3, 5)) {
                    "01" -> Addition(modes)
                    "02" -> Multiplication(modes)
                    "03" -> Input
                    "04" -> Output
                    "05" -> JumpIfTrue(modes)
                    "06" -> JumpIfFalse(modes)
                    "07" -> LessThan(modes)
                    "08" -> Equals(modes)
                    "99" -> Stop
                    else -> throw IllegalArgumentException("Invalid opcode: '$opCode'")
                }
            }

            private fun bindModes(instruction: String): Array<Mode> {
                return arrayOf(
                    bindMode(instruction[2]),
                    bindMode(instruction[1]),
                    bindMode(instruction[0])
                )
            }

            private fun bindMode(modeCode: Char): Mode {
                return when (modeCode) {
                    '0' -> Mode.Position
                    '1' -> Mode.Immediate
                    else -> throw IllegalArgumentException("Unsupported mode: '${modeCode}'")
                }
            }
        }

        abstract fun apply(instructions: MutableList<Int>, currentIndex: Int): Int
        protected abstract val offset: Int

        class Addition(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                val param3 = instructions[currentIndex + 3]

                val a = modes[0].getValue(instructions, param1)
                val b = modes[1].getValue(instructions, param2)
                val targetIndex = param3 // FIXME?

                instructions[targetIndex] = a + b

                return currentIndex + offset
            }

            override val offset: Int
                get() = 4
        }

        class Multiplication(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                val param3 = instructions[currentIndex + 3]

                val a = modes[0].getValue(instructions, param1)
                val b = modes[1].getValue(instructions, param2)
                val targetIndex = param3 // FIXME?

                instructions[targetIndex] = a * b

                return currentIndex + offset
            }

            override val offset: Int
                get() = 4
        }

        object Input : Operation(emptyArray()) {
            private const val Input = 5

            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val target = instructions[currentIndex + 1]
                instructions[target] = Input

                return currentIndex + offset
            }

            override val offset: Int
                get() = 2
        }

        object Output : Operation(emptyArray()) {

            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val parameter = instructions[currentIndex + 1]
                println(instructions[parameter])

                return currentIndex + offset
            }

            override val offset: Int
                get() = 2
        }

        class JumpIfTrue(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                return when (modes[0].getValue(instructions, param1)) {
                    0 -> currentIndex + offset
                    else -> {
                        param2
                    }
                }
            }

            override val offset: Int
                get() = 3
        }

        class JumpIfFalse(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                return when (modes[0].getValue(instructions, param1)) {
                    0 -> {
                        param2
                    }
                    else -> currentIndex + offset
                }
            }

            override val offset: Int
                get() = 3
        }

        class LessThan(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                val param3 = instructions[currentIndex + 3]

                val a = modes[0].getValue(instructions, param1)
                val b = modes[1].getValue(instructions, param2)

                instructions[param3] = if (a < b) 1 else 0

                return currentIndex + offset
            }

            override val offset: Int
                get() = 4
        }

        class Equals(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                val param3 = instructions[currentIndex + 3]

                val a = modes[0].getValue(instructions, param1)
                val b = modes[1].getValue(instructions, param2)

                instructions[param3] = if (a == b) 1 else 0

                return currentIndex + offset
            }

            override val offset: Int
                get() = 4
        }

        object Stop : Operation(emptyArray()) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int): Int {
                throw UnsupportedOperationException()
            }

            override val offset: Int
                get() = throw UnsupportedOperationException()
        }
    }

    sealed class Mode {
        abstract fun getValue(instructions: List<Int>, parameter: Int): Int

        object Position : Mode() {
            override fun getValue(instructions: List<Int>, parameter: Int): Int {
                return instructions[parameter]
            }
        }

        object Immediate : Mode() {
            override fun getValue(instructions: List<Int>, parameter: Int): Int {
                return parameter
            }
        }
    }
}