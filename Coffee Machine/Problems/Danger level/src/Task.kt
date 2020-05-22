enum class DangerLevel {
    HIGH {
        override fun getLevel(): Int {
            return 3
        }
    },
    MEDIUM {
        override fun getLevel(): Int {
            return 2
        }
    },
    LOW {
        override fun getLevel(): Int {
            return 1
        }
    };

    abstract fun getLevel(): Int

}