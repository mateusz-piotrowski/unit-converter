package converter

import java.util.Scanner

enum class UnitType { LENGTH, WEIGHT, TEMPERATURE, UNKNOWN }

enum class MeasureUnit(
    val type: UnitType,
    val ratio: Double, // Relative to Meter or Gram
    val singular: String,
    val plural: String,
    val synonyms: List<String>
) {
    // Lengths - Using your exact snippet constants
    METER(UnitType.LENGTH, 1.0, "meter", "meters", listOf("m", "meter", "meters")),
    KILOMETER(UnitType.LENGTH, 1000.0, "kilometer", "kilometers", listOf("km", "kilometer", "kilometers")),
    MILLIMETER(UnitType.LENGTH, 0.001, "millimeter", "millimeters", listOf("mm", "millimeter", "millimeters")),
    CENTIMETER(UnitType.LENGTH, 0.01, "centimeter", "centimeters", listOf("cm", "centimeter", "centimeters")),
    MILE(UnitType.LENGTH, 1609.35, "mile", "miles", listOf("mi", "mile", "miles")),
    YARD(UnitType.LENGTH, 0.9144, "yard", "yards", listOf("yd", "yard", "yards")),
    FOOT(UnitType.LENGTH, 0.3048, "foot", "feet", listOf("ft", "foot", "feet")),
    INCH(UnitType.LENGTH, 0.0254, "inch", "inches", listOf("in", "inch", "inches")),

    // Weights - Using your exact snippet constants
    GRAM(UnitType.WEIGHT, 1.0, "gram", "grams", listOf("g", "gram", "grams")),
    KILOGRAM(UnitType.WEIGHT, 1000.0, "kilogram", "kilograms", listOf("kg", "kilogram", "kilograms")),
    MILLIGRAM(UnitType.WEIGHT, 0.001, "milligram", "milligrams", listOf("mg", "milligram", "milligrams")),
    POUND(UnitType.WEIGHT, 453.592, "pound", "pounds", listOf("lb", "pound", "pounds")),
    OUNCE(UnitType.WEIGHT, 28.3495, "ounce", "ounces", listOf("oz", "ounce", "ounces")),

    // Temperatures - Logic handled separately
    CELSIUS(UnitType.TEMPERATURE, 0.0, "degree Celsius", "degrees Celsius", listOf("c", "dc", "celsius", "degree celsius", "degrees celsius")),
    FAHRENHEIT(UnitType.TEMPERATURE, 0.0, "degree Fahrenheit", "degrees Fahrenheit", listOf("f", "df", "fahrenheit", "degree fahrenheit", "degrees fahrenheit")),
    KELVIN(UnitType.TEMPERATURE, 0.0, "kelvin", "kelvins", listOf("k", "kelvin", "kelvins")),

    NULL(UnitType.UNKNOWN, 0.0, "???", "???", emptyList());

    companion object {
        fun find(name: String): MeasureUnit {
            val lower = name.lowercase()
            for (unit in values()) {
                if (lower in unit.synonyms) return unit
            }
            return NULL
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)

    while (true) {
        println("Enter what you want to convert (or exit):")
        if (!scanner.hasNext()) break
        val input = scanner.nextLine().lowercase()
        if (input == "exit") break

        val tokens = input.split(" ")

        try {
            // 1. Parse Number
            val amount = tokens[0].toDouble()

            // 2. Parse Source Unit (Handle "degree(s) unit")
            var nextIndex = 1
            var fromName = tokens[nextIndex]
            if (fromName == "degree" || fromName == "degrees") {
                fromName += " " + tokens[nextIndex + 1]
                nextIndex += 2
            } else {
                nextIndex += 1
            }
            val fromUnit = MeasureUnit.find(fromName)

            // 3. Skip "to" or "in"
            nextIndex += 1

            // 4. Parse Target Unit (Handle "degree(s) unit")
            var toName = tokens[nextIndex]
            if (toName == "degree" || toName == "degrees") {
                toName += " " + tokens[nextIndex + 1]
            }
            val toUnit = MeasureUnit.find(toName)

            // 5. Validation Logic
            if (fromUnit == MeasureUnit.NULL || toUnit == MeasureUnit.NULL || fromUnit.type != toUnit.type) {
                println("Conversion from ${fromUnit.plural} to ${toUnit.plural} is impossible\n")
                continue
            }

            if (fromUnit.type == UnitType.LENGTH && amount < 0.0) {
                println("Length shouldn't be negative\n")
                continue
            }
            if (fromUnit.type == UnitType.WEIGHT && amount < 0.0) {
                println("Weight shouldn't be negative\n")
                continue
            }

            // 6. Calculation
            val result = when (fromUnit.type) {
                UnitType.TEMPERATURE -> {
                    // Convert Source to Celsius first
                    val celsius = when (fromUnit) {
                        MeasureUnit.CELSIUS -> amount
                        MeasureUnit.FAHRENHEIT -> (amount - 32.0) * 5.0 / 9.0
                        MeasureUnit.KELVIN -> amount - 273.15
                        else -> 0.0
                    }
                    // Convert Celsius to Target
                    when (toUnit) {
                        MeasureUnit.CELSIUS -> celsius
                        MeasureUnit.FAHRENHEIT -> celsius * 9.0 / 5.0 + 32.0
                        MeasureUnit.KELVIN -> celsius + 273.15
                        else -> 0.0
                    }
                }
                else -> amount * fromUnit.ratio / toUnit.ratio
            }

            // 7. Output Formatting
            val outFrom = if (amount == 1.0) fromUnit.singular else fromUnit.plural
            val outTo = if (result == 1.0) toUnit.singular else toUnit.plural
            println("$amount $outFrom is $result $outTo\n")

        } catch (e: Exception) {
            println("Parse error\n")
        }
    }
}
