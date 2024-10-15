package com.bontouch.fluxtimshumam.codecamp24

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.Serializable

class Greeting {
    private val client = HttpClient()
    private val platform = getPlatform()

    suspend fun greet(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}