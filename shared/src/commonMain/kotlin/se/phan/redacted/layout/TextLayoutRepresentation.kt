package se.phan.redacted.layout

import se.phan.redacted.text.Newline
import se.phan.redacted.text.Punctuation
import se.phan.redacted.text.Space
import se.phan.redacted.text.TextPart
import se.phan.redacted.text.Word

sealed class TextLayoutRepresentation {
    data class Paragraph(val parts: List<TextPart>) : TextLayoutRepresentation()
    object VerticalSpace : TextLayoutRepresentation()
}

fun List<TextPart>.toLayoutRepresentation(): List<TextLayoutRepresentation> {
    return fold(emptyList()) { list, part ->
        when (part) {
            is Word, is Punctuation, is Space -> {
                val last = list.lastOrNull()
                if (last is TextLayoutRepresentation.Paragraph) {
                    val lastParts = last.parts + part
                    list.take(list.size - 1) + TextLayoutRepresentation.Paragraph(lastParts)
                } else {
                    list + TextLayoutRepresentation.Paragraph(listOf(part))
                }
            }
            is Newline -> {
                list + TextLayoutRepresentation.VerticalSpace
            }
        }
    }
}