import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val numbers = scanner.nextInt()
    val numbersArray = IntArray(numbers)

    for (i in 0..numbersArray.lastIndex){
        numbersArray[i] = scanner.nextInt()
    }

    val check = scanner.nextInt()

    if(numbersArray.contains(check)){
        println("YES")
    } else {
        println("NO")
    }

}