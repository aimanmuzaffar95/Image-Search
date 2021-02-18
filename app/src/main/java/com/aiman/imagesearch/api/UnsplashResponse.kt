package com.aiman.imagesearch.api

import com.aiman.imagesearch.models.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)