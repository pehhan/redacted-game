package se.phan.redacted.guess

import se.phan.redacted.normalized

actual fun Guess.normalized(): Guess {
    return Guess(value.normalized())
}