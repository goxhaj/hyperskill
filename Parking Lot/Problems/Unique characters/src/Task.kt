fun main() {
    // put your code here
    val word = readLine()!!
    var unique = 0
    for (i in word.indices){
        if(isUnique(word, word[i], i)){
            unique++
        }
    }
    println(unique)
}

fun isUnique(word:String, find: Char, skip:Int): Boolean {
    for (i in word.indices){
        if(i==skip){
            continue
        }
        if(word[i]==find){
            return false
        }
    }
    return true;
}