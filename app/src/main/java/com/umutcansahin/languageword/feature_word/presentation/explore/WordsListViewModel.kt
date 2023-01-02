package com.umutcansahin.languageword.feature_word.presentation.explore

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.languageword.common.Resource
import com.umutcansahin.languageword.feature_word.domain.use_case.WordUseCases
import com.umutcansahin.languageword.feature_word.presentation.words.WordsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WordsListViewModel @Inject constructor(
    private val wordUseCases: WordUseCases
): ViewModel() {

    private val _state = mutableStateOf(WordsListState())
    val state: State<WordsListState> = _state

    init {
        getWords()
    }

    private fun getWords() {
        wordUseCases.getWords().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = WordsListState(words = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = WordsListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = WordsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}