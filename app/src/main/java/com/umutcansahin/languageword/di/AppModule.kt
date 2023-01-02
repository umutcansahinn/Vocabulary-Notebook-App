package com.umutcansahin.languageword.di

import android.app.Application
import androidx.room.Room
import com.umutcansahin.languageword.common.Constants
import com.umutcansahin.languageword.feature_word.data.data_source.WordDatabase
import com.umutcansahin.languageword.feature_word.data.remote.WordApi
import com.umutcansahin.languageword.feature_word.data.repository.WordRepositoryImpl
import com.umutcansahin.languageword.feature_word.domain.repository.WordRepository
import com.umutcansahin.languageword.feature_word.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWordDatabase(app: Application): WordDatabase {
        return Room.databaseBuilder(
            app,
            WordDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWordsApi(): WordApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WordApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWordRepository(db: WordDatabase,api: WordApi): WordRepository {
        return WordRepositoryImpl(db.wordDao,api)
    }

    @Provides
    @Singleton
    fun provideWordUseCase(repository: WordRepository): WordUseCases {
        return WordUseCases(
            getAllWord = GetAllWord(repository),
            deleteWord = DeleteWord(repository),
            addWord = AddWord(repository),
            getWord = GetWord(repository),
            getWords = GetWords(repository)
        )
    }
}