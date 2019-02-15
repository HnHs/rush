package rush

class SyntaxToken(override val syntaxKind: SyntaxKind, val position: Int, val text: String, val value: Any?)
    : SyntaxNode() {
    override fun getChildren(): List<SyntaxNode> {
        return emptyList()
    }
}