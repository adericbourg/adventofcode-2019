package net.dericbourg.adventofcode.y2019.d04

class ValidityChecker {
    fun isValid(password: Int): Boolean {
        val chars = password.toString().toCharArray()
        return containsDouble(chars) && isIncreasing(chars)
    }

    private fun containsDouble(digits: CharArray): Boolean {
        val uniqueDigitCount = digits.toSet()
        return digits.size > uniqueDigitCount.size
    }

    private fun isIncreasing(digits: CharArray): Boolean {
        for (i in (0..(digits.size - 2))) {
            if (digits[i] > digits[i + 1]) {
                return false
            }
        }
        return true
    }
}
