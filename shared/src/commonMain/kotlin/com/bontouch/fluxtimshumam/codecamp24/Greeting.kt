package com.bontouch.fluxtimshumam.codecamp24

import com.bontouch.fluxtimshumam.codecamp24.model.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class Greeting {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            header("Bearer", apiKey)
        }
    }
    private val platform = getPlatform()
    private val apiBasePath = "https://api.themoviedb.org/3/movie/"
    private val apiKey = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZGE5OTE5YTFmN2MwNTk1MWJiMzRjMjQ2MTMwNzJiNiIsIm5iZiI6MTcyODk5MTc5OC44NDYwMjcsInN1YiI6IjY3MGU0ZTM1ZjU4YTkyMDZhYTQxZGRmNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.mgF5U4REWISxMwbTeYU1fYyeSyyYFT-O5jbl73jwy1c"

    suspend fun greet(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }

    suspend fun getPopularMovies(): MoviesResponse {
        val path = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1"
        val response = client.get(path)
        return response.body()
    }

}