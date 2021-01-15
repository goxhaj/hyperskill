fun intDivision(x: String, y: String): Int {
    try {
        val a = x.toInt()
        val b = y.toInt()
        return a / b
    } catch (e: NumberFormatException) {
        println("Read values are not integers.")
    } catch (e: ArithmeticException) {
        println("Exception: division by zero!")
    }

    return 0
}

fun main() {
    val x = readLine()!!
    val y = readLine()!!
    println(intDivision(x, y))

    val a = intArrayOf(3, 0, 3, 9, 2, 1)
    var r = 7

    r = 7
    for (i in a.indices) {
        var x = a[i]
        x = x * x
        r += x
    }

    println(r)

    r = 13
    r += a[a[0]]
    r -= a[a[a.size - 1]]
    println(r)

    r = 0
    for (i in a.indices) {
        if (a[i] < a.size) r += a[i]
    }
    println(r)
}
