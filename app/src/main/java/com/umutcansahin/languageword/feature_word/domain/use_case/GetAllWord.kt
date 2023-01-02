package com.umutcansahin.languageword.feature_word.domain.use_case

import com.umutcansahin.languageword.feature_word.domain.model.Word
import com.umutcansahin.languageword.feature_word.domain.repository.WordRepository
import com.umutcansahin.languageword.feature_word.domain.util.OrderType
import com.umutcansahin.languageword.feature_word.domain.util.WordOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllWord(
    private val repository: WordRepository
) {

    operator fun invoke(
        wordOrder: WordOrder = WordOrder.Date(OrderType.Descending)
    ): Flow<List<Word>> {
        return repository.getAllWord().map { words ->
            when(wordOrder.orderType) {
                is OrderType.Ascending -> {
                    when(wordOrder) {
                        is WordOrder.Title -> {words.sortedBy { it.title.lowercase() }}
                        is WordOrder.Date -> { words.sortedBy { it.timestamp }}
                    }
                }
                is OrderType.Descending -> {
                    when(wordOrder) {
                        is WordOrder.Title -> { words.sortedByDescending { it.title.lowercase() }}
                        is WordOrder.Date -> { words.sortedByDescending { it.timestamp } }
                    }
                }
            }
        }
    }
}