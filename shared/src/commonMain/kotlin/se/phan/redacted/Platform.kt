package se.phan.redacted

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform