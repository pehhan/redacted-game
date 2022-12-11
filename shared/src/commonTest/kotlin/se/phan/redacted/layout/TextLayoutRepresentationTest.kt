package se.phan.redacted.layout

import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import se.phan.redacted.parser.TextParser
import se.phan.redacted.text.Space
import se.phan.redacted.text.Word
import kotlin.test.Test

class TextLayoutRepresentationTest {

    @Test
    fun `text parts are converted to correct text layout representation`() {
        val str = """
            Paul Atreides
            
            After two newlines
            After one newline
            
            Two again
        """.trimIndent()

        val text = TextParser.parse(str)

        val expectedRepresentation = listOf(
            TextLayoutRepresentation.Paragraph(
                listOf(
                    Word("Paul"),
                    Space,
                    Word("Atreides")
                )
            ),
            TextLayoutRepresentation.VerticalSpace,
            TextLayoutRepresentation.VerticalSpace,
            TextLayoutRepresentation.Paragraph(
                listOf(
                    Word("After"),
                    Space,
                    Word("two"),
                    Space,
                    Word("newlines")
                )
            ),
            TextLayoutRepresentation.VerticalSpace,
            TextLayoutRepresentation.Paragraph(
                listOf(
                    Word("After"),
                    Space,
                    Word("one"),
                    Space,
                    Word("newline")
                )
            ),
            TextLayoutRepresentation.VerticalSpace,
            TextLayoutRepresentation.VerticalSpace,
            TextLayoutRepresentation.Paragraph(
                listOf(
                    Word("Two"),
                    Space,
                    Word("again")
                )
            )
        )

        expect(text.parts.toLayoutRepresentation()).toEqual(expectedRepresentation)
    }
}