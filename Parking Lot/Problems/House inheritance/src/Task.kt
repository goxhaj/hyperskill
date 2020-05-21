fun main() {
    val rooms = readLine()!!
    val price = readLine()!!
    var house = House(rooms.toInt(), price.toInt())

    when (rooms.toInt()) {
        in Int.MIN_VALUE..1 -> house = Cabin(rooms.toInt(), price.toInt())
        2, 3 -> house = Bungalow(rooms.toInt(), price.toInt())
        4 -> house = Cottage(rooms.toInt(), price.toInt())
        5, 6, 7 -> house = Mansion(rooms.toInt(), price.toInt())
        8, 9, 10 -> house = Palace(rooms.toInt(), price.toInt())
    }

    println(totalPrice(house))
}

fun totalPrice(house: House): Int {
    return house.finalPrice()
}

class Cabin(rooms: Int, price: Int = 1, private val coefficient: Double = 1.0) : House(rooms, price) {
    override fun finalPrice(): Int {
        return (basePrice() * this.coefficient).toInt()
    }
}

class Bungalow(rooms: Int, price: Int = 1, private val coefficient: Double = 1.2) : House(rooms, price) {
    override fun finalPrice(): Int {
        return (basePrice() * this.coefficient).toInt()
    }
}

class Cottage(rooms: Int, price: Int = 1, private val coefficient: Double = 1.25) : House(rooms, price) {
    override fun finalPrice(): Int {
        return (basePrice() * this.coefficient).toInt()
    }
}

class Mansion(rooms: Int, price: Int = 1, private val coefficient: Double = 1.4) : House(rooms, price) {
    override fun finalPrice(): Int {
        return (basePrice() * this.coefficient).toInt()
    }
}

class Palace(rooms: Int, price: Int = 1, private val coefficient: Double = 1.6) : House(rooms, price) {
    override fun finalPrice(): Int {
        return (basePrice() * this.coefficient).toInt()
    }
}

open class House(val rooms: Int, val price: Int) {
    open fun finalPrice(): Int {
        return 0;
    }

    fun basePrice(): Int {
        return when (price){
            in Int.MIN_VALUE..0 -> 0
            in 1_000_000..Int.MAX_VALUE -> 1_000_000
            else -> price
        }
    }
}