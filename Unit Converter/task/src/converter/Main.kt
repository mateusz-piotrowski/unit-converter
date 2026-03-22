package converter

fun main() {
    print("Enter a number and a measure of length: ")
    val input = readlnOrNull()?.trim() ?: return
    val parts = input.split(" ", limit = 2).map { it.trim() }

    if (parts.size != 2) {
        println("Wrong input. Unknown unit $input")
        return
    }

    val valueText = parts[0]
    val unitText = parts[1].lowercase()

    val value = valueText.toDoubleOrNull()
    if (value == null) {
        println("Wrong input. Unknown unit $input")
        return
    }

    val toMeterFactor = when (unitText) {
        "m", "meter", "meters" -> 1.0
        "km", "kilometer", "kilometers" -> 1000.0
        "cm", "centimeter", "centimeters" -> 0.01
        "mm", "millimeter", "millimeters" -> 0.001
        "mi", "mile", "miles" -> 1609.35
        "yd", "yard", "yards" -> 0.9144
        "ft", "foot", "feet" -> 0.3048
        "in", "inch", "inches" -> 0.0254
        else -> {
            println("Wrong input. Unknown unit $unitText")
            return
        }
    }

    val inMeters = value * toMeterFactor

    // Choose singular or plural for the input unit
    val inputUnitName = when (unitText) {
        "m", "meter", "meters" -> if (value == 1.0) "meter" else "meters"
        "km", "kilometer", "kilometers" -> if (value == 1.0) "kilometer" else "kilometers"
        "cm", "centimeter", "centimeters" -> if (value == 1.0) "centimeter" else "centimeters"
        "mm", "millimeter", "millimeters" -> if (value == 1.0) "millimeter" else "millimeters"
        "mi", "mile", "miles" -> if (value == 1.0) "mile" else "miles"
        "yd", "yard", "yards" -> if (value == 1.0) "yard" else "yards"
        "ft", "foot", "feet" -> if (value == 1.0) "foot" else "feet"
        "in", "inch", "inches" -> if (value == 1.0) "inch" else "inches"
        else -> "???" // should not happen here
    }

    val meterName = if (inMeters == 1.0) "meter" else "meters"

    println("${value}0 $inputUnitName is ${inMeters} $meterName")
}
