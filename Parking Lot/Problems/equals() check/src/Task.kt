import java.util.*

data class Client(val name: String, val age: Int, val balance: Int) {
    override fun equals(client: Any?): Boolean {
        if (client is Client) {
            return this.name == client.name && this.age == client.age
        }
        return false;

    }
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val name1 = input.next()
    val age1 = input.nextInt()
    val balance1 = input.nextInt()
    val client1 = Client(name1, age1, balance1)
    val name2 = input.next()
    val age2 = input.nextInt()
    val balance2 = input.nextInt()
    val client2 = Client(name2, age2, balance2)
    println(client1.equals(client2))

}