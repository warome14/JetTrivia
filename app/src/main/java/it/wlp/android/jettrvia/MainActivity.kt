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
import it.wlp.android.jettrvia.screens.TriviaHome
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



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetTrviaTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hello, World!")
        }
    }
}
