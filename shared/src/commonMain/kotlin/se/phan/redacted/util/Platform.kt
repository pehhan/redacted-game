package se.phan.redacted.util

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform