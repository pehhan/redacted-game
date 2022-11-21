package se.phan.redacted.renderer

import se.phan.redacted.text.Text

interface TextRenderer {
    fun render(text: Text): String
}