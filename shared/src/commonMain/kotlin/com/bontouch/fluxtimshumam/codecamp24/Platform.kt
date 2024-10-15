package com.bontouch.fluxtimshumam.codecamp24

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform