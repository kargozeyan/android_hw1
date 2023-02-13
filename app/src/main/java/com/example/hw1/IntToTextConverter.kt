package com.example.hw1

import android.util.Log

private val base = mapOf(
    0 to "",
    1 to "one",
    2 to "two",
    3 to "three",
    4 to "four",
    5 to "five",
    6 to "six",
    7 to "seven",
    8 to "eight",
    9 to "nine",
    10 to "ten",
    11 to "eleven",
    12 to "twelve",
    13 to "thirteen",
    14 to "fourteen",
    15 to "fifteen",
    16 to "sixteen",
    17 to "seventeen",
    18 to "eighteen",
    19 to "nineteen"
)

private val tens = mapOf(
    0 to "",
    1 to "",
    2 to "twenty",
    3 to "thirty",
    4 to "forty",
    5 to "fifty",
    6 to "sixty",
    7 to "seventy",
    8 to "eighty",
    9 to "ninety",
)


class IntegerToTextConverter {
    fun convert(n: String): String {
        try {
            return convert(n.toInt())
        } catch (e: java.lang.NumberFormatException) {
            throw ConvertException("The number is out of range")
        }
    }

    fun convert(n: Int): String {
        if (n < 0 || n > 999_999_999)
            throw ConvertException("The number is out of range")
        if (n == 0) {
            return "zero"
        }

        val millionNumber = n / 1_000_000
        var millionText = ""

        if (millionNumber != 0) {
            millionText = "${tripleDigitToText(millionNumber)} million"
        }

        val thousandNumber = (n / 1_000) % 1000
        var thousandText = ""
        if (thousandNumber != 0) {
            thousandText = "${tripleDigitToText(thousandNumber)} thousand"
        }
        val rest = n % 1000

        return "$millionText $thousandText ${tripleDigitToText(rest)}".trim()
    }

    private fun tripleDigitToText(n: Int): String {
        val hundredNumber = n / 100
        var hundredText = ""
        if (hundredNumber != 0) {
            hundredText = "${base[hundredNumber]} hundred"
        }

        val doubleDigit = n % 100
        return "$hundredText ${doubleDigitToText(doubleDigit)}".trim()
    }

    private fun doubleDigitToText(n: Int): String {
        return if (n >= 20) {
            "${tens[n / 10]} ${base[n % 10]}"
        } else {
            base[n]!!;
        }
    }
}

class ConvertException(message: String) : Exception(message)
