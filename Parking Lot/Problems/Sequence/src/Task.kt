import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val a = scanner.next()
    val b = scanner.next()
    val c = scanner.next()

    print(a.toLowerCase().toCharArray()[0]+1==b.toLowerCase().toCharArray()[0] && b.toLowerCase().toCharArray()[0]+1==c.toLowerCase().toCharArray()[0])

}