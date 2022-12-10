package se.phan.redacted.renderer

import se.phan.redacted.text.Newline
import se.phan.redacted.text.Punctuation
import se.phan.redacted.text.Space
import se.phan.redacted.text.Text
import se.phan.redacted.text.TextPart
import se.phan.redacted.text.Word

class TrueWordLengthPunctuationVisibleRenderer : TextRenderer {

    override fun render(text: Text): String {
        return text.parts.fold("") { result, part ->
            result + render(part)
        }
    }

    override fun render(textPart: TextPart): String {
        return when (textPart) {
            is Word -> renderWord(textPart)
            is Punctuation -> renderPunctuation(textPart).toString()
            is Space -> renderSpace()
            is Newline -> renderNewline()
        }
    }

    private fun renderWord(word: Word): String {
        return if (word.redacted) {
            REDACTED_SIGN.repeat(word.value.length)
        } else {
            word.value
        }
    }

    private fun renderPunctuation(punctuation: Punctuation): Char {
        return punctuation.sign
    }

    private fun renderSpace(): String {
        return SPACE_SIGN
    }

    private fun renderNewline(): String {
        return NEWLINE_SIGN
    }

    companion object {
        private const val SPACE_SIGN = " "
        private const val NEWLINE_SIGN = "\n"
        private const val REDACTED_SIGN = "█"
    }
}