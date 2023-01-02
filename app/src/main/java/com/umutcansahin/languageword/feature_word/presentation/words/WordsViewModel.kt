package com.umutcansahin.languageword.feature_word.presentation.words

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.languageword.feature_word.domain.model.Word
import com.umutcansahin.languageword.feature_word.domain.use_case.WordUseCases
import com.umutcansahin.languageword.feature_word.domain.util.OrderType
import com.umutcansahin.languageword.feature_word.domain.util.WordOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordsViewModel @Inject constructor(
    private val wordUseCases: WordUseCases
) : ViewModel() {

    private val _state = mutableStateOf(WordsState())
    val state: State<WordsState> = _state

    private var recentlyDeletedWord: Word? = null

    private var getWordsJob: Job? = null

    init {
        getAllWord(WordOrder.Date(OrderType.Descending))
    }

    fun onEvent(event:WordsEvent) {
        when(event) {
            is WordsEvent.Order -> {
                if (
                    state.value.wordOrder::class == event.wordOrder::class &&
                    state.value.wordOrder.orderType == event.wordOrder.orderType
                ) {
                    return
                }
                getAllWord(event.wordOrder)
            }
            is WordsEvent.DeleteWord -> {
                viewModelScope.launch {
                    wordUseCases.deleteWord(event.word)
                    recentlyDeletedWord = event.word
                }
            }
            is WordsEvent.RestoreWord -> {
                viewModelScope.launch {
                    wordUseCases.addWord(recentlyDeletedWord ?: return@launch)
                    recentlyDeletedWord = null
                }
            }
            is WordsEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
        }
    }

    private fun getAllWord(wordOrder: WordOrder) {
        getWordsJob?.cancel()
        getWordsJob = wordUseCases.getAllWord(wordOrder).onEach { words ->
            _state.value = state.value.copy(
                words = words,
                wordOrder = wordOrder
            )
        }.launchIn(viewModelScope)
    }
}