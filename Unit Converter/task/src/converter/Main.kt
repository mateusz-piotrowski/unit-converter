package converter

enum class Unit (val value: Double, val short: String, val single: String, val multi: String, val type: Int) {
    REF(1.0,"???","???","???",0),
    METER(1.0, "m", "meter", "meters", 0),
    KILOMETER(1000.0, "km", "kilometer", "kilometers", 0 ),
    MILLIMETER(0.001, "mm", "millimeter", "millimeters", 0),
    CENTIMETER(0.01,"cm", "centimeter", "centimeters", 0 ),
    MILE(1609.35,"mi", "mile", "miles", 0 ),
    YARD(0.9144, "yd", "yard", "yards", 0 ),
    FOOT(0.3048, "ft", "foot", "feet", 0 ),
    INCH(0.0254, "in", "inch", "inches", 0 ),
    GRAM(1.0, "g", "gram", "grams", 1 ),
    KILOGRAM(1000.0, "kg", "kilogram", "kilograms", 1 ),
    MILLIGRAM(0.001, "mg", "milligram", "milligrams", 1 ),
    POUND(453.592, "lb", "pound", "pounds", 1 ),
    OUNCE(28.3495, "oz", "ounce", "ounces", 1)
}
fun main() {
    var input = ""
    while (true) {
        println("Enter what you want to convert (or exit):")
        input = readln().lowercase()
        if (input == "exit") break // 1

        val (num, from, dir, to) = input.split(" ")
        var fromVal = Unit.REF
        var toVal = Unit.REF

        for (i in Unit.values()) {
            if (from in listOf(i.short, i.single, i.multi)) { fromVal = i }
            if (to in listOf(i.short, i.single, i.multi)) { toVal = i }
        }
        if (from in listOf(fromVal.short, fromVal.single, fromVal.multi) &&
            to in listOf(toVal.short, toVal.single, toVal.multi) && fromVal.type == toVal.type) {
            val convert = (num.toDouble() * fromVal.value) / toVal.value
            println("${num.toDouble()} ${if (num.toDouble() == 1.0) fromVal.single else fromVal.multi} is " +
                    "$convert ${if (convert == 1.0) toVal.single else toVal.multi}")
        } else { println("Conversion from ${fromVal.multi} to ${toVal.multi} is impossible")}
    }
}
