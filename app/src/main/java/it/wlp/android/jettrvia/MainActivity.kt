package it.wlp.android.jettrvia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import it.wlp.android.jettrvia.model.Questions
import it.wlp.android.jettrvia.screens.QuestionViewModel
import it.wlp.android.jettrvia.ui.theme.JetTrviaTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTrviaTheme {

                val viewModel: QuestionViewModel by viewModels()

                TriviaHome(viewModel)
            }
        }
    }
}

@Composable
fun TriviaHome(viewModel: QuestionViewModel) {
    Questions(viewModel)
}

@Composable
fun Questions(viewModel: QuestionViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()

    if (!viewModel.data.value.loaded!!) {
        Log.d("Questions", "Loading...")
    } else {
        Log.d("Questions", "Questions: ${questions.toString()}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetTrviaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello, World!")
        }
    }
}
