package it.wlp.android.jettrvia.compoments

import android.graphics.DashPathEffect
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.wlp.android.jettrvia.model.QuestionItem
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

//@Preview
@Composable
fun QuestionDisplay(question: QuestionItem
                    ,questionIndex: MutableState<Int>
                ,questionViewModel: QuestionViewModel
                ,onClickNext: (Int) -> Unit = {}) {

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 0f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(4.dp), color = Colors.mDarkPurple
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            QuestionTracker()
            drawDashPathEffect(pathEffect)
            Column {
                Text(text = question.question
                , modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .padding(6.dp)
                    .align(Alignment.Start)
                , fontSize = 17.sp
                , color = Colors.mOffWhite
                , fontWeight = FontWeight.Bold
                , lineHeight = TextUnit(22f, TextUnitType.Sp)
                )
                RowAnswer()
            }
        }
    }
}

@Preview
@Composable
fun RowAnswer()
{
    Row(modifier = Modifier.padding(3.dp)
        .fillMaxWidth()
        .height(55.dp)
        .border(width = 3.dp
            , brush = Brush.linearGradient(colors =
                listOf(Colors.mOffDarkPurple,Colors.mLightGray))
            , shape = RoundedCornerShape(15.dp)
        )
        .clip(RoundedCornerShape(topEndPercent = 50
           , topStartPercent = 50
           , bottomStartPercent = 50
           , bottomEndPercent = 50))
        .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically) {

    }
}

@Composable
fun QuestionTracker(counter: Int = 10, totalQuestions: Int = 100) =
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(TextAlign.Start, textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = Colors.mLightGray, fontWeight = FontWeight.Bold, fontSize = 27.sp
                )
            ) {
                append("Question: $counter/")
            }
            withStyle(
                style = SpanStyle(
                    color = Colors.mLightGray, fontWeight = FontWeight.Light, fontSize = 14.sp
                )
            ) {
                append("$totalQuestions")
            }

        }
    }, modifier = Modifier.padding(12.dp))


@Composable
fun drawDashPathEffect(pathEffect: PathEffect) {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)) {

        drawLine(color = Colors.mLightGray, start = Offset(0f, 0f)
            , end = Offset(size.width, 0f), pathEffect = pathEffect)
    }
}