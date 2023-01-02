package com.umutcansahin.languageword.feature_word.domain.use_case

import com.umutcansahin.languageword.common.Resource
import com.umutcansahin.languageword.feature_word.data.remote.dto.toWordsFromApi
import com.umutcansahin.languageword.feature_word.domain.model.WordsFromApi
import com.umutcansahin.languageword.feature_word.domain.repository.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetWords(
    private val repository: WordRepository
) {

    operator fun invoke(): Flow<Resource<List<WordsFromApi>>> = flow {
        try {
            emit(Resource.Loading<List<WordsFromApi>>())
            val words = repository.getWords().map { it.toWordsFromApi() }
            emit(Resource.Success<List<WordsFromApi>>(words))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<WordsFromApi>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<WordsFromApi>>("Couldn't reach server. Check your internet connection."))
        }

    }


}