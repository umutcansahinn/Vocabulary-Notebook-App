package com.umutcansahin.languageword.feature_word.presentation.explore

import com.umutcansahin.languageword.feature_word.domain.model.WordsFromApi


data class WordsListState(
    val isLoading: Boolean = false,
    val words: List<WordsFromApi> = emptyList(),
    val error: String = ""
)
