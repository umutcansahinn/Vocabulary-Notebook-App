package com.umutcansahin.languageword.feature_word.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.umutcansahin.languageword.feature_word.domain.model.WordsFromApi

data class WordDto(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("timestamp")
    val timestamp: Int?,
    @SerializedName("color")
    val color: Int?,
    @SerializedName("sentence")
    val sentence: Int?,
)

fun WordDto.toWordsFromApi(): WordsFromApi {
    return WordsFromApi(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp?.toLong(),
        color = color,
        sentence = sentence
    )
}