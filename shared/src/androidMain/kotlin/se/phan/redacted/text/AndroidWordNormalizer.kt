package se.phan.redacted.text

import java.text.Normalizer

actual fun Word.normalized(): Word {
    val normalized = Regex("\\p{InCombiningDiacriticalMarks}+")
        .replace(Normalizer.normalize(value, Normalizer.Form.NFD), "")

    return Word(normalized, redacted)
}