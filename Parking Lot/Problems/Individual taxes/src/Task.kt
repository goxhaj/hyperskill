import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    val numberOfCompanies = scanner.nextInt()
    val yearlyIncome = IntArray(numberOfCompanies)
    for (i in 0 until numberOfCompanies) {
        yearlyIncome[i] = scanner.nextInt()
    }
    val percentageForCompanies = IntArray(numberOfCompanies)
    for (i in 0 until numberOfCompanies) {
        percentageForCompanies[i] = scanner.nextInt()
    }

    var maxTaxes:Long = 0
    var maxIndex = 0
    for (i in 0 until numberOfCompanies) {
        var taxes:Long = (yearlyIncome[i] * percentageForCompanies[i]).toLong()
        if (taxes > maxTaxes) {
            maxTaxes = taxes
            maxIndex = i
        }
    }

    println(++maxIndex)
}
