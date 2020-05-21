import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val elements = scanner.nextInt()

    var prev = 0
    var count = 0
    var max = 0

    for (i in 0 until elements) {
        val el = scanner.nextInt()
        if(i==0){
            prev=el
            count++
        }
        else if (prev <= el) {
            count++
        } else {
            count = 1
        }
        prev = el
        if (max < count) {
            max = count
        }
    }

    println(max)

}