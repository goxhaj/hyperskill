import java.util.*

fun main(args: Array<String>) {

    val scanner = Scanner(System.`in`)

    val numbers = scanner.nextInt()
    val numbersArray = IntArray(numbers)

    for (i in 0..numbersArray.lastIndex) {
        numbersArray[i] = scanner.nextInt()
    }

    val n = scanner.nextInt()
    val m = scanner.nextInt()

    var index = 0
    var count = 0
    for (i in 0..numbersArray.lastIndex) {
        if (numbersArray[i] == n || numbersArray[i] == m) {
            count++
            index=i
        } else {
            count = 0
        }

        if (count == 2) {
            break
        }
    }

    if (count == 2) {
        println("NO")
    } else {
        println("YES")
    }
}