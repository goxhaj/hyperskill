import java.util.*
import kotlin.math.sqrt

fun main() {
    val scanner = Scanner(System.`in`)
    val a: Double = scanner.nextDouble()
    val b: Double = scanner.nextDouble()
    val c: Double = scanner.nextDouble()

    val result = b * b - 4.0 * a * c

    val d1 = (-b + sqrt(result)) / (2.0 * a)
    val d2 = (-b - sqrt(result)) / (2.0 * a)

    val list: DoubleArray = doubleArrayOf(d1, d2).sortedArray()

    println(list.joinToString(" ", ""))

}