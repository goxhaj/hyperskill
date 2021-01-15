package flashcards

import java.io.File
import java.io.FileNotFoundException
import java.util.*


class FlashCardGame {

    private val flashCards = LinkedHashMap<String, FlashCard>()
    var consoleLog = mutableListOf<String>()

    private var hardestCards = mutableMapOf<String, Int>()

    private val terms = Hashtable<String, String>()
    private val definitions = Hashtable<String, String>()

    fun checkDefinition(term: String, definition: String) {
        if (flashCards[term]?.definition == definition) {
            print("Correct!\n")
        } else {
            if (definitions[definition] != null) {
                print(
                    "Wrong. The right answer is \"${flashCards[term]?.definition}\", but your definition is correct for \"${definitions[definition]}\"\n"
                )
            } else {
                print("Wrong. The right answer is \"${flashCards[term]?.definition}\".\n")
            }

            updateStatistics(term)
        }
    }

    private fun updateStatistics(term: String) {
        val count = hardestCards[term]
        if (count == null) {
            hardestCards[term] = 1
        } else {
            hardestCards[term] = count + 1
        }
    }

    fun add() {
        while (true) {
            print("The card:\n")
            val term = readLine()!!
            consoleLog.add(term+"\n")
            if (terms[term] != null) {
                print("The card \"$term\" already exists. Try again:\n")
                break
            }

            print("The definition of the card:\n")
            val definition = readLine()!!
            consoleLog.add(definition+"\n")
            if (definitions[definition] != null) {
                print("The definition \"$definition\" already exists. Try again:\n")
                break
            } else {
                terms[term] = definition
                definitions[definition] = term
            }

            val flashcard = FlashCard(term, definition)
            flashCards[term] = flashcard
            this.print("The pair $flashcard has been added.\n")
            break
        }
    }

    fun remove() {
        print("Which card?\n")
        val term = readLine()!!
        consoleLog.add(term+"\n")
        if (terms[term] == null) {
            print("Can't remove \"$term\": there is no such card.\n")
        } else {
            val def = terms[term]
            val ter = definitions[def]
            definitions.remove(def)
            terms.remove(ter)
            flashCards.remove(ter)
            print("The card has been removed.\n")
        }
    }

    fun importFlashCards(fileName: String) {
        if (File(fileName).exists()) {
            try {
                val cards = File(fileName).readLines()
                for (i in cards.indices step 3) {
                    val term = cards[i]
                    val definition = cards[i + 1]
                    val count = cards[i + 2].toInt()
                    updateCard(term, definition, count)

                }
                print("${cards.size / 3} cards have been loaded.\n")
            } catch (e: Exception) {
                print("There was an error in reading the File\n")
            }
        } else print("File not found.\n")
    }


    private fun updateCard(term: String, definition: String, count: Int) {
        if (terms[term] != null) {
            definitions.remove(terms[term])
        }

        terms[term] = definition
        definitions[definition] = term

        hardestCards[definition] = count
        flashCards[term] = FlashCard(term, definition)
    }

    fun exportFlashCards(fileName: String) {
        try {
            val file = File(fileName)
            for (card in flashCards) {
                var count = 0
                if (hardestCards[card.key] != null) {
                    count = hardestCards[card.key]!!
                }
                file.appendText("${card.key}\n")
                file.appendText("${card.value.definition}\n")
                file.appendText("$count\n")

            }
            print("${flashCards.size} cards have been saved.\n")
        } catch (e: FileNotFoundException) {
            print("File not found.\n")
        }
    }

    fun nextCommand(): String {
        print(
            "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):\n"
        )
        val command = readLine()!!
        consoleLog.add(command+"\n")
        return command
    }

    fun ask(n: Int): String? {
        var count = 0
        for (card in flashCards) {
            if (count == n - 1) {
                return card.key
            }
            count++
        }

        return null
    }

    fun log(fileName: String) {
        val file = File(fileName)
        file.writeText("")
        val msg = "The log has been saved.\n"
        consoleLog.add(msg)
        for (log in consoleLog) {
            file.appendText(log)
        }
        println(msg)
    }

    private fun findMax(): MutableList<String> {
        val listOfTerms = mutableListOf<String>()
        var max = 0
        for ((key, value) in hardestCards) {
            if (value >= max) {
                listOfTerms.add(key)
                max = value
            }
        }
        return listOfTerms
    }

    fun hardestCard() {
        val terms = findMax()
        var strTerms = ""

        for (term in terms) {
            strTerms += " \"${term}\","
        }

        val msg = when (terms.size) {
            0 -> {
                "There are no cards with errors."
            }
            1 -> {
                "The hardest card is \"${hardestCards.keys.first()}\". You have ${hardestCards.values.first()} errors answering it"
            }
            else -> {
                print("The hardest cards are")
                strTerms.substring(0, strTerms.length - 1)
            }
        }

        print(msg + "\n")
    }

    fun resetStats() {
        hardestCards.clear()
        print("Card statistics have been reset.\n")
    }


    fun print(msg: String) {
        consoleLog.add(msg)
        kotlin.io.print(msg)
    }

}

data class FlashCard(val term: String, val definition: String) {
    override fun toString(): String {
        return "(\"$term\":\"$definition\")"
    }
}


fun main() {

    val flashCardGame = FlashCardGame()

    var command = flashCardGame.nextCommand()
    while (command != "exit") {
        when (command) {
            "add" -> {
                flashCardGame.add()
            }

            "remove" -> {
                flashCardGame.remove()
            }

            "import" -> {
                flashCardGame.print("File name:\n")
                val fileName = readLine()!!
                flashCardGame.consoleLog.add(fileName+"\n")
                flashCardGame.importFlashCards(fileName)
            }

            "export" -> {
                flashCardGame.print("File name:\n")
                val fileName = readLine()!!
                flashCardGame.consoleLog.add(fileName+"\n")
                flashCardGame.exportFlashCards(fileName)
            }

            "ask" -> {
                flashCardGame.print("How many times to ask?\n")
                val nrCards = readLine()!!.toInt()
                flashCardGame.consoleLog.add(nrCards.toString()+"\n")
                for (n in 1..nrCards) {
                    val term = flashCardGame.ask(n)
                    if (term != null) {
                        flashCardGame.print("Print the definition of \"${term}\":\n")
                        val definition = readLine()!!
                        flashCardGame.consoleLog.add(definition+"\n")
                        flashCardGame.checkDefinition(term, definition)
                    }
                }
            }

            "log" -> {
                flashCardGame.print("File name:\n")
                val fileName = readLine()!!
                flashCardGame.consoleLog.add(fileName+"\n")
                flashCardGame.log(fileName)
            }

            "hardest card" -> {
                flashCardGame.hardestCard()
            }

            "reset stats" -> {
                flashCardGame.resetStats()
            }
        }
        flashCardGame.print("\n")
        command = flashCardGame.nextCommand()
    }

    flashCardGame.print("Bye bye!\n")
}