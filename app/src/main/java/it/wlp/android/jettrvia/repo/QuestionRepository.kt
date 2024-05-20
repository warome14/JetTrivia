package it.wlp.android.jettrvia.repo

import it.wlp.android.jettrvia.data.DataOrException
import it.wlp.android.jettrvia.model.QuestionItem
import it.wlp.android.jettrvia.net.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val questionApi: QuestionApi){

    private val listOfQuestions = DataOrException<Array<QuestionItem>, Exception>()


}