package it.wlp.android.jettrvia.screens

import androidx.compose.runtime.Composable
import it.wlp.android.jettrvia.compoments.Questions


@Composable
fun TriviaHome(viewModel: QuestionViewModel) {
    Questions(viewModel)
}


