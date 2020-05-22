import java.util.Scanner



fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val countries = mutableMapOf<String, String>()
    countries.put("Germany","Euro")
    countries.put("Mali","CFA franc")
    countries.put("Dominica","Eastern Caribbean dollar")
    countries.put("Canada","Canadian dollar")
    countries.put("Spain","Euro")
    countries.put("Australia","Australian dollar")
    countries.put("Brazil","Brazilian real")
    countries.put("Senegal","CFA franc")
    countries.put("France","Euro")
    countries.put("Grenada","Eastern Caribbean dollar")
    countries.put("Kiribati","Australian dollar")

    val country1 = input.next()
    val country2 = input.next()

    println(countries.get(country1) == countries.get(country2))

}