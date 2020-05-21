fun main() {
    // put your code here
    val line = readLine()!!
    for(i in 0..line.length) {
        if(line[i].isDigit()){
            println(line[i])
            break
        }
    }
}