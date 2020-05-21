import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    var multiply:Long = 1
    for (i in a until b) {
        multiply *= i
    }

    println(multiply)
}