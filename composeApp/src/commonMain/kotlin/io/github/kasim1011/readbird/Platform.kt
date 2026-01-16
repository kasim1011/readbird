package io.github.kasim1011.readbird

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform