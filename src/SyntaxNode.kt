package rush

abstract class SyntaxNode {

    abstract val syntaxKind: SyntaxKind

    abstract fun getChildren(): List<SyntaxNode>
}