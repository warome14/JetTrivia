package it.wlp.android.jettrvia.repo

import android.util.Log
import it.wlp.android.jettrvia.data.DataOrException
import it.wlp.android.jettrvia.model.QuestionItem
import it.wlp.android.jettrvia.net.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val questionApi: QuestionApi){

    private val response = DataOrException<ArrayList<QuestionItem>, Exception>()


    suspend fun getQuestions(): DataOrException<ArrayList<QuestionItem>, Exception> {

        try {
            response.loaded = false
            response.data = questionApi.getQuestions().questionItem
            if (response.data.toString().isNotEmpty())
                response.loaded = true

        } catch (e: Exception) {
            Log.e("DataOrException","Error: ${e.message}")
            this.response.exception = e
        }

        return response
    }
}