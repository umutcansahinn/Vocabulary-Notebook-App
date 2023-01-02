package com.umutcansahin.languageword.feature_word.presentation.words

import com.umutcansahin.languageword.feature_word.domain.model.Word
import com.umutcansahin.languageword.feature_word.domain.util.OrderType
import com.umutcansahin.languageword.feature_word.domain.util.WordOrder

data class WordsState(
    val words: List<Word> = emptyList(),
    val wordOrder: WordOrder = WordOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
