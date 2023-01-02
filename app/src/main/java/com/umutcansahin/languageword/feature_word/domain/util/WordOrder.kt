package com.umutcansahin.languageword.feature_word.domain.util

import com.umutcansahin.languageword.feature_word.domain.model.Word

sealed class WordOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): WordOrder(orderType)
    class Date(orderType: OrderType): WordOrder(orderType)

    fun copy(orderType: OrderType): WordOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
        }
    }
}
