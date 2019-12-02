package net.dericbourg.adventofcode.y2019.d02

class IntCodeComputer {

    fun compute(intCodeProgram: List<Int>): List<Int> {
        val restoredGravity = restoreGravity(intCodeProgram)
        return computeOperations(restoredGravity)
    }

    fun restoreGravity(intCodeProgram: List<Int>): List<Int> {
        val buffer = intCodeProgram.toMutableList()
        buffer[1] = 12
        buffer[2] = 2
        return buffer.toList()
    }

    fun computeOperations(intCodeProgram: List<Int>): List<Int> {
        val computationBuffer = intCodeProgram.toMutableList()
        for (i in 0..computationBuffer.size step 4) {
            val opCode = computationBuffer[i]
            when (bindOpCode(opCode)) {
                ComputationFlowOperation.StoreValue -> applyOperation(computationBuffer, i, opCode)
                ComputationFlowOperation.Stop -> return computationBuffer.toList()
            }
        }
        return emptyList()
    }

    private fun applyOperation(computationBuffer: MutableList<Int>, chunk: Int, opCode: Int) {
        val pos1 = computationBuffer[chunk + 1]
        val pos2 = computationBuffer[chunk + 2]
        val target = computationBuffer[chunk + 3]
        val substitutionValue = getOperation(opCode).apply(computationBuffer[pos1], computationBuffer[pos2])
        computationBuffer[target] = substitutionValue
    }

    private fun bindOpCode(opCode: Int): ComputationFlowOperation {
        return when (opCode) {
            1 -> ComputationFlowOperation.StoreValue
            2 -> ComputationFlowOperation.StoreValue
            99 -> ComputationFlowOperation.Stop
            else -> throw IllegalArgumentException("Invalid opcode: $opCode")
        }
    }

    private fun getOperation(opCode: Int): OpCodeOperation {
        return when (opCode) {
            1 -> OpCodeOperation.Addition
            2 -> OpCodeOperation.Multiplication
            else -> throw IllegalArgumentException("Invalid operation opcode: $opCode")
        }
    }

    sealed class ComputationFlowOperation {
        object Stop : ComputationFlowOperation()
        object StoreValue : ComputationFlowOperation()
    }

    sealed class OpCodeOperation {

        abstract fun apply(a: Int, b: Int): Int

        object Addition : OpCodeOperation() {
            override fun apply(a: Int, b: Int): Int {
                return a + b
            }
        }

        object Multiplication : OpCodeOperation() {
            override fun apply(a: Int, b: Int): Int {
                return a * b
            }
        }
    }
}