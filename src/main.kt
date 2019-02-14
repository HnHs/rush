package rush

fun main(args: Array<String>) {
    while (true) {
        print("> ")
        val line = readLine()
        if(line.isNullOrEmpty())
            return;

        if(line == "1 + 2 + 3") {
            println("?")
        } else {
            println("ERROR")
        }
    }
}