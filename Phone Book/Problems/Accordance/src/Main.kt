fun main() {
    val arr = arrayOf(arrayOf<String>("", "", ""), arrayOf<String>("", "", ""))

    for(i in 0 until 2) {
        for (j in 0 until 3) {
            arr[i][j]= ("[$i][$j]")
        }
    }
    println(arr.contentDeepToString())
}