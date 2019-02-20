package codeAnalysis

class SyntaxTree(val diagnostics: List<String>, val root: ExpressionSyntax, val endOfFileToken: SyntaxToken) {
    companion object {
        fun parse(text: String): SyntaxTree {
            val parser = Parser(text)
            return parser.parse()
        }
    }
}