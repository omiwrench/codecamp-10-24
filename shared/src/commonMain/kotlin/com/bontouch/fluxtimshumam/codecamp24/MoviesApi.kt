package com.bontouch.fluxtimshumam.codecamp24

import com.bontouch.fluxtimshumam.codecamp24.model.ApiMovie
import com.bontouch.fluxtimshumam.codecamp24.model.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.resources.get
import io.ktor.client.request.header
import io.ktor.resources.Resource
import io.ktor.client.plugins.resources.Resources
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class MoviesApi {
    private fun jsonConfig() = Json {
        encodeDefaults = true
        prettyPrint = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val client = HttpClient {
        defaultRequest {
            header("Authorization", "Bearer $TMDB_AUTH_KEY")
            url {
                host = "api.themoviedb.org"
                protocol = URLProtocol.HTTPS
            }
        }

        install(ContentNegotiation) {
            json(jsonConfig())
        }

        install(Resources)
    }

    suspend fun getPopularMovies(): List<ApiMovie> = withContext(Dispatchers.IO) {
        val response = client.get(V3.Discover.Movie(V3.Discover())).body<MoviesResponse>()
        response.results
    }
}

@Resource("/3")
private object V3 {
    @Resource("/discover")
    class Discover(val parent: V3 = V3) {
        @Resource("/movie")
        class Movie(val parent: Discover) {
            @Resource("/popular&page=1")
            class Popular(val parent: Movie, val language: String = "en-US", val page: Int = 1)
        }
    }
}