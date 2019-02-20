package codeAnalysis


class ParenthesizedExpressionSyntax(val openParenthesisToken: SyntaxToken, val expressionSyntax: ExpressionSyntax, val closeParenthesis: SyntaxToken)
    : ExpressionSyntax() {

    override val syntaxKind: SyntaxKind = SyntaxKind.ParenthesizedExpression
    override fun getChildren(): List<SyntaxNode> = listOf(openParenthesisToken, expressionSyntax, closeParenthesis)

}
