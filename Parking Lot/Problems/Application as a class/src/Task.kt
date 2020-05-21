class Application(val name: String) {

    // write the run method here
    fun run(args:Array<String>) {
        println(this.name)
        args.forEach { arg -> println(arg) }
    }
}