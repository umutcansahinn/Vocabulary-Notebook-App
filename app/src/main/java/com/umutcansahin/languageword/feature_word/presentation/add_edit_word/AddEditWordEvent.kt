package com.umutcansahin.languageword.feature_word.presentation.add_edit_word

import androidx.compose.ui.focus.FocusState

sealed class AddEditWordEvent {

    data class EnteredTitle(val value: String): AddEditWordEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditWordEvent()

    data class EnteredContent(val value: String): AddEditWordEvent()
    data class ChangeContentFocus(val focusState: FocusState): AddEditWordEvent()


    data class EnteredSentence(val value: String?): AddEditWordEvent()
    data class ChangeSentenceFocus(val focusState: FocusState): AddEditWordEvent()

    data class ChangeColor(val color: Int): AddEditWordEvent()
    object SaveWord: AddEditWordEvent()
}
