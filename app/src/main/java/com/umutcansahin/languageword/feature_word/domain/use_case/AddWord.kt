package com.umutcansahin.languageword.feature_word.domain.use_case

import com.umutcansahin.languageword.feature_word.domain.model.InvalidWordException
import com.umutcansahin.languageword.feature_word.domain.model.Word
import com.umutcansahin.languageword.feature_word.domain.repository.WordRepository

class AddWord(
    private val repository: WordRepository
) {

    @Throws(InvalidWordException::class)
    suspend operator fun invoke(word: Word) {
        if (word.title.isBlank()) {
            throw InvalidWordException("The title of the note can't be empty.")
        }
        if (word.content.isBlank()){
            throw InvalidWordException("The content of the note can't be empty.")
        }
        repository.insertWord(word)
    }
}