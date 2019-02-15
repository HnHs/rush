package rush

import rush.SyntaxKind.*

class Lexer(private val text: String) {
    private val eof: Char = '\u0000'
    private var position: Int = 0;

    private val current: Char
        get() {
            if (position >= text.length)
                return eof
            return text[position]
        }

    public fun nextToken(): SyntaxToken {

        if (position >= text.length) {
            return SyntaxToken(EndOfFileToken, position, eof.toString(), null)
        }

        if (current.isDigit()) {
            return nextDigitToken()
        }

        if (current.isWhitespace()) {
            return nextWhiteSpaceToken()
        }

        if (current == '+') {
            return SyntaxToken(PlusToken, position++, "+", null)
        }

        if (current == '-') {
            return SyntaxToken(MinusToken, position++, "-", null)
        }

        if (current == '/') {
            return SyntaxToken(SlashToken, position++, "/", null)
        }

        if (current == '*') {
            return SyntaxToken(StarToken, position++, "*", null)
        }

        if (current == '(') {
            return SyntaxToken(OpenParenthesisToken, position++, "(", null)
        }

        if (current == ')') {
            return SyntaxToken(CloseParenthesisToken, position++, ")", null)
        }

        return SyntaxToken(BadToken, position++, text.substring(position - 1, position), null)

    }

    private fun nextWhiteSpaceToken(): SyntaxToken {
        val start = position

        while (current.isWhitespace()) {
            next()
        }

        val length = position - start
        val tokenText = text.substring(start, (start + length))

        return SyntaxToken(WhiteSpaceToken, start, tokenText, String.empty)
    }

    private fun nextDigitToken(): SyntaxToken {
        val start = position

        while (current.isDigit()) {
            next()
        }

        val length = position - start
        val tokenText = text.substring(start, (start + length))

        return SyntaxToken(NumberToken, start, tokenText, tokenText.toInt())
    }

    private fun next() {
        position++
    }
}