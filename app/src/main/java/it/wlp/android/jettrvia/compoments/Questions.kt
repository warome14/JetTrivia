package it.wlp.android.jettrvia.compoments

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

//@Preview
@Composable
fun Questions(viewModel: QuestionViewModel) {
    val loading = viewModel.data.value.loading
    val questions = viewModel.data.value.data?.toMutableList()

    val questionIndex = remember {
        mutableIntStateOf(0)
    }

    val question = questions?.get(questionIndex.intValue)



    if (loading == true)
        CircularProgressIndicator()
     else
        if(questions != null)
            QuestionDisplay(question = question!!
                , totalQuestions = questions.size
                , questionIndex = questionIndex
                , questionViewModel = viewModel
                ){ questionIndex.intValue = questionIndex.intValue + 1 }
}

//@Preview
@Composable
fun QuestionDisplay(
    totalQuestions: Int,
    question: QuestionItem,
    questionIndex: MutableIntState,
    questionViewModel: QuestionViewModel,
    onNextClicked: (Int) -> Unit = {}
) {

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 20f), 0f)

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }


    val choicesState = remember(question) {
        question.choices.toMutableList()
    }

    val updateAnswer: (Int) -> Unit = remember(question) {
        {
            answerState.value = it
            correctAnswerState.value = question.answer == choicesState[it]
        }
    }




    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            , color = Colors.mDarkPurple
    ) {

        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            QuestionTracker(totalQuestions = totalQuestions, counter = questionIndex.intValue)
            DrawDashPathEffect(pathEffect)
            Column {
                Text(
                    text = question.question,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .align(Alignment.Start),
                    fontSize = 17.sp,
                    color = Colors.mOffWhite,
                    fontWeight = FontWeight.Bold,
                    lineHeight = TextUnit(22f, TextUnitType.Sp)
                )
                choicesState.forEachIndexed { index, answerText ->
                    RowAnswer(index, answerText, answerState, correctAnswerState, updateAnswer)
                }
                Button(onClick = { onNextClicked(questionIndex.intValue) }
                    , modifier = Modifier
                        .padding(3.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(34.dp)
                    , colors = ButtonDefaults.buttonColors(Colors.mOffDarkPurple)) {
                        Text(text = "Next", modifier = Modifier.padding(3.dp)
                            , color = Colors.mOffWhite, fontSize = 17.sp)
                }
            }
        }
    }
}

//@Preview
@Composable
fun RowAnswer(
    index: Int,
    answerText: String,
    answerState: MutableState<Int?>,
    correctAnswerState: MutableState<Boolean?>,
    updateAnswer: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(55.dp)
            .border(
                width = 3.dp, brush = Brush.linearGradient(
                    colors =
                    listOf(Colors.mOffDarkPurple, Colors.mLightGray)
                ), shape = RoundedCornerShape(15.dp)
            )
            .clip(
                RoundedCornerShape(
                    topEndPercent = 50,
                    topStartPercent = 50,
                    bottomStartPercent = 50,
                    bottomEndPercent = 50
                )
            )
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = answerState.value == index, onClick = {
            updateAnswer(index)
        }, modifier = Modifier.padding(16.dp), colors = RadioButtonDefaults
            .colors(
            selectedColor = if (correctAnswerState.value == true && index == answerState.value)
                Color.Green.copy(alpha = 0.2f)
            else
                Color.Red.copy(alpha = 0.2f)
            )
        )

        val annotatedString = buildAnnotatedString {
            withStyle(
                    style = SpanStyle(
                        color = if (correctAnswerState.value == true && index == answerState.value)
                            Color.Green.copy(alpha = 0.2f)
                        else if (correctAnswerState.value == false && index == answerState.value)
                            Color.Red.copy(alpha = 0.2f)
                        else
                            Colors.mOffWhite
                        , fontWeight = FontWeight.Bold, fontSize = 17.sp
                    )
                ) {
                    append(answerText)
                }
        }

        Text(text = annotatedString, modifier = Modifier.padding(6.dp))

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

@Preview
@Composable
fun ShowScore(score: Int = 12) {

    val gradient = Brush.linearGradient(listOf(Colors.mLightPurple,Colors.mDarkPurple))

    Row(modifier = Modifier
        .padding(3.dp)
        .background(Color.Transparent)
        .clip(
            RoundedCornerShape(
                topStartPercent = 50,
                bottomEndPercent = 50,
                bottomStartPercent = 50,
                topEndPercent = 50
            )
        )
        .fillMaxWidth()
        .height(45.dp)
        .border(
            width = 4.dp,
            shape = RoundedCornerShape(34.dp),
            brush = Brush.linearGradient(colors = listOf(Colors.mLightPurple, Colors.mLightPurple))
        )) {
        Button(onClick = { /*TODO*/ }
            , enabled = false
            , contentPadding = PaddingValues(1.dp)
            , modifier = Modifier.background( brush = gradient)
            , elevation = null
            , colors = ButtonDefaults
                .buttonColors(containerColor = Color.Transparent
                    , disabledContainerColor = Color.Transparent)
            ) {
        }
    }
}

@Composable
fun DrawDashPathEffect(pathEffect: PathEffect) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {

        drawLine(
            color = Colors.mLightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }
}