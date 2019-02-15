package rush

import rush.SyntaxKind.*

class Parser {

    private val tokens = mutableListOf<SyntaxToken>()
    private var position: Int = 0
    private val current: SyntaxToken get() = peek(0)

    public constructor(text: String) {
        val lexer = Lexer(text);
        var token: SyntaxToken
        do {
            token = lexer.nextToken()

            if (token.syntaxKind != WhiteSpaceToken &&
                    token.syntaxKind != BadToken)
                tokens.add(token)
        } while (token.syntaxKind != EndOfFileToken)
    }

    public fun parse(): ExpressionSyntax {
        var left = parsePrimaryExpression();

        while (current.syntaxKind == PlusToken ||
                current.syntaxKind == MinusToken) {
            val operatorToken = next()
            val right = parsePrimaryExpression()
            left = BinaryExpressionSyntax(left, operatorToken, right)
        }

        return left
    }

    private fun parsePrimaryExpression(): ExpressionSyntax {
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

        return SyntaxToken(kind, current.position, String.empty, null)
    }

    private fun peek(offset: Int): SyntaxToken {
        val index = position + offset
        if (index >= tokens.size)
            tokens[tokens.size - 1]
        return tokens[index]
    }
}