import java.time.Instant
import java.util.*


class Player(val id: Int, val name: String, val hp: Int) {

    companion object {
        fun create(name: String): Player {
            val id = Instant.now().toEpochMilli().toInt()
            return Player(id, name, 100)
        }
    }
}