package se.phan.redacted.text

import se.phan.redacted.normalized

actual fun Word.normalized(): Word {
    return Word(value.normalized(), redacted)
}