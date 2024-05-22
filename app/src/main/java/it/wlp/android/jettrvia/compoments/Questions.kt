package it.wlp.android.jettrvia.compoments

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.wlp.android.jettrvia.screens.QuestionViewModel
import it.wlp.android.jettrvia.util.Colors

@Composable
fun Questions(viewModel: QuestionViewModel) {
    val loading = viewModel.data.value.loading
    val questions = viewModel.data.value.data?.toMutableList()
    if (loading == true) {
        Log.d("Questions", "Question Loding...")
    } else {
        questions?.forEach {
            Log.d("Questions", "Question: ${it.question}")
        }
    }
}

@Preview
@Composable
fun QuestionDisplay() =
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(4.dp), color = Colors.mDarkPurple) {

        Column(modifier = Modifier.padding(12.dp)
            , horizontalAlignment = Alignment.Start
            , verticalArrangement = Arrangement.Top) {
            QuestionTracker()

        }
    }

@Composable
fun QuestionTracker(counter: Int = 10,  totalQuestions: Int = 100) = Text(text = buildAnnotatedString {
    withStyle(style = ParagraphStyle(TextAlign.Start, textIndent = TextIndent.None)){
        withStyle(style = SpanStyle(color = Colors.mLightGray
            ,fontWeight = FontWeight.Bold
            ,fontSize = 27.sp)){
            append("Question: $counter/")
        }
        withStyle(style = SpanStyle(color = Colors.mLightGray
            ,fontWeight = FontWeight.Light
            ,fontSize = 14.sp)){
            append("$totalQuestions")
        }

    }
}, modifier = Modifier.padding(12.dp))