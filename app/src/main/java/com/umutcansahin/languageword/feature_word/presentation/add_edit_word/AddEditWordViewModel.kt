package com.umutcansahin.languageword.feature_word.presentation.add_edit_word

import android.view.AbsSavedState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umutcansahin.languageword.feature_word.domain.model.InvalidWordException
import com.umutcansahin.languageword.feature_word.domain.model.Word
import com.umutcansahin.languageword.feature_word.domain.use_case.WordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditWordViewModel @Inject constructor(
    private val wordUseCases: WordUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _wordTitle = mutableStateOf(WordTextFieldState(hint = "Please Enter Word"))
    val wordTitle: State<WordTextFieldState> = _wordTitle

    private val _wordContent = mutableStateOf(WordTextFieldState(hint = "Please Enter Word Meaning"))
    val wordContent: State<WordTextFieldState> = _wordContent

    private val _wordSentence = mutableStateOf(WordTextFieldState(hint = "You can enter a sentence if you want"))
    val wordSentence: State<WordTextFieldState> = _wordSentence

    private val _wordColor = mutableStateOf(Word.wordColors.random().toArgb())
    val wordColor: State<Int> = _wordColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentWordId: Int? = null

    init {
        savedStateHandle.get<Int>("wordId")?.let { wordId->
            if (wordId != -1) {
                viewModelScope.launch {
                    wordUseCases.getWord(wordId)?.also { word->
                        currentWordId = word.id
                        _wordTitle.value = wordTitle.value.copy(
                            text = word.title,
                            isHintVisible = false
                        )
                        _wordContent.value = wordContent.value.copy(
                            text = word.title,
                            isHintVisible = false
                        )
                        _wordSentence.value = wordSentence.value.copy(
                            text = word.sentence ?: "",
                            isHintVisible = false
                        )
                        _wordColor.value = word.color
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditWordEvent) {
        when(event) {
            is AddEditWordEvent.EnteredTitle -> {
                _wordTitle.value = wordTitle.value.copy(
                    text = event.value
                )
            }
            is AddEditWordEvent.ChangeTitleFocus -> {
                _wordTitle.value = wordTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && wordTitle.value.text.isBlank()
                )
            }
            is AddEditWordEvent.EnteredContent -> {
                _wordContent.value = wordContent.value.copy(
                    text = event.value
                )
            }
            is AddEditWordEvent.ChangeContentFocus -> {
                _wordContent.value = wordContent.value.copy(
                    isHintVisible = !event.focusState.isFocused && wordContent.value.text.isBlank()
                )
            }
            is AddEditWordEvent.EnteredSentence -> {
                _wordSentence.value = wordSentence.value.copy(
                    text = event.value ?: ""
                )
            }
            is AddEditWordEvent.ChangeSentenceFocus -> {
                _wordSentence.value = wordSentence.value.copy(
                    isHintVisible = !event.focusState.isFocused &&wordSentence.value.text.isBlank()
                )
            }
            is AddEditWordEvent.ChangeColor -> {
                _wordColor.value = event.color
            }
            is AddEditWordEvent.SaveWord -> {
                viewModelScope.launch {
                    try {
                        wordUseCases.addWord(
                            Word(
                                title = wordTitle.value.text,
                                content = wordContent.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = wordColor.value,
                                sentence = wordSentence.value.text,
                                id = currentWordId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveWord)
                    }catch (e: InvalidWordException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save word"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveWord : UiEvent()
    }
}