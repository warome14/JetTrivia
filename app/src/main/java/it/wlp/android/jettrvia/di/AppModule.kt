package it.wlp.android.jettrvia.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.wlp.android.jettrvia.net.QuestionApi
import it.wlp.android.jettrvia.util.Constants.BASE_QUESTIONS_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule

@Singleton
@Provides
fun provideQuestionApi() : QuestionApi = Retrofit.Builder()
    .baseUrl(BASE_QUESTIONS_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(QuestionApi::class.java)