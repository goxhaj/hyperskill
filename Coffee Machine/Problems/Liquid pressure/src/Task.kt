import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`).useLocale(Locale.US)
    val gravity =  9.8
    val density = scanner.nextDouble()
    val height = scanner.nextDouble()
    print(density * gravity * height)
}