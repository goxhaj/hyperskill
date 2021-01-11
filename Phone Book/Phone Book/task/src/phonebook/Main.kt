package phonebook

import java.io.File
import java.util.*
import kotlin.math.floor
import kotlin.math.sqrt

class Contact(val number: Int, val person: Person) {
    override fun toString(): String {
        return person.name
    }
}

class Person(val name: String) {
    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?) = (other is Person) && this.name == other.name

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class PhoneBook(phoneBookFile: String) {

    private val MAX_SEARCH_TIME_SEC: Int = 1

    private var contacts: MutableList<Contact>

    init {
        this.contacts = loadContacts(phoneBookFile)
    }

    private fun loadContacts(fileName: String): MutableList<Contact> {
        val lines = File(fileName).readLines()
        contacts = MutableList<Contact>(lines.size) { Contact(0, Person("")) }
        for ((i, line) in lines.withIndex()) {
            val contact = line.split(" ")
            contacts[i] = Contact(contact.first().toInt(), Person(contact.subList(1, contact.size).joinToString(" ")))
        }
        return contacts
    }

    fun linearSearch(personsToFind: Array<Person>): Int {
        var count = 0
        for (contact in contacts) {
            if (personsToFind.any { it.name == contact.person.name }) {
                count++
            }
        }

        return count
    }

    fun findBybubbleSort(personsToFind: Array<Person>) {
        println("Start searching (bubble sort + jump search)...")
        var swap = true
        val entries = personsToFind.size
        var stopped = false
        val start = System.currentTimeMillis()
        while (swap) {
            swap = false
            for (i in 0 until contacts.size - 1) {
                if (contacts[i].person.name > contacts[i + 1].person.name) {
                    val temp = contacts[i]
                    contacts[i] = contacts[i + 1]
                    contacts[i + 1] = temp
                    swap = true
                }
            }

            if (checkTimeout(start)) {
                stopped = true
                break
            }
        }

        if (stopped) {
            val sortTime = System.currentTimeMillis() - start
            val startSearchingTime = System.currentTimeMillis()
            val count = linearSearch(personsToFind)
            val searchTime = System.currentTimeMillis() - startSearchingTime
            val endTotal = System.currentTimeMillis() - start
            println(
                "Found $count / $entries entries. Time taken: " + String.format(
                    "%1\$tM min. %1\$tS sec. %1\$tL ms.",
                    endTotal
                )
            )
            println(
                "Sorting time: STOPPED, moved to linear search: " + String.format(
                    "%1\$tM min. %1\$tS sec. %1\$tL ms.",
                    sortTime
                )
            )
            println("Searching time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", searchTime))

        } else {
            val sortTime = System.currentTimeMillis() - start
            val startSearchingTime = System.currentTimeMillis()
            val count = jumpSearch(personsToFind)
            val searchTime = System.currentTimeMillis() - startSearchingTime
            val endTotal = System.currentTimeMillis()
            println(
                "Found $count / $entries entries. Time taken: " + String.format(
                    "%1\$tM min. %1\$tS sec. %1\$tL ms.",
                    endTotal - start
                )
            )
            println("Sorting time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", sortTime))
            println("Searching time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", searchTime))
        }
    }


    fun findByQuickSort(personsToFind: Array<Person>) {
        println("Start searching (quick sort + binary search)...")
        val startFound = System.currentTimeMillis()
        var start = System.currentTimeMillis()
        quicksort(contacts, 0, contacts.lastIndex)
        var end = System.currentTimeMillis()
        val sortingTime = end - start

        start = System.currentTimeMillis()
        var count = 0
        for (person in personsToFind) {
            if (contacts.binarySearch {
                    String.CASE_INSENSITIVE_ORDER.compare(
                        it.person.name,
                        person.name
                    )
                } >= 0) {
                count++
            }
        }
        end = System.currentTimeMillis()
        val searchingTime = end - start

        val entries = personsToFind.size
        val endFound = System.currentTimeMillis()
        println(
            "Found $count / $entries entries. Time taken: " + String.format(
                "%1\$tM min. %1\$tS sec. %1\$tL ms.",
                endFound - startFound
            )
        )
        println("Sorting time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", sortingTime))
        println("Searching time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", searchingTime))
    }


    private fun quicksort(contacts: MutableList<Contact>, left: Int, right: Int) {
        val pivotIndex = partition(contacts, left, right)

        if (left < pivotIndex) {
            quicksort(contacts, left, pivotIndex - 1)
        }

        if (right > pivotIndex) {
            quicksort(contacts, pivotIndex + 1, right)
        }
    }

    private fun partition(contacts: MutableList<Contact>, left: Int, right: Int): Int {
        var start = left
        val pivot = contacts[right]
        for (i in start until right) {
            if (contacts[i].person.name < pivot.person.name) {
                val temp = contacts[start]
                contacts[start] = contacts[i]
                contacts[i] = temp
                start++
            }
        }
        val temp = contacts[start]
        contacts[start] = pivot
        contacts[right] = temp
        return start
    }

    private fun checkTimeout(startTime: Long): Boolean {
        return (System.currentTimeMillis() - startTime) / 1000 > MAX_SEARCH_TIME_SEC
    }

    private fun jumpSearch(persons: Array<Person>): Int {
        var count = 0
        val contactsSize: Int = contacts.size
        for (person in persons) {
            var step = floor(sqrt(contactsSize.toDouble())).toInt()

            var prev = 0
            while (contacts[step.coerceAtMost(contactsSize) - 1].person.name < person.name) {
                prev = step
                step += floor(sqrt(contactsSize.toDouble())).toInt()
                if (prev >= contactsSize) break
            }

            while (contacts[prev].person.name < person.name) {
                prev++
                if (prev == step.coerceAtMost(contactsSize)) break
            }

            if (contacts[prev].person.name == person.name) {
                count++
            }
        }

        return count
    }

    fun findByHashtable(personsToFind: Array<Person>) {
        println("Start searching (hash table)...")
        val startFound = System.currentTimeMillis()
        var start = System.currentTimeMillis()
        val hashedContacts = buildHashtable(contacts.map { it -> it.person })
        var end = System.currentTimeMillis()
        val creatingTime = end - start

        start = System.currentTimeMillis()
        var count = 0
        for (person in personsToFind) {
            if (hashedContacts[createHash(person.name)] != null) {
                count++
            }
        }
        end = System.currentTimeMillis()
        val searchingTime = end - start

        val entries = personsToFind.size
        val endFound = System.currentTimeMillis()
        println(
            "Found $count / $entries entries. Time taken: " + String.format(
                "%1\$tM min. %1\$tS sec. %1\$tL ms.",
                endFound - startFound
            )
        )
        println("Creating time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", creatingTime))
        println("Searching time: " + String.format("%1\$tM min. %1\$tS sec. %1\$tL ms.", searchingTime))
    }

    private fun buildHashtable(contacts: List<Person>): Hashtable<Int, String> {
        val hashtable: Hashtable<Int, String> = Hashtable()
        contacts.forEach { hashtable[createHash(it.name)] = it.name }
        return hashtable
    }

    private fun createHash(name: String): Int {
        var hash = 7
        for (element in name) {
            hash = hash * 31 + element.toInt()
        }
        return hash
    }

    fun findByLinearSearch(personsToFind: Array<Person>) {
        println("Start searching (linear search)...")
        val start = System.currentTimeMillis()
        val entries = personsToFind.size
        val count = linearSearch(personsToFind)
        val end = System.currentTimeMillis()
        println(
            "Found $count / $entries entries. Time taken: " + String.format(
                "%1\$tM min. %1\$tS sec. %1\$tL ms.",
                end - start
            )
        )
    }
}


fun loadPersonsToFind(fileName: String): Array<Person> {
    val lines = File(fileName).readLines()
    val persons = Array<Person>(lines.size) { Person("") }
    for ((i, line) in lines.withIndex()) {
        persons[i] = Person(line)
    }
    return persons
}

fun main() {
    var phoneBook = PhoneBook("C:\\Users\\agoxhaj\\directory.txt")
    var personsToFind = loadPersonsToFind("C:\\Users\\agoxhaj\\find.txt")
    phoneBook.findByLinearSearch(personsToFind)
    println()
    phoneBook.findBybubbleSort(personsToFind)
    println()
    phoneBook.findByQuickSort(personsToFind)
    println()
    phoneBook.findByHashtable(personsToFind)
    println()
}