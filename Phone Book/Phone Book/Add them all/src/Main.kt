fun solution(first: List<Int>, second: List<Int>): MutableList<Int> {
    var list = mutableListOf<Int>()
    list.addAll(first)
    list.addAll(second)
    return list
}