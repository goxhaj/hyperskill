import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val fractional = scanner.nextDouble()
    println(fractional.toString().split(".")[1][0])
}