package parking

import java.util.*


fun main() {
    var parkingLot = ParkingLot(0)
    val scanner = Scanner(System.`in`)
    var command = scanner.next()
    while (command != "exit") {
        when (command) {
            "reg_by_color" -> {
                var color = scanner.next()
                parkingLot.regByColor(color)
            }
            "spot_by_color" -> {
                var color = scanner.next()
                parkingLot.spotByColor(color)
            }
            "spot_by_reg" -> {
                var plate = scanner.next()
                parkingLot.spotByReg(plate)
            }
            "status" -> parkingLot.printCars()
            "create" -> {
                var size = scanner.nextInt()
                parkingLot = ParkingLot(size)
                println("Created a parking lot with $size spots.")
            }
            "park" -> {
                val plate = scanner.next()
                val color = scanner.next()
                parkingLot.park(plate, color)
            }
            "leave" -> {
                val carFromSpot = scanner.nextInt()
                parkingLot.leave(carFromSpot)
            }
        }
        command = scanner.next()
    }
}

class Car {
    var plate: String = ""
    var color: String = ""

    constructor()

    constructor(plate: String, color: String) {
        this.plate = plate
        this.color = color
    }
}

class ParkingLot(size: Int = 0) {

    val cars = Array(size) { Car() }

    fun isLotEmpty(): Boolean {
        return if (cars.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
            true
        } else {
            false
        }
    }

    fun regByColor(color: String) {
        if (!isLotEmpty()) {
            val filterByColor = cars.filter { it.color.toLowerCase() == color.toLowerCase() }.toTypedArray()
            if (filterByColor.isEmpty()) {
                println("No cars with color $color were found.")
            } else {
                println(getCarsByPlateNumber(filterByColor))
            }
        }
    }

    fun spotByColor(color: String) {
        if (!isLotEmpty()) {
            var spots = arrayListOf<Int>()
            for (i in cars.indices) {
                if (!isEmptySpot(cars[i]) && cars[i].color.toLowerCase() == color.toLowerCase()) {
                    spots.add(i + 1)
                }
            }
            val listOfSpotNumbers = spots.joinToString(", ", "", "")
            if (listOfSpotNumbers == "") {
                println("No cars with color $color were found.")
            } else {
                println(listOfSpotNumbers)
            }
        }
    }

    fun spotByReg(plate: String) {
        if (!isLotEmpty()) {
            var spots = arrayListOf<Int>()
            for (i in cars.indices) {
                if (!isEmptySpot(cars[i]) && cars[i].plate.toLowerCase() == plate.toLowerCase()) {
                    spots.add(i + 1)
                }
            }
            val listOfSpotNumbers = spots.joinToString(", ", "", "")
            if (listOfSpotNumbers == "") {
                println("No cars with registration number $plate were found.")
            } else {
                println(listOfSpotNumbers)
            }
        }
    }

    override fun toString(): String {
        return getCarsByPlateNumber(this.cars)
    }

    private fun getCarsByPlateNumber(cars: Array<Car>): String {
        return cars.joinToString(", ", "", "") { car -> car.plate }
    }


    fun park(plate: String, color: String) {
        if (!isLotEmpty()) {
            var parked = false

            for (i in cars.indices) {
                var car = Car(plate, color)
                if (isEmptySpot(cars[i])) {
                    cars[i] = car
                    parked = true
                    println(color + " car parked in spot " + (i + 1) + ".")
                    break
                }
            }

            if (!parked) {
                println("Sorry, the parking lot is full.")
            }
        }
    }

    fun leave(spot: Int) {
        if (!isLotEmpty()) {
            val index = spot - 1
            if (canRemoveCarFromSpotSpace(index)) {
                if (isEmptySpot(cars[index])) {
                    println("There is no car in the spot $spot.")
                } else {
                    cars[index] = Car()
                    println("Spot $spot is free.")
                }
            }
        }
    }

    fun printCars() {
        if (!isLotEmpty()) {
            var size = 0
            for (i in cars.indices) {
                if (!isEmptySpot(cars[i])) {
                    println("" + (i + 1) + " " + cars[i].plate + " " + cars[i].color)
                    size++
                }
            }
            if (size == 0) {
                println("Parking lot is empty.")
            }
        } else {
            println("Sorry, a parking lot has not been created.")
        }

    }

    private fun isEmptySpot(car: Car): Boolean {
        return car.plate == "" && car.color == ""
    }


    private fun canRemoveCarFromSpotSpace(index: Int): Boolean {
        return cars.lastIndex >= index
    }

}






