import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val words = mutableMapOf<String, Int>()

    var word = scanner.next()
    while (word != "stop") {
        if (words.containsKey(word)) {
            words[word] = words[word]!! + 1
        } else {
            words[word] = 1
        }
        word = scanner.next()
    }

    var mostUsedWordCount = 0
    var mostUsedWord = "null"
    for (entry in words) {
        if (mostUsedWordCount < entry.value) {
            mostUsedWordCount = entry.value
            mostUsedWord = entry.key
        }
    }

    println(mostUsedWord)
}