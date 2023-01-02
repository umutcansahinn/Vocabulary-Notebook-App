package com.umutcansahin.languageword.feature_word.domain.repository

import com.umutcansahin.languageword.feature_word.data.remote.dto.WordDto
import com.umutcansahin.languageword.feature_word.domain.model.Word
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun getAllWord(): Flow<List<Word>>

    suspend fun getWordById(id: Int): Word?

    suspend fun insertWord(word: Word)

    suspend fun deleteWord(word: Word)

    suspend fun getWords(): List<WordDto>
}