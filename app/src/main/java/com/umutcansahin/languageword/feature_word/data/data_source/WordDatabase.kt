package com.umutcansahin.languageword.feature_word.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.umutcansahin.languageword.feature_word.domain.model.Word

@Database(entities = [Word::class], version = 1)
abstract class WordDatabase: RoomDatabase() {
    abstract val wordDao: WordDao
}