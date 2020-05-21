// Posted from EduTools plugin
import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val count = scanner.nextInt()
    var before = 0
    var sorted = "YES"
    for (i in 0 until count) {
        val el = scanner.nextInt()
        if (i == 0) {
            before = el
        } else {
            if (before > el) {
                sorted = "NO"
                break
            } else {
                before = el
            }
        }
    }

    println(sorted)
}
