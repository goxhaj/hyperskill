fun parseCardNumber(cardNumber: String): Long {
    val parts = cardNumber.split(" ")
    if(parts.size!=4){
        throw Exception("Invalid Card Number")
    }

    var number = ""
    for(part in parts){
        number += part
    }

    return number.toLong()
}
