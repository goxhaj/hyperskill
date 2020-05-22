import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()
    val c = scanner.nextInt()
    when (20){
        a+b -> println("true")
        a+c -> println("true")
        b+c -> println("true")
        else -> println("false")
    }
}