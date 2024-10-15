package com.bontouch.fluxtimshumam.codecamp24.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse (
    val page: Int,
    val results: List<ApiMovie>
)