package com.example.fitnesswraps

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform