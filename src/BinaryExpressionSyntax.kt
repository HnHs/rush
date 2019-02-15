package rush

import rush.SyntaxKind.*

class BinaryExpressionSyntax(val left: ExpressionSyntax, val operatorToken: SyntaxToken, val right: ExpressionSyntax)
    : ExpressionSyntax() {
    override val syntaxKind: SyntaxKind = BinaryExpression



    override fun getChildren(): List<SyntaxNode> {
        return listOf(left, operatorToken, right)
    }
}