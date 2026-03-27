package reproducer

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

fun mainReproducer() {
    val input = "1 foot in cm\nexit\n"
    val inStream = ByteArrayInputStream(input.toByteArray())
    val outStream = ByteArrayOutputStream()
    
    val oldIn = System.`in`
    val oldOut = System.out
    
    System.setIn(inStream)
    System.setOut(PrintStream(outStream))
    
    try {
        converter.main()
    } catch (e: Throwable) {
        System.setIn(oldIn)
        System.setOut(oldOut)
        System.err.println("Caught exception: $e")
        e.printStackTrace(System.err)
    } finally {
        System.setIn(oldIn)
        System.setOut(oldOut)
    }
    System.out.println(outStream.toString())
}

fun main() {
    mainReproducer()
}
