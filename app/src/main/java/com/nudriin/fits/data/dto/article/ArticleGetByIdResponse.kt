package com.nudriin.fits.data.dto.article

import com.google.gson.annotations.SerializedName

data class ArticleGetByIdResponse(

    @field:SerializedName("success")
    val success: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("article")
    val article: Article
)

data class Article(

    @field:SerializedName("date")
    val createdAt: String,

    @field:SerializedName("img_url")
    val imgUrl: String,

    @field:SerializedName("author")
    val author: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("content")
    val content: String
)
