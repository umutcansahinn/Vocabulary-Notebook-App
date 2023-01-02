package com.umutcansahin.languageword.feature_word.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.umutcansahin.languageword.ui.theme.*

@Entity
data class Word(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val sentence: String? = null,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val wordColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidWordException(message: String) : Exception(message)
