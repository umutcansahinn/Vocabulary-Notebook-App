package com.umutcansahin.languageword.feature_word.domain.use_case

data class WordUseCases(
    val getAllWord: GetAllWord,
    val deleteWord: DeleteWord,
    val addWord: AddWord,
    val getWord: GetWord,
    val getWords: GetWords
)
