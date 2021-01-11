import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val n = scanner.nextInt()
    val arr: IntArray = IntArray(n)

    for (i in 0 until n) {
        arr[i] = scanner.nextInt()
    }
    if (n == 1) println(arr[0])
    else
        println(maxProd(arr))
}

fun maxProd(arr: IntArray): Int {
    var index = 1
    var maxProd = 0
    for (i in 0..arr.lastIndex) {
        for (j in index..arr.lastIndex) {
            if (maxProd < arr[i] * arr[j])
                maxProd = arr[i] * arr[j]
        }
        index++
    }

    return maxProd
}


