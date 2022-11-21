package se.phan.redacted.parser

object SpecialCharacters {

    val PUNCTUATION = listOf(
        '.', ',', '!', '?', '¡', '¿', ':', ';', '-', '_', '(', ')', '[', ']', '{', '}',
        '\'', '"', '´', '`', '¨', '”', '’', '$', '€', '¢', '£', '¥', '#', '&', '@', '©',
        '§', '/', '\\', '+', '*', '%', '‰', '^', '<', '>', '∞', '|', '=', '≈', '≠', '±',
        '~'
    )

    val SPACE = listOf(' ')
    val NEWLINE = listOf('\n')
    val ALL = PUNCTUATION + SPACE + NEWLINE
}