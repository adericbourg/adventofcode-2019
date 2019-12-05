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
                    operation.apply(computationBuffer, loopIndex)
                    loopIndex += operation.offset
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
                    "99" -> Stop
                    else -> throw IllegalArgumentException("Invalid operation opcode: '$opCode'")
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

        abstract fun apply(instructions: MutableList<Int>, currentIndex: Int)
        abstract val offset: Int

        class Addition(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int) {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                val param3 = instructions[currentIndex + 3]

                val a = modes[0].getValue(instructions, param1)
                val b = modes[1].getValue(instructions, param2)
                val targetIndex = param3 // FIXME?

                instructions[targetIndex] = a + b
            }

            override val offset: Int
                get() = 4
        }

        class Multiplication(modes: Array<Mode>) : Operation(modes) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int) {
                val param1 = instructions[currentIndex + 1]
                val param2 = instructions[currentIndex + 2]
                val param3 = instructions[currentIndex + 3]

                val a = modes[0].getValue(instructions, param1)
                val b = modes[1].getValue(instructions, param2)
                val targetIndex = param3 // FIXME?

                instructions[targetIndex] = a * b
            }

            override val offset: Int
                get() = 4
        }

        object Input : Operation(emptyArray()) {
            private const val Input = 1

            override fun apply(instructions: MutableList<Int>, currentIndex: Int) {
                val target = instructions[currentIndex + 1]
                instructions[target] = Input
            }

            override val offset: Int
                get() = 2
        }

        object Output : Operation(emptyArray()) {

            override fun apply(instructions: MutableList<Int>, currentIndex: Int) {
                val parameter = instructions[currentIndex + 1]
                println(instructions[parameter])
            }

            override val offset: Int
                get() = 2
        }

        object Stop : Operation(emptyArray()) {
            override fun apply(instructions: MutableList<Int>, currentIndex: Int) {
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