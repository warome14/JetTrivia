package it.wlp.android.jettrvia.net

import it.wlp.android.jettrvia.model.Questions
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {

    @GET("animals.json")
    suspend fun getQuestions(): Questions
}