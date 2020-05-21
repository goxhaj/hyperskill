import java.util.*

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val xCord = IntArray(3)
    val yCord = IntArray(3)
    var x = 0
    var y = 0
    for (i in 0 until 6) {
        if (i % 2 == 0) {
            xCord[x++] = input.nextInt()
        } else {
            yCord[y++] = input.nextInt()
        }
    }

    printCoordinates(xCord)
    printCoordinates(yCord)

}

fun printCoordinates(coords: IntArray) {
    var count = 0
    for (i in 1..5) {
        if (coords.contains(i))
            continue
        else {
            if (count == 0) {
                print(i)
            } else {
                print(" $i")
            }
            count++
        }
    }
    println()
}
