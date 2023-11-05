package com.example.jhstudy.models

import com.example.jhstudy.models.CurrentUserCollection
import com.example.jhstudy.models.Exif
import com.example.jhstudy.models.Links
import com.example.jhstudy.models.Location
import com.example.jhstudy.models.Urls
import com.example.jhstudy.models.User

data class PhotoResponse(
    val blur_hash: String,
    val color: String,
    val created_at: String,
    val current_user_collections: List<CurrentUserCollection>,
    val description: String,
    val downloads: Int,
    val exif: Exif,
    val height: Int,
    val id: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val location: Location,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val width: Int
)