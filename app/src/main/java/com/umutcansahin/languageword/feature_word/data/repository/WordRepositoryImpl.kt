package com.umutcansahin.languageword.feature_word.data.repository

import com.umutcansahin.languageword.feature_word.data.data_source.WordDao
import com.umutcansahin.languageword.feature_word.data.remote.WordApi
import com.umutcansahin.languageword.feature_word.data.remote.dto.WordDto
import com.umutcansahin.languageword.feature_word.domain.model.Word
import com.umutcansahin.languageword.feature_word.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordRepositoryImpl (
    private val dao: WordDao,
    private val api: WordApi
) : WordRepository{
    override fun getAllWord(): Flow<List<Word>> {
        return dao.getAllWord()
    }

    override suspend fun getWordById(id: Int): Word? {
        return dao.getWordById(id)
    }

    override suspend fun insertWord(word: Word) {
        dao.insertWord(word)
    }

    override suspend fun deleteWord(word: Word) {
        dao.deleteWord(word)
    }

    override suspend fun getWords(): List<WordDto> {
        return api.getWords()
    }
}