package se.phan.redacted

import java.text.Normalizer

fun String.normalized(): String {
    return Regex("\\p{InCombiningDiacriticalMarks}+")
        .replace(Normalizer.normalize(this, Normalizer.Form.NFD), "")
}