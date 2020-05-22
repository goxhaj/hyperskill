package machine

import java.util.*

class CoffeeMachine {
    var money: Int = 550
    var water: Int = 400
    var milk: Int = 540
    var coffeeBeans = 120
    var cups: Int = 9

    fun espresso() {
        if (hasEnoughResources(250, 0, 16, 1)) {
            this.water -= 250
            this.coffeeBeans -= 16
            this.cups--
            this.money += 4
        }
    }

    fun latte() {
        if (hasEnoughResources(350, 75, 20, 1)) {
            this.water -= 350
            this.milk -= 75
            this.coffeeBeans -= 20
            this.cups--
            this.money += 7
        }
    }

    fun cappucino() {
        if (hasEnoughResources(200, 100, 12, 1)) {
            this.water -= 200
            this.milk -= 100
            this.coffeeBeans -= 12
            this.cups--
            this.money += 6
        }
    }

    fun fill(water: Int, milk: Int, coffeeBeans: Int, cups: Int) {
        this.water += water
        this.milk += milk
        this.coffeeBeans += coffeeBeans
        this.cups += cups
    }

    fun take(): Int {
        val money = this.money
        this.money = 0
        return money
    }

    fun hasEnoughResources(water: Int, milk: Int, coffeeBeans: Int, cups: Int): Boolean {
        if (this.water - water < 0) {
            println("Sorry, not enough water!")
            return false
        }
        if (this.milk - milk < 0) {
            println("Sorry, not enough milk!")
            return false
        }
        if (this.coffeeBeans - coffeeBeans < 0) {
            println("Sorry, not enough coffee beans!")
            return false
        }
        if (this.cups - cups < 0) {
            println("Sorry, not enough cups!")
            return false
        }

        println("I have enough resources, making you a coffee!")
        return true
    }

    override fun toString(): String {
        return "The coffee machine has:\n" +
                "$water of water\n" +
                "$milk of milk\n" +
                "$coffeeBeans of coffee beans\n" +
                "$cups of disposable cups\n" +
                "$money of money"
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val coffeeMachine = CoffeeMachine()
    var action = action(scanner)
    while (action != "exit") {
        when (action) {
            "buy" -> {
                print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                var choice = scanner.next()
                when (choice) {
                    "1" -> coffeeMachine.espresso()
                    "2" -> coffeeMachine.latte()
                    "3" -> coffeeMachine.cappucino()
                }
            }
            "fill" -> {
                print("Write how many ml of water do you want to add:")
                var water = scanner.nextInt()
                print("Write how many ml of milk do you want to add:")
                var milk = scanner.nextInt()
                print("Write how many grams of coffee beans do you want to add:")
                var coffeeBeans = scanner.nextInt()
                print("Write how many disposable cups of coffee do you want to add:")
                var cups = scanner.nextInt()
                coffeeMachine.fill(water, milk, coffeeBeans, cups)
            }
            "take" -> {
                val money = coffeeMachine.take();
                println("I gave you $$money")
            }
            "remaining" -> println(coffeeMachine)
        }
        action = action(scanner)
    }
}

fun action(scanner: Scanner): String {
    print("Write action (buy, fill, take):")
    return scanner.next()
}
