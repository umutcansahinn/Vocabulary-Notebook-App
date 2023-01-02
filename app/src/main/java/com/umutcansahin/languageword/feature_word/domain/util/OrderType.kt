package com.umutcansahin.languageword.feature_word.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
