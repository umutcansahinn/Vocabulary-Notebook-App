package com.umutcansahin.languageword.feature_word.data.remote

import com.umutcansahin.languageword.feature_word.data.remote.dto.WordDto
import retrofit2.http.GET

interface WordApi {

    @GET("umutcansahinn/json/main/words.json")
    suspend fun getWords(): List<WordDto>
}