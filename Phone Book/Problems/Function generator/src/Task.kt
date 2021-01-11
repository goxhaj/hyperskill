fun generate(functionName: String): (Int) -> Int {
    when (functionName) {
        "identity" -> {
            return ::identity
        }
        "half" -> {
            return ::half
        }
        "zero" -> {
            return ::zero
        }
        else -> {
            return ::zero
        }
    }
}

fun identity(number: Int): Int {
    return number
}

fun half(number: Int): Int {
    return number / 2
}

fun zero(number: Int): Int {
    return 0
}
