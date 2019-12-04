package net.dericbourg.adventofcode.y2019.d04

class ValidityChecker {
    fun isValid(password: Int): Boolean {
        val chars = password.toString().toCharArray()
        return containsStrictDouble(chars) && isIncreasing(chars)
    }

    private fun containsStrictDouble(digits: CharArray): Boolean {
        return digits.fold(HashMap<Char, Int>()) { map: MutableMap<Char, Int>, digit: Char ->
            map[digit] = map.getOrDefault(digit, 0) + 1
            map
        }.values.contains(2)
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
