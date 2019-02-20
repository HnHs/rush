package codeAnalysis

import codeAnalysis.SyntaxKind.*

class Evaluator(private val root: ExpressionSyntax) {

    fun eval(): Int = evalExpression(root)

    private fun evalExpression(root: ExpressionSyntax): Int {
        when (root) {
            is NumberExpressionSyntax -> return evalNumberExpression(root)
            is BinaryExpressionSyntax -> return evalBinaryExpression(root)
            is ParenthesizedExpressionSyntax -> return evalParenthesizedExpression(root)
            else -> throw Throwable("Unexpected node ${root.syntaxKind}")
        }
    }

    private fun evalParenthesizedExpression(root: ParenthesizedExpressionSyntax): Int = evalExpression(root.expressionSyntax)

    private fun evalBinaryExpression(root: BinaryExpressionSyntax): Int {
        val left = evalExpression(root.left)
        val right = evalExpression(root.right)


        return when (root.operatorToken.syntaxKind) {
            PlusToken -> left + right
            StarToken -> left * right
            SlashToken -> left / right
            MinusToken -> left - right
            else -> throw Throwable("Unexpected binary operator ${root.operatorToken.syntaxKind}")
        }
    }

    private fun evalNumberExpression(root: NumberExpressionSyntax) = root.numberToken.value as Int
}