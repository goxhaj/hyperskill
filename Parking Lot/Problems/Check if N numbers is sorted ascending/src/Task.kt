import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val count = scanner.nextInt()
    val nums = readLine()!!.split(" ")
    var before = 0
    var sorted = "YES"
    if (nums.size == count) {
        for (i in 0 until count) {
            if (i == 0) {
                before = nums[0].toInt()
            } else {
                if (before > nums[i].toInt()) {
                    sorted = "NO"
                    break
                } else {
                    before = nums[i].toInt()
                }
            }
        }
    }
    println(sorted)
}
