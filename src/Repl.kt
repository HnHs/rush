import codeAnalysis.Evaluator
import codeAnalysis.SyntaxNode
import codeAnalysis.SyntaxToken
import codeAnalysis.SyntaxTree

fun main() {
    var showTree = false

    while (true) {
        print("> ")
        val line = readLine()
        if (line.isNullOrEmpty())
            return;

        if (line == "#showTree") {
            showTree = !showTree
            println(if (showTree) "Showing parse tree" else "Hiding parse tree")
            continue
        }

        var syntaxTree = SyntaxTree.parse(line)

        if (showTree) pprint(syntaxTree.root);

        if (syntaxTree.diagnostics.any())
            syntaxTree.diagnostics.forEach { println(it) }
        else {
            var result = Evaluator(syntaxTree.root).eval()
            println("= $result")

        }
    }
}

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
