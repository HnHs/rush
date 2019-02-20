package codeAnalysis

import codeAnalysis.SyntaxKind.*

class Parser {

    private val tokens = mutableListOf<SyntaxToken>()
    private var position: Int = 0
    private val current: SyntaxToken get() = peek(0)
    val diagnostics: MutableList<String> = mutableListOf()

    constructor(text: String) {
        val lexer = Lexer(text);
        var token: SyntaxToken
        do {
            token = lexer.nextToken()

            if (token.syntaxKind != WhiteSpaceToken &&
                    token.syntaxKind != BadToken)
                tokens.add(token)
        } while (token.syntaxKind != EndOfFileToken)

        diagnostics.addAll(lexer.diagnostics)
    }

    fun parse(): SyntaxTree {
        var expression = parseTerm()
        var endOfFileToken = match(EndOfFileToken)
        return SyntaxTree(diagnostics, expression, endOfFileToken)
    }

    private fun parseTerm(): ExpressionSyntax {
        var left = parseFactor();

        while (current.syntaxKind == PlusToken ||
                current.syntaxKind == MinusToken) {
            val operatorToken = next()
            val right = parseFactor()
            left = BinaryExpressionSyntax(left, operatorToken, right)
        }

        return left
    }

    private fun parseFactor(): ExpressionSyntax {
        var left = parsePrimaryExpression();

        while (current.syntaxKind == StarToken ||
                current.syntaxKind == SlashToken) {
            val operatorToken = next()
            val right = parsePrimaryExpression()
            left = BinaryExpressionSyntax(left, operatorToken, right)
        }

        return left
    }

    private fun parseExpression(): ExpressionSyntax = parseTerm()

    private fun parsePrimaryExpression(): ExpressionSyntax {

        if (current.syntaxKind == OpenParenthesisToken) {
            val left = next()
            val expression = parseExpression()
            val right = match(CloseParenthesisToken)
            return ParenthesizedExpressionSyntax(left, expression, right)
        }

        val numberToken = match(NumberToken)
        return NumberExpressionSyntax(numberToken)
    }

    private fun next(): SyntaxToken {
        val now = current
        position++
        return now
    }

    private fun match(kind: SyntaxKind): SyntaxToken {
        if (current.syntaxKind == kind)
            return next()

        diagnostics.add("ERROR: Unexected token <${current.syntaxKind}>, expected<$kind>")
        return SyntaxToken(kind, current.position, String.empty, null)
    }

    private fun peek(offset: Int): SyntaxToken {
        val index = position + offset
        if (index >= tokens.size)
            tokens[tokens.size - 1]
        return tokens[index]
    }
}