package com.bontouch.fluxtimshumam.codecamp24

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}