package rush

fun pprint(syntaxNode: SyntaxNode, indent: String = "", isLast: Boolean = true) {

    val marker = if (isLast) "└──" else "├──"

    print("$indent$marker${syntaxNode.syntaxKind}")

    if (syntaxNode is SyntaxToken && syntaxNode.value != null) {
        print(" ${syntaxNode.value}")
    }

    println()

    val newIdent = "$indent${if (isLast) "    " else "│   "}"

    var lastChild = syntaxNode.getChildren().lastOrNull()
    for (child in syntaxNode.getChildren()) {
        pprint(child, newIdent, child === lastChild)
    }
}

fun main(args: Array<String>) {
    while (true) {
        print("> ")
        val line = readLine()
        if (line.isNullOrEmpty())
            return;

        val parser = Parser(line)
        var expressionSyntax = parser.parse()

        pprint(expressionSyntax);

        if (parser.diagnostics.any())
            parser.diagnostics.forEach { println(it) }
    }
}