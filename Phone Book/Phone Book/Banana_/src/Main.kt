fun solution(strings: MutableList<String>, str: String): MutableList<String> {
    for ((index, str2) in strings.withIndex()) {
        if (str2 == str) {
            strings[index] = "Banana"
        }
    }
    return strings
}