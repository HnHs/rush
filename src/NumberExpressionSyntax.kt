package rush

import rush.SyntaxKind.*

class NumberExpressionSyntax(val numberToken: SyntaxToken) : ExpressionSyntax() {
    override val syntaxKind: SyntaxKind = NumberExpression

    override fun getChildren(): List<SyntaxNode> {
        return listOf(numberToken)
    }
}