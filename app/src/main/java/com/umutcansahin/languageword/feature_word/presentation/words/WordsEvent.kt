package com.umutcansahin.languageword.feature_word.presentation.words

import com.umutcansahin.languageword.feature_word.domain.model.Word
import com.umutcansahin.languageword.feature_word.domain.util.WordOrder

sealed class WordsEvent {
    data class Order(val wordOrder: WordOrder): WordsEvent()
    data class DeleteWord(val word: Word): WordsEvent()
    object RestoreWord: WordsEvent()
    object ToggleOrderSection: WordsEvent()
}
