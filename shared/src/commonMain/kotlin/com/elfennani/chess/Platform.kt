package com.elfennani.chess

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform