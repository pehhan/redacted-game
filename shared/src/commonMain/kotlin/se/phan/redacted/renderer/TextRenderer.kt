package se.phan.redacted.renderer

import se.phan.redacted.text.Text
import se.phan.redacted.text.TextPart

interface TextRenderer {
    fun render(text: Text): String
    fun render(textPart: TextPart): String
}