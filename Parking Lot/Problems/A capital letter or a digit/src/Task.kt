import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val a = scanner.next()
    val capital = a.toCharArray()[0] in 'A'..'Z'
    val number = a.toCharArray()[0] in '1'..'9'
    print(capital || number)
}