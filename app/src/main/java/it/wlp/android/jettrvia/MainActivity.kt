package it.wlp.android.jettrvia

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
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
    val loading = viewModel.data.value.loading
    val questions = viewModel.data.value.data?.toMutableList()
    if(loading == true){
        Log.d("Questions", "Question Loding...")
    }
    else{
        questions?.forEach{
            Log.d("Questions", "Question: ${it.question}")

        }
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
