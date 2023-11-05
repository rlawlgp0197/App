package com.example.jhstudy.model

data class UnsplashResponse(val result: List<PhotoData>)
data class PhotoData(val id: String, val url: String)
