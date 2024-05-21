package it.wlp.android.jettrvia.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.wlp.android.jettrvia.data.DataOrException
import it.wlp.android.jettrvia.model.QuestionItem
import it.wlp.android.jettrvia.repo.QuestionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val repository: QuestionRepository) :
    ViewModel() {

    val data: MutableState<DataOrException<ArrayList<QuestionItem>, Exception>> = mutableStateOf(
        DataOrException(
            null, true,
            Exception("")
        )
    )

    init {
        getQuestions()
    }


    private fun getQuestions() {

        viewModelScope.launch {

            data.value.loading = true
            data.value = repository.getQuestions()
            if (data.value.data.toString().isNotEmpty())
                data.value.loading = false
        }
    }
}