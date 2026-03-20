package converter

fun main() {
    print("Enter a number and a measure: ")
    val input = readLine() ?: return

    // Split input into number and unit parts
    val parts = input.trim().split("\\s+".toRegex(), 2)
    if (parts.size != 2) {
        println("Wrong input")
        return
    }

    val numberStr = parts[0]
    val unit = parts[1].trim().lowercase()

    // Parse number (guaranteed to be integer)
    val number = numberStr.toIntOrNull()
    if (number == null) {
        println("Wrong input")
        return
    }

    // Check unit (case insensitive, supports km, kilometer(s))
    val isValidUnit = unit == "km" || unit == "kilometer" || unit == "kilometers"
    if (!isValidUnit) {
        println("Wrong input")
        return
    }

    // Determine singular/plural form for output
    val unitName = if (number == 1) "kilometer" else "kilometers"
    val meters = number * 1000

    println("$number $unitName is $meters meters")
}
