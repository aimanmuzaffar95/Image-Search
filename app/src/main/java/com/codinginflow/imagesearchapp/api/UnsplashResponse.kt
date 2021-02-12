package com.codinginflow.imagesearchapp.api

import com.codinginflow.imagesearchapp.models.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)