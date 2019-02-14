package rush

fun main(args: Array<String>) {
    while (true) {
        print("> ")
        val line = readLine()
        if(line.isNullOrEmpty())
            return;

        val lexer = Lexer(line)
        while (true) {
            val token = lexer.nextToken()
            if(token.syntaxKind == SyntaxKind.EndOfFileToken)
                break;

            println("${token.syntaxKind}: ${token.text} => ${token.value}")

        }
    }
}